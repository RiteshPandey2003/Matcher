package com.Image.Matcher.Service;

import com.Image.Matcher.DTO.ChatRequest;
import com.Image.Matcher.config.AiConfig;
import org.springframework.stereotype.Service;

import java.util.stream.DoubleStream;

@Service
public class ChatService {

    public String askQuestion(ChatRequest prompt) {
        return AiConfig.generate(prompt.toString());
    }
}
