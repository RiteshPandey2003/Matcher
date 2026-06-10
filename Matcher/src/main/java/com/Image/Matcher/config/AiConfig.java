package com.Image.Matcher.config;

import dev.langchain4j.model.openaiofficial.OpenAiOfficialChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Value("${github.api.key}")
    private String apiKey;

    @Bean
    public OpenAiOfficialChatModel chatModel() {

        // Fail fast — catch missing/wrong key at startup, not at request time
        if (apiKey == null || apiKey.isBlank() || apiKey.equals("YOUR_GITHUB_PAT_HERE")) {
            throw new IllegalStateException(
                    "GitHub API key is not configured! " +
                            "Set 'github.api.key' in application.properties with your GitHub PAT. " +
                            "Get one from: https://github.com/settings/tokens (Models: Read and Write permission)"
            );
        }

        System.out.println("✅ GitHub API Key loaded — starts with: " + apiKey.substring(0, Math.min(8, apiKey.length())) + "...");

        return OpenAiOfficialChatModel.builder()
                .baseUrl("https://models.inference.ai.azure.com")   // correct GitHub Models endpoint
                .apiKey(apiKey)
                .modelName("gpt-4o-mini")                           // stable model on GitHub Models
                .build();
    }
}