package com.txttosql.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.txttosql.project.dto.Response.PerguntaPadraoResponseDto;
import com.txttosql.project.service.QuestoesPadraoService;

import lombok.AllArgsConstructor;

@RestController 
@AllArgsConstructor 
@RequestMapping("/questoes-padrao")
public class QuestoesPadraoController {

    private final QuestoesPadraoService questoesPadraoService;

    @GetMapping("/{id}")
    public ResponseEntity<PerguntaPadraoResponseDto> getStatusPerguntaPadrao(@PathVariable String id) {
        PerguntaPadraoResponseDto resposta = questoesPadraoService.criarResposta(id);
        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
