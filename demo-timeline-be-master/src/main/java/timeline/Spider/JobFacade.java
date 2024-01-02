// Copyright (C) 2022 Meituan
// All rights reserved
package timeline.Spider;

import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import timeline.db.model.Clue;
import timeline.db.respository.ClueRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author wangxi
 * @version 1.0
 * @created 2023
 **/
@Service
@Slf4j
public class JobFacade {

    @Autowired
    private ClueRepository clueRepository;
    //定时任务

    /**
     * cron：使用Cron表达式。每秒执行
     */
    @Scheduled(cron = "1 * * * * ?")
    public void reportCurrentTimeWithCronExpression() {
        log.info("Cron Expression: The begin time is: {}", new Date());
//        doJob();
        log.info("Cron Expression: The end time is: {}", new Date());
    }

    private void doJob() {
        String content = searchFromBaidu("手机发布会");
        List<String> list = parseHtml(content);
        if (list.size() == 0) {
            return;
        }
        for (String url : list) {
            final Optional<Clue> byUrl = clueRepository.findByUrl(url);
            if (byUrl.isPresent()) {
                continue;
            }
            Clue clue = new Clue();
            clue.setUrl(url);
            clue.setStatus("新数据");
            clueRepository.save(clue);
        }
    }

    private final OkHttpClient httpClient = new OkHttpClient();

    private List<String> parseHtml(String html) {
        if (StringUtil.isBlank(html)) {
            return Collections.emptyList();
        }
        List<String> titleUrls = new ArrayList<>();
        final Document document = Jsoup.parse(html);
        Elements sourceLinks = document.select("a.source-link_Ft1ov");
        for (Element sourceLink : sourceLinks) {
            String url = sourceLink.attr("href");
            titleUrls.add(url);
        }
        return titleUrls;
    }

    private String searchFromBaidu(String keyword) {

        // https://www.baidu.com/s?tn=news&rtt=4&bsst=1&cl=2&wd=fs&medium=0
        String baseUrl = "http://www.baidu.com/s";
        Map<String, String> params = new HashMap<>();
        params.put("tn", "news");
        params.put("rtt", "4");
        params.put("bsst", "1");
        params.put("cl", "2");
        params.put("wd", keyword);

        //做http请求
        return sendGetRequest(baseUrl, params);
    }

    private String sendGetRequest(String url, Map<String, String> parameters) {
        HttpUrl parse = HttpUrl.parse(url);
        if (parse == null) {
            return null;
        }
        HttpUrl.Builder httpBuilder = parse.newBuilder();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }

        Request request = new Request.Builder().url(httpBuilder.build()).build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            ResponseBody body = response.body();
            if (body == null) {
                return null;
            }
            return body.string();
        } catch (Exception e) {
            log.error("sendGetRequest error", e);
        }
        return null;
    }

}
