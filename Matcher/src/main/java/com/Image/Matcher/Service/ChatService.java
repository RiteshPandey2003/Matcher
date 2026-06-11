package com.Image.Matcher.Service;

import com.Image.Matcher.DTO.ChatRequest;
import com.Image.Matcher.DTO.ChatResponse;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private static final Logger log = LoggerFactory.getLogger(ChatService.class);

    private final OllamaChatModel chatModel;

    public ChatService(OpenAiOfficialChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public ChatResponse askQuestion(ChatRequest request) {

        if (request.getPrompt() == null || request.getPrompt().isBlank()) {
            return ChatResponse.error("Prompt cannot be empty");
        }

        log.info("Incoming prompt: {}", request.getPrompt());

        try {
            String answer = chatModel.chat(request.getPrompt());
            log.info("AI response received successfully");
            return ChatResponse.ok(answer);

        } catch (Exception e) {
            log.error("Error calling AI model: {}", e.getMessage(), e);
            return ChatResponse.error("AI call failed: " + e.getMessage());
        }
    }
}