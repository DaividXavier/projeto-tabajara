package br.com.projetotabajara.tabajara.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetotabajara.tabajara.entity.ItemPedido;
import br.com.projetotabajara.tabajara.entity.Pedido;
import br.com.projetotabajara.tabajara.entity.Produto;
import br.com.projetotabajara.tabajara.repository.PedidoRepository;
import br.com.projetotabajara.tabajara.repository.ProdutoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Salvar pedido
    public Pedido salvarPedido(Pedido pedido) {
        pedido.setDataPedido(LocalDate.now());

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getIdProduto())
                    .orElseThrow(() -> new RuntimeException("Produto nao encontrado"));

            item.setProduto(produto);
            item.setPreco(produto.getValorProduto());
            item.atualizarSubtotal();
            item.setPedido(pedido);
        }

        pedido.atualizarTotal();
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }
}
