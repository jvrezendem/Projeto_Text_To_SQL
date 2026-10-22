package com.txttosql.project.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.txttosql.project.database.models.Partida;
import com.txttosql.project.dto.PartidaDto;
import com.txttosql.project.service.PartidaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("v1/partidas")
public class PartidaController {

    private final PartidaService partidaService;

    @GetMapping("/allPartidas")
    public ResponseEntity<List<Partida>> getAllPartidas() {
        return new ResponseEntity<>(partidaService.getAllPartidas(), HttpStatus.OK);
    }

    @GetMapping("/getPartidaById")
    public ResponseEntity<Partida> getPartidaById(UUID id) {
        Partida partida = partidaService.getPartidaById(id);
        if (partida != null) {
            return new ResponseEntity<>(partida, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createPartida")
    public ResponseEntity<Void> createPartida(@RequestBody PartidaDto partidaDto) {
        partidaService.createPartida(partidaDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deletePartidaById")
    public ResponseEntity<Void> deletePartidaById(UUID id) {
        partidaService.deletePartidaById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
