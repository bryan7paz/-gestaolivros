package com.exemplo.gestaolivros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Entidade que representa um livro da biblioteca pessoal.
 *
 * Conceitos de POO aplicados:
 * - Classe: molde que define as características (atributos) e os comportamentos (métodos) de um livro.
 * - Atributos: titulo, autor, genero, anoPublicacao e status.
 * - Métodos: getters e setters que encapsulam o acesso aos atributos.
 * - Objetos: cada linha da tabela tb_livros é um objeto (instância) desta classe.
 */
@Entity
@Table(name = "tb_livros")
public class Livro {

    /** Identificador único do livro (gerado automaticamente pelo banco). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 200, message = "O título deve ter no máximo 200 caracteres")
    @Column(nullable = false, length = 200)
    private String titulo;

    @NotBlank(message = "O autor é obrigatório")
    @Size(max = 150, message = "O autor deve ter no máximo 150 caracteres")
    @Column(nullable = false, length = 150)
    private String autor;

    @Size(max = 80, message = "O gênero deve ter no máximo 80 caracteres")
    @Column(length = 80)
    private String genero;

    @NotNull(message = "O ano de publicação é obrigatório")
    @Min(value = 1, message = "O ano de publicação deve ser maior que zero")
    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao;

    @NotNull(message = "O status de leitura é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "status_leitura", nullable = false, length = 20)
    private StatusLeitura status;

    // ===== Construtores =====

    public Livro() {
        // Construtor padrão exigido pelo JPA.
    }

    public Livro(String titulo, String autor, String genero, Integer anoPublicacao, StatusLeitura status) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anoPublicacao = anoPublicacao;
        this.status = status;
    }

    // ===== Métodos (comportamentos) =====

    /** Cadastra/atualiza os dados deste livro a partir de outro objeto Livro. */
    public void atualizarDados(Livro outros) {
        this.titulo = outros.titulo;
        this.autor = outros.autor;
        this.genero = outros.genero;
        this.anoPublicacao = outros.anoPublicacao;
        this.status = outros.status;
    }

    // ===== Getters e Setters (encapsulamento) =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public StatusLeitura getStatus() {
        return status;
    }

    public void setStatus(StatusLeitura status) {
        this.status = status;
    }
}
