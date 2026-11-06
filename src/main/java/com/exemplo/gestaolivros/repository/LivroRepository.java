package com.exemplo.gestaolivros.repository;

import com.exemplo.gestaolivros.model.Livro;
import com.exemplo.gestaolivros.model.StatusLeitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório (interface) responsável pelo acesso aos dados dos livros.
 * O Spring Data JPA gera automaticamente a implementação em tempo de execução.
 */
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    /** Busca livros cujo título contenha o texto informado (ignorando maiúsculas/minúsculas). */
    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    /** Lista todos os livros com um determinado status de leitura. */
    List<Livro> findByStatus(StatusLeitura status);
}
