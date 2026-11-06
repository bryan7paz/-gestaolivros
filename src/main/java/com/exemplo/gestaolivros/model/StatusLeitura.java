package com.exemplo.gestaolivros.model;

/**
 * Status de leitura possível para um livro.
 */
public enum StatusLeitura {
    QUERO_LER("Quero ler"),
    LENDO("Lendo"),
    LIDO("Lido"),
    PAUSADO("Pausado"),
    ABANDONADO("Abandonado");

    private final String descricao;

    StatusLeitura(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
