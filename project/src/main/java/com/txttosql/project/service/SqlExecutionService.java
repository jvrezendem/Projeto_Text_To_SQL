package com.txttosql.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.txttosql.project.exception.InvalidSqlException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SqlExecutionService {

    private final JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
    public List<Map<String, Object>> executarSQL(String comandoSql) {
        try {
            return jdbcTemplate.queryForList(comandoSql);
        } catch (BadSqlGrammarException e) {
            throw new InvalidSqlException(comandoSql, e);
        }
    }
}
