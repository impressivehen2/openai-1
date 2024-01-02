package com.impressivehen.openai.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Autowired
    private AIConfig aiConfig;

    @Bean
    public RestTemplate imagePromptRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            HttpHeaders httpHeaders = request.getHeaders();
            httpHeaders.add("Authorization", "Bearer " + aiConfig.getApiKey());
            httpHeaders.add("Content-Type", "application/json");
            return execution.execute(request, body);
        });

        return restTemplate;
    }
}
