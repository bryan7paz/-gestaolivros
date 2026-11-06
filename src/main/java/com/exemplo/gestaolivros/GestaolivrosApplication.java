package com.exemplo.gestaolivros;

import com.exemplo.gestaolivros.model.Livro;
import com.exemplo.gestaolivros.model.StatusLeitura;
import com.exemplo.gestaolivros.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Classe principal da aplicação Spring Boot.
 * Anotação @SpringBootApplication habilita a auto-configuração e a varredura de componentes.
 */
@SpringBootApplication
public class GestaolivrosApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaolivrosApplication.class, args);
    }

    /** Cadastra alguns livros de exemplo ao iniciar a aplicação (facilita os testes). */
    @Bean
    CommandLineRunner dadosIniciais(LivroRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", 1954, StatusLeitura.LIDO));
                repository.save(new Livro("Dom Casmurro", "Machado de Assis", "Romance", 1899, StatusLeitura.LENDO));
                repository.save(new Livro("Clean Code", "Robert C. Martin", "Tecnologia", 2008, StatusLeitura.QUERO_LER));
            }
        };
    }
}
