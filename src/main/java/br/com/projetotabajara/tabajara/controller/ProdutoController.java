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
import br.com.projetotabajara.tabajara.entity.Produto;
import br.com.projetotabajara.tabajara.service.FornecedorService;
import br.com.projetotabajara.tabajara.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService serviceProduto;

    @Autowired
    private FornecedorService serviceFornecedor;

    // Listar produtos
    @GetMapping("/listar")
    public String listar(Model model) {

        List<Produto> produtos = serviceProduto.findAll();

        model.addAttribute("produtos", produtos);

        return "produto/listarProdutos";
    }

    // Abrir formulário de criação
    @GetMapping("/criar")
    public String criarForm(Model model) {

        model.addAttribute("produto", new Produto());
//
        List<Fornecedor> fornecedores = serviceFornecedor.findAll();
        model.addAttribute("fornecedores", fornecedores);

        return "produto/formularioProduto";
    }

    // Salvar produto
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Produto produto) {

        serviceProduto.save(produto);

        return "redirect:/produtos/listar";
    }

    // Abrir formulário de edição
    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Integer id, Model model) {

        Produto produto = serviceProduto.findById(id);
        model.addAttribute("produto", produto);

        List<Fornecedor> fornecedores = serviceFornecedor.findAll();
        model.addAttribute("fornecedores", fornecedores);

        return "produto/formularioProduto";
    }

    // Atualizar produto
    @PutMapping("/atualizar/{id}")
    public String atualizar(@PathVariable Integer id, @ModelAttribute Produto produto) {

        produto.setIdProduto(id);

        serviceProduto.save(produto);

        return "redirect:/produtos/listar"; 
    }

    // Excluir produto
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {

        serviceProduto.deleteById(id);

        return "redirect:/produtos/listar";
    }
}