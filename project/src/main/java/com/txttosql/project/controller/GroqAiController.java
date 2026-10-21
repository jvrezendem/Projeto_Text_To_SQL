package com.txttosql.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.txttosql.project.dto.Request.PerguntaRequestDto;
import com.txttosql.project.dto.Response.RespostaResponseDto;
import com.txttosql.project.service.GroqAiService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("v1/ai")
public class GroqAiController {

    private final GroqAiService groqAiService;

    @PostMapping("/perguntar")
    public ResponseEntity<RespostaResponseDto> perguntar(@RequestBody PerguntaRequestDto pergunta) {
        RespostaResponseDto resposta = groqAiService.fazerPergunta(pergunta);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
