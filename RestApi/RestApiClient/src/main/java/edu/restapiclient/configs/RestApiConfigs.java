package edu.restapiclient.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestApiConfigs {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
