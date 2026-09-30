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
        return timeRepository.findAll();
    }

    public Time getTimeById(UUID id) {
        return timeRepository.findById(id).orElse(null);
    }

    //usado no service de jogador
    public Time findByNome(String nome) {
        return timeRepository.findByNome(nome);
    }

    public void createTime(TimeDto timeDto) {
        timeRepository.save(Time.builder()
                .nome(timeDto.getNome())
                .pais(timeDto.getPais())
                .dataFundacao(timeDto.getFundacao())
                .build());
    }

    public void deleteTimeById(UUID id){
        timeRepository.deleteById(id);;
    }


}
