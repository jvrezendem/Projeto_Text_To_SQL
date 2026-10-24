package com.txttosql.project.service;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataBseContextService {

    public String migrationReader() {
        var resource = new ClassPathResource("db/migration/V1__criar_estrutura.sql");
        try {
            return resource.getContentAsString(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
