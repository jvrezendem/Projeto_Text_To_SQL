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

import com.txttosql.project.database.models.Campeonato;
import com.txttosql.project.dto.CampeonatoDto;
import com.txttosql.project.service.CampeonatoService;

import lombok.AllArgsConstructor;

@RestController 
@AllArgsConstructor 
@RequestMapping ("v1/campeonato")
public class CampeonatoController {

    private final CampeonatoService campeonatoService;

    @GetMapping ("/allCampeonatos")
    public ResponseEntity<List<Campeonato>> getAllCampeonatos() {
        return new ResponseEntity<>(campeonatoService.getAllCampeonatos(), HttpStatus.OK);
    }
    
    @GetMapping ("/campeonatoById/{id}")
    public ResponseEntity<Campeonato> getCampeonatoById(@PathVariable UUID id) {
        Campeonato campeonato = campeonatoService.getCampeonatoById(id);
        if (campeonato != null) {
            return new ResponseEntity<>(campeonato, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping ("/createCampeonato")
    public ResponseEntity<Void> createCampeonato(@RequestBody CampeonatoDto campeonato) {
        campeonatoService.createCampeonato(campeonato);
        return new ResponseEntity<>(HttpStatus.CREATED);
    
    }

    @DeleteMapping ("/deleteCampeonato/{id}")
    public ResponseEntity<Void> deleteCampeonato(@PathVariable UUID id) {
        campeonatoService.deleteCampeonatoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
