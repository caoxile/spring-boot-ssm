package com.company.project.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Package com.company.project.common.config
 * @Description
 * @Project hulk-backend-service
 * @Author caoxile
 * @Create 2019-05-15
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
