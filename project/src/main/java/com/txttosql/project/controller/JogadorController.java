package com.txttosql.project.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.txttosql.project.database.models.Jogador;
import com.txttosql.project.dto.JogadorDto;
import com.txttosql.project.service.JogadorService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor 
@RequestMapping("v1/jogador")
public class JogadorController {

    private final JogadorService jogadorService;

    @GetMapping("/allJogador")
    public ResponseEntity<List<JogadorDto>> getAllJogadores() {
        List<JogadorDto> jogadores = jogadorService.getAllJogadores();
        return new ResponseEntity<>(jogadores, HttpStatus.OK);
    }

    @GetMapping("/jogadorById/{id}")
    public ResponseEntity<JogadorDto> getJogadorById(@PathVariable UUID id) {

        Jogador jogador = jogadorService.getJogadorById(id);

        if(jogador != null) {
            JogadorDto jogadorDto = JogadorDto.builder()
                    .nome(jogador.getNome())
                    .dataNascimento(jogador.getDataNascimento())
                    .posicao(jogador.getPosicao())
                    .nacionalidade(jogador.getNacionalidade())
                    .nomeTime(jogador.getTime().getNome())
                    .build();
            return new ResponseEntity<>(jogadorDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createJogador")
    public ResponseEntity<Void> createJogador(@RequestBody JogadorDto jogadorDto) {
        jogadorService.createJogador(jogadorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @DeleteMapping ("/deleteJogador/{id}")
    public ResponseEntity<Void> deleteJogador(@PathVariable UUID id) {
        jogadorService.deleteJogador(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
