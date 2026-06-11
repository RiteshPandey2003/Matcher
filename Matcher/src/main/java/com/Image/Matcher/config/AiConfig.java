package com.Image.Matcher.config;

import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Value("${ollama.base-url:http://localhost:11434}")
    private String ollamaBaseUrl;

    @Value("${ollama.model:llama3.2:3b}")
    private String modelName;

    @Bean
    public OllamaChatModel chatModel() {

        // Verify Ollama is reachable
        System.out.println("✅ Ollama Configuration:");
        System.out.println("   Base URL: " + ollamaBaseUrl);
        System.out.println("   Model: " + modelName);

        return OllamaChatModel.builder()
                .baseUrl(ollamaBaseUrl)     // Local Ollama instance
                .modelName(modelName)       // llama3.2:3b
                .build();
    }
}