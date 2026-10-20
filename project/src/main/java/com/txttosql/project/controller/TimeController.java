package com.txttosql.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.txttosql.project.database.models.Time;
import com.txttosql.project.dto.TimeDto;
import com.txttosql.project.service.TimeService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController 
@AllArgsConstructor 
@RequestMapping ("v1/time")
public class TimeController {

    private final TimeService timeService;

    @GetMapping("/allTimes")
    public ResponseEntity<List<Time>> getAllTimes() {
    
        return new ResponseEntity<>(timeService.getAllTimes(), HttpStatus.OK);
    }

    @GetMapping("/timeById/{id}")
    public ResponseEntity<Time> getTimeById(@PathVariable UUID id) {
        Time time = timeService.getTimeById(id);
        if (time != null) {
            return new ResponseEntity<>(time, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping ("/timeByNome/{nome}")
    public ResponseEntity<Time> getTimeByNome(@PathVariable String nome) {
        Time time = timeService.findByNome(nome);
        if (time != null) {
            return new ResponseEntity<>(time, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createTime")
    public ResponseEntity<Void> createTime(@RequestBody TimeDto timeDto) {
        timeService.createTime(timeDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @DeleteMapping ("/deleteTime/{id}")
    public ResponseEntity<Void> deleteTimeById(UUID id) {
        timeService.deleteTimeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
