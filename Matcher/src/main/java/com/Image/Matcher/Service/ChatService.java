package com.Image.Matcher.Service;

import com.Image.Matcher.DTO.ChatRequest;
import com.Image.Matcher.DTO.ChatResponse;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private static final Logger log = LoggerFactory.getLogger(ChatService.class);

    private final OllamaChatModel chatModel;
    private final EmbeddingModel embeddingModel;

    public ChatService(OllamaChatModel chatModel, EmbeddingModel embeddingModel) {
        this.chatModel = chatModel;
        this.embeddingModel = embeddingModel;
    }

    public ChatResponse askQuestion(ChatRequest request) {

        if (request.getPrompt() == null || request.getPrompt().isBlank()) {
            return ChatResponse.error("Prompt cannot be empty");
        }

        log.info("Incoming prompt: {}", request.getPrompt());

        try {
            String answer = chatModel.generate(request.getPrompt());
            Embedding e = embeddingModel.embed(request.getPrompt()).content();

            System.out.println(e);
            log.info("AI response received successfully");
            return ChatResponse.ok(answer);

        } catch (Exception e) {
            log.error("Error calling AI model: {}", e.getMessage(), e);
            return ChatResponse.error("AI call failed: " + e.getMessage());
        }
    }
}