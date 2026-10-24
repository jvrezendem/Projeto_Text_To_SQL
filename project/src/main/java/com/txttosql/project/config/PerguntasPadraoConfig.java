package com.txttosql.project.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.txttosql.project.dto.PerguntaPadraoDto;

@ConfigurationProperties
public record PerguntasPadraoConfig(
    List<PerguntaPadraoDto> perguntas
) {

}
