package com.txttosql.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.txttosql.project.database.models.Jogador;
import com.txttosql.project.database.repository.IJogadorRepository;
import com.txttosql.project.dto.JogadorDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JogadorService {

    private final IJogadorRepository jogadorRepository;
    private final TimeService timeService;

    public List<JogadorDto> getAllJogadores() {
        List<Jogador> jogadores = jogadorRepository.findAll();

        List<JogadorDto> jogadoresDto = new ArrayList<>();

        for (Jogador jogador : jogadores) {
            JogadorDto jogadorDto = new JogadorDto(
                    jogador.getNome(),
                    jogador.getDataNascimento(),
                    jogador.getPosicao(),
                    jogador.getNacionalidade(),
                    jogador.getTime().getNome());
            jogadoresDto.add(jogadorDto);
        }

        return jogadoresDto;
    }

    public Jogador getJogadorById(UUID id) {
        return jogadorRepository.findById(id).orElse(null);
    }

    public void createJogador(JogadorDto jogadorDto) {
        jogadorRepository.save(Jogador.builder()
                .nome(jogadorDto.nome())
                .dataNascimento(jogadorDto.dataNascimento())
                .posicao(jogadorDto.posicao())
                .nacionalidade(jogadorDto.nacionalidade())
                .time(timeService.findByNome(jogadorDto.nomeTime()))
                .build());
    }

    public void deleteJogador(UUID id) {
        jogadorRepository.deleteById(id);
    }

    public Jogador findByNome(String nome) {
        return jogadorRepository.findByNome(nome);
    }
}
