package com.Image.Matcher.Service;

import com.Image.Matcher.DTO.ChatRequest;
import dev.langchain4j.model.openaiofficial.OpenAiOfficialChatModel;
import org.springframework.stereotype.Service;



@Service
public class ChatService {

    private final OpenAiOfficialChatModel chatModel;

    public ChatService(OpenAiOfficialChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String askQuestion(ChatRequest request) {

        System.out.println("Incoming Prompt: " + request.getPrompt());

        String answer = chatModel.chat(request.getPrompt());

        System.out.println("AI Response: " + answer);

        return answer;
    }
}
