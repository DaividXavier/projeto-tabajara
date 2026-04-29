package br.com.projetotabajara.tabajara.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.projetotabajara.tabajara.entity.Pedido;
import br.com.projetotabajara.tabajara.entity.Produto;
import br.com.projetotabajara.tabajara.entity.Usuario;
import br.com.projetotabajara.tabajara.service.PedidoService;
import br.com.projetotabajara.tabajara.service.ProdutoService;
import br.com.projetotabajara.tabajara.service.UsuarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/pedidos")

public class PedidoController {

@Autowired
private UsuarioService usuarioService;
@Autowired
private PedidoService pedidoService;
@Autowired
private ProdutoService produtoService;

//endpoint para salvar o pedido (Json usado pelo fetch)
@PostMapping
@ResponseBody
public Pedido salvarPedido(@RequestBody Pedido pedido) {
    return pedidoService.salvarPedido(pedido);
}
//abrir a tela de cadastro
@GetMapping("/criar")
public String criarForm(Model model) {
    model.addAttribute("pedido", new Pedido());
    List<Usuario> usuarios = usuarioService.listarTodos();
    model.addAttribute("usuarios", usuarios);
    List<Produto> produtos = produtoService.findAll();
    model.addAttribute("produtos", produtos);
    return "pedido/formularioPedido";
}
}