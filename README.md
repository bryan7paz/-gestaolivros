# Gerenciamento de Livros - Biblioteca Pessoal

Aplicação web desenvolvida com **Java 21**, **Spring Boot**, **Thymeleaf**, **JPA/Hibernate** e banco de dados **H2**, para gerenciamento de uma biblioteca pessoal.

## Funcionalidades

| Funcionalidade | Descrição | Como acessar |
|---|---|---|
| **Cadastrar** | Adicionar novos livros (título, autor, gênero, ano de publicação e status de leitura) | Botão "+ Cadastrar novo livro" na página inicial |
| **Listar** | Exibir todos os livros cadastrados | Página inicial (`/livros`) |
| **Consultar** | Buscar um livro específico pelo seu identificador (ou por título) | Clicar no título do livro / campo de busca |
| **Atualizar** | Modificar as informações de um livro cadastrado | Botão "Editar" |
| **Excluir** | Remover um livro do sistema | Botão "Excluir" |

## Estrutura do projeto (POO + Spring Boot)

```
src/main/java/com/exemplo/gestaolivros/
├── GestaolivrosApplication.java   # Classe principal (inicializa o Spring Boot)
├── model/
│   ├── Livro.java                 # Entidade (classe, atributos e métodos - POO)
│   └── StatusLeitura.java         # Enum com o status de leitura
├── repository/
│   └── LivroRepository.java       # Acesso aos dados (Spring Data JPA)
├── service/
│   └── LivroService.java          # Regras de negócio (Cadastrar, Listar, Consultar, Atualizar, Excluir)
└── controller/
    └── LivroController.java       # Páginas web (Spring MVC + Thymeleaf)

src/main/resources/
├── application.properties         # Configurações (H2, JPA)
└── templates/
    ├── fragments.html             # Estilos e cabeçalho
    ├── lista.html                 # Página de listagem
    ├── form.html                  # Página de cadastro/atualização
    └── detalhe.html               # Página de consulta
```

### Conceitos de POO aplicados
- **Classe** - `Livro` é o molde que descreve um livro.
- **Atributos** - `titulo`, `autor`, `genero`, `anoPublicacao`, `status`.
- **Métodos** - getters/setters, `atualizarDados()`, métodos do service e controller.
- **Objetos** - cada livro cadastrado é uma instância (objeto) da classe `Livro`.
- **Encapsulamento** - atributos privados acessados por getters/setters.
- **Enum** - `StatusLeitura` restringe os valores possíveis de status.

## Como executar

### IntelliJ IDEA
1. Abra o IntelliJ IDEA → **File → Open** → selecione a pasta do projeto (`gestaolivros`).
2. Aguarde o Maven baixar as dependências.
3. Execute a classe `GestaolivrosApplication` (botão verde ▶).

### Linha de comando (opcional)
```
./mvnw.cmd spring-boot:run
```

A aplicação sobe em **http://localhost:8080/livros** (o navegador abre automaticamente a página de listagem).
Ao iniciar, a aplicação já cadastra 3 livros de exemplo.

### Console do banco de dados H2 (opcional)
Com a aplicação rodando, acesse http://localhost:8080/h2-console e use:
- JDBC URL: `jdbc:h2:mem:gestaolivrosdb`
- User: `sa`
- Senha: *(vazio)*

## Testes

Execute os testes automatizados com:
```
./mvnw.cmd test
```

### Roteiro de teste manual (demonstração)
1. **Listar** - abra http://localhost:8080/livros → os livros de exemplo aparecem.
2. **Cadastrar** - clique em "+ Cadastrar novo livro", preencha título, autor, gênero, ano e status → "Cadastrar".
3. **Consultar** - use a busca ou clique no título do livro → o sistema carrega o registro pelo id.
4. **Atualizar** - clique "Editar", altere os dados e salve.
5. **Excluir** - clique "Excluir" e confirme.
