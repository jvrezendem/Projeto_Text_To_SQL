package com.txttosql.project.dto;

import java.util.UUID;

public record GolDto(String jogadorNome, UUID partidaId, String campeonatoNome, String timeNome, int minuto) {
}
