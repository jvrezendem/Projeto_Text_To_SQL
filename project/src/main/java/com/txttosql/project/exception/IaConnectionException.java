package com.txttosql.project.exception;

public class IaConnectionException extends RuntimeException {
    public IaConnectionException() {
        super("Não foi possível conectar com a API do Groq.");
    }
}
