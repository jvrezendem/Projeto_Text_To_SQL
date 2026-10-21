package com.txttosql.project.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.txttosql.project.database.models.Time;
import com.txttosql.project.database.repository.ITimeRepository;
import com.txttosql.project.dto.TimeDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimeService {

    private final ITimeRepository timeRepository;

    public List<Time> getAllTimes() {
        List<Time> times = timeRepository.findAll();

        return times;
    }

    public Time getTimeById(UUID id) {
        Time time = timeRepository.findById(id).orElse(null);
        return time;
    }

    // usado no service de jogador
    public Time findByNome(String nome) {
        Time time = timeRepository.findByNome(nome);
        return time;
    }

    public void createTime(TimeDto timeDto) {
        timeRepository.save(Time.builder()
                .nome(timeDto.nome())
                .pais(timeDto.pais())
                .cidade(timeDto.cidade())
                .dataFundacao(timeDto.fundacao())
                .build());
    }

    public void deleteTimeById(UUID id) {
        timeRepository.deleteById(id);
    }

}
