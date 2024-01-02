package timeline;// Copyright (C) 2022 Meituan
// All rights reserved

/**
 * @author wangxi
 * @version 1.0
 * @created 2022/9/20 11:15
 **/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAutoConfiguration
@EnableScheduling
@SpringBootApplication(scanBasePackages = { "timeline" })
public class Boot {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Boot.class, args);
    }

}
