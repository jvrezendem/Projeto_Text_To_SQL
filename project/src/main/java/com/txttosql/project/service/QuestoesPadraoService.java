package com.txttosql.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.txttosql.project.config.PerguntasPadraoConfig;
import com.txttosql.project.dto.PerguntaPadraoDto;
import com.txttosql.project.dto.Request.PerguntaRequestDto;
import com.txttosql.project.dto.Response.PerguntaPadraoResponseDto;
import com.txttosql.project.dto.Response.ResultadoArqResponseDto;
import com.txttosql.project.exception.IlegalQuestionException;
import com.txttosql.project.exception.InvalidSqlException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestoesPadraoService {

    private final PerguntasPadraoConfig perguntasPadraoConfig;
    private final GroqAiService groqAiService;

    private PerguntaPadraoDto getPerguntaPadraoByID(String id) {
        return perguntasPadraoConfig.perguntas().stream()
                .filter(pergunta -> pergunta.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pergunta padrão não encontrada para o ID: " + id));
    }

    public PerguntaPadraoResponseDto criarResposta(String id) {
        PerguntaPadraoDto perguntaPadrao = getPerguntaPadraoByID(id);
        PerguntaRequestDto pergunta = new PerguntaRequestDto(perguntaPadrao.texto());

        ResultadoTentativa semContexto = executarTentativa(() -> groqAiService.txtToSqlNoContext(pergunta));
        ResultadoTentativa comContexto = executarTentativa(() -> groqAiService.txtToSqlContext(pergunta));
        List<Map<String, Object>> gabarito = perguntaPadrao.gabarito().getOrDefault("registros", List.of());

        return new PerguntaPadraoResponseDto(
                perguntaPadrao.texto(),
                perguntaPadrao.sqlReferencia(),
                perguntaPadrao.gabarito(),
                semContexto.sql(),
                semContexto.registros(),
                comContexto.sql(),
                comContexto.registros()
        );
    }

    private ResultadoTentativa executarTentativa(Supplier<ResultadoArqResponseDto> chamada) {
        try {
            ResultadoArqResponseDto resultado = chamada.get();
            return new ResultadoTentativa(resultado.sql(), resultado.registros_encontrados(), null);
        } catch (InvalidSqlException e) {
            return new ResultadoTentativa(e.getSql(), List.of(), e.getMessage());
        } catch (IlegalQuestionException e) {
            return new ResultadoTentativa(e.getSql(), List.of(), e.getMessage());
        }
    }


    private record ResultadoTentativa(String sql, List<Map<String, Object>> registros, String erro) {
    }
}
