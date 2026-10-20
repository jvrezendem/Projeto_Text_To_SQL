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

import com.txttosql.project.database.models.Gol;
import com.txttosql.project.dto.GolDto;
import com.txttosql.project.service.GolService;

import lombok.AllArgsConstructor;

@RestController 
@AllArgsConstructor 
@RequestMapping ("v1/gols")
public class GolController {

    private final GolService golService;

    @GetMapping ("/allGols")
    public ResponseEntity<List<Gol>> getAllGols() {
       return new ResponseEntity<>(golService.getAllGols(), HttpStatus.OK);
    }

    @GetMapping ("/getGolById")
    public ResponseEntity<Gol> getGolById(UUID id) {
        Gol gol = golService.getGolById(id);
        if (gol != null) {
            return new ResponseEntity<>(gol, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping ("/createGol")
    public ResponseEntity<Void> createGol(@RequestBody GolDto golDto) {
        golService.createGol(golDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping ("/deleteGolById")
    public ResponseEntity<Void> deleteGolById(UUID id) {
        golService.deleteGolById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
