package com.impressivehen.openai.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AIConfig {
    @Value("${spring.ai.openai.apikey}")
    private String apiKey;

    @Value("${spring.ai.openai.endpoint}}")
    private String openAIImageUrl;
}
