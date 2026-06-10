package com.Image.Matcher.Controller;

import com.Image.Matcher.DTO.ChatRequest;
import com.Image.Matcher.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    ChatService chatService;

    @PostMapping
    public String Chat(@RequestBody ChatRequest chatRequest){
        System.out.println(chatRequest);
        return chatService.askQuestion(chatRequest);
    }
}
