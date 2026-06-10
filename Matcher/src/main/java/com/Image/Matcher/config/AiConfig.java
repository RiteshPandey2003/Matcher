package com.Image.Matcher.config;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AiConfig {

    @Value("${google.api.key}")
    private String apiKey;

    static Client client = new Client();

    public Client geminiClient() {

        return Client.builder()
                .apiKey(apiKey)
                .build();
    }

    public static String generate(String prompt) {
        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-2.5-flash",
                        "Explain how AI works in a few words",
                        null);

        System.out.println(response.text());
        return prompt;
    }

}
