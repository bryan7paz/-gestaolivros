package com.exemplo.gestaolivros.controller;

import com.exemplo.gestaolivros.model.Livro;
import com.exemplo.gestaolivros.model.StatusLeitura;
import com.exemplo.gestaolivros.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller (camada de apresentação) responsável pelas páginas web do sistema.
 * Recebe as requisições do navegador, chama o LivroService e devolve as páginas Thymeleaf.
 */
@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService service;

    public LivroController(LivroService service) {
        this.service = service;
    }

    /** LISTAR: exibe todos os livros cadastrados (com busca opcional por título). */
    @GetMapping
    public String listar(@RequestParam(required = false) String titulo, Model model) {
        model.addAttribute("livros", service.buscarPorTitulo(titulo));
        model.addAttribute("filtro", titulo == null ? "" : titulo);
        return "lista";
    }

    /** CONSULTAR: busca um livro específico pelo seu identificador. */
    @GetMapping("/{id}")
    public String consultar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        return service.consultarPorId(id)
                .<String>map(livro -> {
                    model.addAttribute("livro", livro);
                    return "detalhe";
                })
                .orElseGet(() -> {
                    redirect.addFlashAttribute("erro", "Livro não encontrado (id " + id + ").");
                    return "redirect:/livros";
                });
    }

    /** Formulário de CADASTRO de um novo livro. */
    @GetMapping("/novo")
    public String formularioNovo(Model model) {
        model.addAttribute("livro", new Livro());
        model.addAttribute("statusLeitura", StatusLeitura.values());
        model.addAttribute("acao", "Cadastrar");
        return "form";
    }

    /** CADASTRAR: salva o novo livro enviado pelo formulário. */
    @PostMapping
    public String cadastrar(@Valid Livro livro, BindingResult resultado,
                            Model model, RedirectAttributes redirect) {
        if (resultado.hasErrors()) {
            model.addAttribute("statusLeitura", StatusLeitura.values());
            model.addAttribute("acao", "Cadastrar");
            return "form";
        }
        service.cadastrar(livro);
        redirect.addFlashAttribute("sucesso", "Livro \"" + livro.getTitulo() + "\" cadastrado com sucesso!");
        return "redirect:/livros";
    }

    /** Formulário de ATUALIZAÇÃO: carrega os dados do livro para edição. */
    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        return service.consultarPorId(id)
                .<String>map(livro -> {
                    model.addAttribute("livro", livro);
                    model.addAttribute("statusLeitura", StatusLeitura.values());
                    model.addAttribute("acao", "Atualizar");
                    return "form";
                })
                .orElseGet(() -> {
                    redirect.addFlashAttribute("erro", "Livro não encontrado (id " + id + ").");
                    return "redirect:/livros";
                });
    }

    /** ATUALIZAR: salva as modificações de um livro cadastrado. */
    @PostMapping("/{id}")
    public String atualizar(@PathVariable Long id, @Valid Livro livro, BindingResult resultado,
                            Model model, RedirectAttributes redirect) {
        if (resultado.hasErrors()) {
            model.addAttribute("statusLeitura", StatusLeitura.values());
            model.addAttribute("acao", "Atualizar");
            return "form";
        }
        return service.atualizar(id, livro)
                .<String>map(atualizado -> {
                    redirect.addFlashAttribute("sucesso", "Livro atualizado com sucesso!");
                    return "redirect:/livros";
                })
                .orElseGet(() -> {
                    redirect.addFlashAttribute("erro", "Livro não encontrado (id " + id + ").");
                    return "redirect:/livros";
                });
    }

    /** EXCLUIR: remove um livro do sistema. */
    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, RedirectAttributes redirect) {
        if (service.excluir(id)) {
            redirect.addFlashAttribute("sucesso", "Livro excluído com sucesso!");
        } else {
            redirect.addFlashAttribute("erro", "Livro não encontrado (id " + id + ").");
        }
        return "redirect:/livros";
    }
}
