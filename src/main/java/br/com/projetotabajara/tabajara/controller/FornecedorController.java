package br.com.projetotabajara.tabajara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projetotabajara.tabajara.entity.Fornecedor;
import br.com.projetotabajara.tabajara.service.FornecedorService;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService serviceFornecedor;

    // Listar fornecedores
    @GetMapping("/listar")
    public String listar(Model model) {

        List<Fornecedor> fornecedores = serviceFornecedor.findAll();

        model.addAttribute("fornecedores", fornecedores);

        return "fornecedores/listarFornecedores";
    }

    // Abrir formulário de criação
    @GetMapping("/criar")
    public String criarForm(Model model) {

        model.addAttribute("fornecedor", new Fornecedor());

        return "fornecedores/formularioFornecedor";
    }

    // Salvar fornecedor
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Fornecedor fornecedor) {

        serviceFornecedor.save(fornecedor);

        return "redirect:/fornecedores/listar";
    }

    // Abrir formulário de edição
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {

        Fornecedor fornecedor = serviceFornecedor.findById(id);

        model.addAttribute("fornecedor", fornecedor);

        return "fornecedores/formularioFornecedor";
    }

    // Atualizar fornecedor
    @PutMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Integer id, @ModelAttribute Fornecedor fornecedor) {

        fornecedor.setIdFornecedor(id);

        serviceFornecedor.save(fornecedor);

        return "redirect:/fornecedores/listar";
    }

    // Excluir fornecedor
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {

        serviceFornecedor.deleteById(id);

        return "redirect:/fornecedores/listar";
    }
}