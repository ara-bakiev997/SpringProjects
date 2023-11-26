package edu.restapiclient.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestApiClientConfiguration {
    @Value("${rest_api_client.url}")
    private String serverUrl;
    // TODO можно вынести создание RestTemplate
}
