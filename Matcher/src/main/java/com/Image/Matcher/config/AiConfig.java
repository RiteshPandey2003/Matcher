package com.Image.Matcher.config;


import dev.langchain4j.model.openaiofficial.OpenAiOfficialChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class AiConfig {

    @Value("${github.api.key}")
    private String apiKey;

    @Bean
    public OpenAiOfficialChatModel chatModel() {

        System.out.println("GitHub API Key Loaded Successfully");

        return OpenAiOfficialChatModel.builder()
                .baseUrl("https://models.github.ai/inference")
                .apiKey(apiKey)
                .modelName("gpt-4.1-nano")
                .build();
    }
}
