package br.com.projetotabajara.tabajara.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import br.com.projetotabajara.tabajara.entity.ItemPedido;
import br.com.projetotabajara.tabajara.entity.Pedido;
import br.com.projetotabajara.tabajara.entity.Produto;
import br.com.projetotabajara.tabajara.entity.Usuario;
import br.com.projetotabajara.tabajara.service.PedidoService;
import br.com.projetotabajara.tabajara.service.ProdutoService;
import br.com.projetotabajara.tabajara.service.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoService produtoService;

    // endpoint para salvar o pedido (Json usado pelo fetch)
    @PostMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> salvarPedido(@RequestBody Map<String, Object> request) {
        Integer usuarioId = toInteger(request.get("usuarioId"));
        List<?> itensRequest = request.get("itens") instanceof List<?> lista ? lista : List.of();

        if (usuarioId == null) {
            throw new ResponseStatusException(BAD_REQUEST, "Usuario nao informado.");
        }

        if (itensRequest.isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "Adicione ao menos um produto.");
        }

        Pedido pedido = new Pedido();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(usuarioId);
        pedido.setUsuario(usuario);

        List<ItemPedido> itens = itensRequest.stream()
                .map(itemObject -> {
                    if (!(itemObject instanceof Map<?, ?> itemRequest)) {
                        throw new ResponseStatusException(BAD_REQUEST, "Item invalido no pedido.");
                    }

                    Integer produtoId = toInteger(itemRequest.get("produtoId"));
                    Integer quantidade = toInteger(itemRequest.get("quantidade"));

                    if (produtoId == null) {
                        throw new ResponseStatusException(BAD_REQUEST, "Produto invalido no pedido.");
                    }

                    if (quantidade == null || quantidade <= 0) {
                        throw new ResponseStatusException(BAD_REQUEST, "Quantidade invalida no pedido.");
                    }

                    Produto produto = new Produto();
                    produto.setIdProduto(produtoId);

                    ItemPedido item = new ItemPedido();
                    item.setProduto(produto);
                    item.setQuantidade(quantidade);
                    return item;
                })
                .collect(Collectors.toList());

        pedido.setItens(itens);
        Pedido pedidoSalvo = pedidoService.salvarPedido(pedido);
        return ResponseEntity.ok(Map.of(
                "sucesso", true,
                "idPedido", pedidoSalvo.getIdPedido()
        ));
    }

    // abrir a tela de cadastro
    @GetMapping("/criar")
    public String criarForm(Model model) {
        model.addAttribute("pedido", new Pedido());

        List<Usuario> usuarios = usuarioService.listarTodos();
        model.addAttribute("usuarios", usuarios);

        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);

        return "pedido/formularioPedido";
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        List<Pedido> pedidos = pedidoService.findAll();
        model.addAttribute("pedidos", pedidos);

        return "pedido/listarPedido";
    }

    private Integer toInteger(Object value) {
        if (value instanceof Integer integer) {
            return integer;
        }

        if (value instanceof Number number) {
            return number.intValue();
        }

        if (value instanceof String text && !text.isBlank()) {
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException ignored) {
                return null;
            }
        }

        return null;
    }
}
