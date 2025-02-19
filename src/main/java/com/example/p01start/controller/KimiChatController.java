package com.example.p01start.controller;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.moonshot.MoonshotChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;

import java.util.Map;

@Controller
public class KimiChatController {

    @Autowired
    private MoonshotChatModel moonshotChatModel;

    @GetMapping("/kimi/generate")
    @ResponseBody
    public String generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        String call = moonshotChatModel.call(message);
        return call;
    }

    @GetMapping("/kimi/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        var prompt = new Prompt(new UserMessage(message));
        return moonshotChatModel.stream(prompt);
    }
}