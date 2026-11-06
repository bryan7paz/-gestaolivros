package com.exemplo.gestaolivros;

import com.exemplo.gestaolivros.model.Livro;
import com.exemplo.gestaolivros.model.StatusLeitura;
import com.exemplo.gestaolivros.service.LivroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes de integração da camada de serviço (H2 em memória).
 * Cobre as cinco funcionalidades: Cadastrar, Listar, Consultar, Atualizar e Excluir.
 */
@SpringBootTest
class GestaolivrosApplicationTests {

    @Autowired
    private LivroService service;

    /** Testa TODAS as funcionalidades em sequência. */
    @Test
    void deveCadastrarListarConsultarAtualizarEExcluirLivro() {
        // CADASTRAR
        Livro novo = service.cadastrar(new Livro("1984", "George Orwell", "Distopia", 1949, StatusLeitura.QUERO_LER));
        assertNotNull(novo.getId(), "O livro cadastrado deve receber um id gerado");

        // LISTAR
        List<Livro> todos = service.listarTodos();
        assertTrue(todos.stream().anyMatch(l -> l.getId().equals(novo.getId())),
                "O livro cadastrado deve aparecer na listagem");

        // CONSULTAR pelo identificador
        Livro consultado = service.consultarPorId(novo.getId()).orElseThrow();
        assertEquals("1984", consultado.getTitulo());

        // ATUALIZAR
        Livro dadosNovos = new Livro("1984", "George Orwell", "Ficção Científica", 1949, StatusLeitura.LIDO);
        Livro atualizado = service.atualizar(novo.getId(), dadosNovos).orElseThrow();
        assertEquals("Ficção Científica", atualizado.getGenero());
        assertEquals(StatusLeitura.LIDO, atualizado.getStatus());

        // EXCLUIR
        assertTrue(service.excluir(novo.getId()));
        assertFalse(service.excluir(novo.getId()), "Excluir o mesmo id duas vezes deve retornar false");
        assertTrue(service.consultarPorId(novo.getId()).isEmpty());
    }
}
