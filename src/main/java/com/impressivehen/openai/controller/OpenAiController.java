package com.impressivehen.openai.controller;

import com.impressivehen.openai.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1")
public class OpenAiController {
    @Autowired
    private AIService aiService;

    @GetMapping(value = "/image/{topic}", produces = "image/jpeg")
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String topic) throws URISyntaxException {
        return ResponseEntity.ok().body(aiService.getImage(topic));
    }
}
