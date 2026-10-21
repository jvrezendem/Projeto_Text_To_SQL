CREATE TABLE campeonato (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    temporada VARCHAR(50) NOT NULL,
    pais VARCHAR(50) NOT NULL,
    CONSTRAINT uk_campeonato_nome_temporada UNIQUE (nome, temporada)
);

CREATE TABLE "time" (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    pais VARCHAR(100) NOT NULL,
    data_fundacao DATE NOT NULL,
    CONSTRAINT uk_time_nome UNIQUE (nome)
);

CREATE TABLE jogador (
    id UUID PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    data_nascimento DATE NOT NULL,
    posicao VARCHAR(50) NOT NULL,
    nacionalidade VARCHAR(50) NOT NULL,
    time_id UUID NOT NULL,
    CONSTRAINT fk_jogador_time FOREIGN KEY (time_id) REFERENCES time(id)
);

CREATE TABLE partida (
    id UUID PRIMARY KEY,
    data DATE NOT NULL,
    rodada INTEGER,
    campeonato_id UUID NOT NULL,
    time_casa_id UUID NOT NULL,
    time_visitante_id UUID NOT NULL,
    gols_casa INTEGER,
    gols_visitante INTEGER,
    CONSTRAINT fk_partida_campeonato FOREIGN KEY (campeonato_id) REFERENCES campeonato (id),
    CONSTRAINT fk_partida_time_casa FOREIGN KEY (time_casa_id) REFERENCES time(id),
    CONSTRAINT fk_partida_time_visitante FOREIGN KEY (time_visitante_id) REFERENCES time(id),
    CONSTRAINT ck_partida_times_diferentes CHECK (
        time_casa_id <> time_visitante_id
    ),
    CONSTRAINT ck_partida_rodada CHECK (
        rodada IS NULL
        OR rodada BETWEEN 1 AND 38
    ),
    CONSTRAINT ck_partida_gols_casa CHECK (
        gols_casa IS NULL
        OR gols_casa >= 0
    ),
    CONSTRAINT ck_partida_gols_visitante CHECK (
        gols_visitante IS NULL
        OR gols_visitante >= 0
    )
);

CREATE TABLE gol (
    id UUID PRIMARY KEY,
    partida_id UUID NOT NULL,
    jogador_id UUID NOT NULL,
    minuto INTEGER NOT NULL,
    gol_contra BOOLEAN NOT NULL DEFAULT FALSE,
    penalti BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_gol_partida FOREIGN KEY (partida_id) REFERENCES partida (id),
    CONSTRAINT fk_gol_jogador FOREIGN KEY (jogador_id) REFERENCES jogador (id),
    CONSTRAINT ck_gol_minuto CHECK (minuto BETWEEN 1 AND 130)
);

CREATE INDEX idx_jogador_time ON jogador (time_id);

CREATE INDEX idx_jogador_nacionalidade ON jogador (nacionalidade);

CREATE INDEX idx_partida_campeonato ON partida (campeonato_id);

CREATE INDEX idx_partida_rodada ON partida (rodada);

CREATE INDEX idx_partida_time_casa ON partida (time_casa_id);

CREATE INDEX idx_partida_time_visitante ON partida (time_visitante_id);

CREATE INDEX idx_gol_partida ON gol (partida_id);

CREATE INDEX idx_gol_jogador ON gol (jogador_id);