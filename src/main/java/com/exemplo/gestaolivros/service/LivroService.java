package com.exemplo.gestaolivros.service;

import com.exemplo.gestaolivros.model.Livro;
import com.exemplo.gestaolivros.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Camada de serviço: concentra as regras de negócio do gerenciamento de livros.
 * Faz a ligação entre o Controller e o Repository.
 */
@Service
public class LivroService {

    private final LivroRepository repository;

    // Injeção de dependência pelo construtor (prática recomendada do Spring).
    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    /** CADSTRAR: adiciona um novo livro no sistema. */
    @Transactional
    public Livro cadastrar(Livro livro) {
        livro.setId(null); // garante que será um novo registro
        return repository.save(livro);
    }

    /** LISTAR: exibe todos os livros cadastrados. */
    @Transactional(readOnly = true)
    public List<Livro> listarTodos() {
        return repository.findAll();
    }

    /** CONSULTAR: busca um livro específico pelo seu identificador. */
    @Transactional(readOnly = true)
    public Optional<Livro> consultarPorId(Long id) {
        return repository.findById(id);
    }

    /** ATUALIZAR: modifica as informações de um livro cadastrado. */
    @Transactional
    public Optional<Livro> atualizar(Long id, Livro dadosAtualizados) {
        return repository.findById(id).map(livro -> {
            livro.atualizarDados(dadosAtualizados);
            return repository.save(livro);
        });
    }

    /** EXCLUIR: remove um livro do sistema. */
    @Transactional
    public boolean excluir(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /** LISTAR com filtro: busca por parte do título (usada na tela de listagem). */
    @Transactional(readOnly = true)
    public List<Livro> buscarPorTitulo(String titulo) {
        return (titulo == null || titulo.isBlank())
                ? repository.findAll()
                : repository.findByTituloContainingIgnoreCase(titulo.trim());
    }
}
