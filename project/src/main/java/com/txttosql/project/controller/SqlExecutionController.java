package com.txttosql.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.txttosql.project.dto.Request.PerguntaRequestDto;
import com.txttosql.project.dto.Response.SqlExecutionResponseDto;
import com.txttosql.project.service.SqlExecutionService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("v1/sqlExecution")
public class SqlExecutionController {

    private final SqlExecutionService sqlExecutionService;

    @PostMapping("/execute")
    public ResponseEntity<SqlExecutionResponseDto> executeSql(@RequestBody PerguntaRequestDto sql) {
        SqlExecutionResponseDto responseDTO = new SqlExecutionResponseDto(sql.pergunta(),
                sqlExecutionService.executarSQL(sql.pergunta()));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
