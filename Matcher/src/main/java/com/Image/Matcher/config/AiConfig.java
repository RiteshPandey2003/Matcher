package com.Image.Matcher.config;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Value("${ollama.base-url:http://localhost:11434}")
    private String ollamaBaseUrl;

    @Value("${ollama.model:llama3.2:3b}")
    private String modelName;

    @Value("${ollama.embedding-model:nomic-embed-text}")
    private String embeddingModelName;

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

    @Bean
    public EmbeddingModel embeddingModel() {

        System.out.println("✅ Embedding Model:");
        System.out.println("Base URL: " + ollamaBaseUrl);
        System.out.println("Model: " + embeddingModelName);

        return OllamaEmbeddingModel.builder()
                .baseUrl(ollamaBaseUrl)
                .modelName(embeddingModelName)
                .build();
    }
}