package com.example.p01start.controller;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@Controller
public class ChatController {

    @Autowired
    private OpenAiChatModel openAiChatModel;

    @RequestMapping("/stream")
    @ResponseBody
    public Flux<ChatResponse> chatStream(@RequestParam(name = "message") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return openAiChatModel.stream(prompt);
    }
}