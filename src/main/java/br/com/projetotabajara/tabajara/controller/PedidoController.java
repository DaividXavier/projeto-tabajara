package br.com.projetotabajara.tabajara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projetotabajara.tabajara.service.PedidoService;
import br.com.projetotabajara.tabajara.service.ProdutoService;
import br.com.projetotabajara.tabajara.service.UsuarioService;

@Controller
@RequestMapping("/pedidos")

public class PedidoController {

@Autowired
private UsuarioService usuarioService;
@Autowired
private PedidoService pedidoService;
@Autowired
private ProdutoService produtoService;


}