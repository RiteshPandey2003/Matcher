package com.Image.Matcher.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {

    private String answer;
    private boolean success;
    private String error;

    // Success factory
    public static ChatResponse ok(String answer) {
        return new ChatResponse(answer, true, null);
    }

    // Error factory
    public static ChatResponse error(String errorMessage) {
        return new ChatResponse(null, false, errorMessage);
    }
}