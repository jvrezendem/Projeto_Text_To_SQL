package com.txttosql.project.exception;

public class InvalidSqlException extends RuntimeException {
    public InvalidSqlException(String respota) {
        super("Resposta não transformada em SQL:" + respota);
    }
}
