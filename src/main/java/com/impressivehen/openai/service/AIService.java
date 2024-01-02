package com.impressivehen.openai.service;

import com.impressivehen.openai.configuration.AIConfig;
import com.impressivehen.openai.models.OpenaiImageResponse;
import org.springframework.ai.client.AiClient;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class AIService {
    @Autowired
    private AIConfig aiConfig;

    @Autowired
    private AiClient aiClient;

    @Autowired
    private RestTemplate imagePromptRestTemplate;

    private final static PromptTemplate imagePromptTemplate = new PromptTemplate("""
                 I am really board from online memes. Can you create me a prompt about {topic}.
                 Elevate the given topic. Make it classy.
                 Make a resolution of 256x256, but ensure that it is presented in json it need to be string.
                 I desire only one creation. Give me as JSON format: prompt, n, size.
                """);

    public InputStreamResource getImage(String topic) throws URISyntaxException {
        imagePromptTemplate.add("topic", topic);
        String imagePrompt = this.aiClient.generate(imagePromptTemplate.create()).getGeneration().getText();
        HttpEntity<String> httpEntity = new HttpEntity<>(imagePrompt);

        String imageUrl = imagePromptRestTemplate.exchange(aiConfig.getOpenAIImageUrl(), HttpMethod.POST, httpEntity, OpenaiImageResponse.class)
                .getBody()
                .getData()
                .get(0)
                .getUrl();

        byte[] imageBytes = imagePromptRestTemplate.getForObject(new URI(imageUrl), byte[].class);

        assert imageBytes != null;

        return new InputStreamResource(new java.io.ByteArrayInputStream(imageBytes));
    }
}
