package com.txttosql.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.txttosql.project.service.GroqAiService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("v1/ai")
public class GroqAiController {

    private final GroqAiService groqAiService;

    @PostMapping("/perguntar")
    public ResponseEntity<String> perguntar(@RequestBody String pergunta) {
        String resposta = groqAiService.fazerPergunta(pergunta);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
