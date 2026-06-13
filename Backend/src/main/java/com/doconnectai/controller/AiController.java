package com.doconnectai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doconnectai.dto.QuestionDto;
import com.doconnectai.service.GeminiService;

@RestController
@RequestMapping("/ai")
public class AiController {

    @Autowired
    private GeminiService geminiService;

    @PostMapping("/answer")
    public String getAnswer(@RequestBody QuestionDto dto) {

        return geminiService.generateAnswer(dto);
    }
}