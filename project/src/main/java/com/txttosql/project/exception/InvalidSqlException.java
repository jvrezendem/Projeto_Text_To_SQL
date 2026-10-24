package com.txttosql.project.exception;

public class InvalidSqlException extends RuntimeException {
    private final String sql;

    public InvalidSqlException(String sql, Throwable cause) {
        super("Não foi possível executar o SQL: " + sql, cause);
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
