package com.txttosql.project.exception;

public class IlegalQuestionException extends RuntimeException {
    private final String sql;

    public IlegalQuestionException(String message) {
        this(message, null);
    }

    public IlegalQuestionException(String message, String sql) {
        super(message);
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
