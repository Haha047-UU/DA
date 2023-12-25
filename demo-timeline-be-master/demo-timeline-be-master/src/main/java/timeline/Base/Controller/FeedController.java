// Copyright (C) 2023 Meituan
// All rights reserved
package timeline.Base.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import timeline.Base.Model.Post;
import timeline.db.model.Briefing;
import timeline.db.model.Phone;
import timeline.db.service.BriefingService;
import timeline.db.service.PhoneService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangxi
 * @version 1.0
 * @created 2023/4/18 17:40
 **/
@RestController
public class FeedController {

    @Autowired
    private BriefingService briefingService;
    @Autowired
    private PhoneService phoneService;

    @CrossOrigin(origins = "http://localhost:8081") // Add this line
    @GetMapping("/api/info-feed")
    public List<Post> getInfoFeed(@RequestParam(defaultValue = "0") int pageNow,
            @RequestParam(defaultValue = "10") int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNow, pageSize, Sort.by(Briefing.BEGIN_TIME).descending());
        List<Briefing> all = briefingService.getAll(pageRequest);

        List<Long> collect = all.stream().map(Briefing::getId).collect(Collectors.toList());
        final Map<Long, List<Phone>> phoneByBriefing = phoneService.getPhoneByBriefing(collect);

        return all.stream().map(v -> {
            Post result = new Post();
            result.setTitle(v.getName());
            result.setAuthor(null);
            result.setTimestamp(getTimestamp(v.getBeginTime(), v.getEndTime()));
            List<Phone> orDefault = phoneByBriefing.getOrDefault(v.getId(), Collections.emptyList());
            result.setProductList(orDefault.stream().map(p -> {
                Post.Product product = new Post.Product();
                product.setName(p.getName());
                product.setMinPrice(p.getMinPrice());
                product.setMaxPrice(p.getMaxPrice());
                return product;
            }).collect(Collectors.toList()));
            return result;
        }).collect(Collectors.toList());
    }

    private String getTimestamp(String beginTime, String endTime) {
        if (beginTime == null) {
            return "";
        }
        if (beginTime.equals(endTime) || endTime == null) {
            return beginTime;
        }
        return beginTime + " - " + endTime;
    }
}

