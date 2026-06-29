package br.com.projetotabajara.tabajara.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetotabajara.tabajara.entity.Produto;
import br.com.projetotabajara.tabajara.repository.ProdutoRepository;

@Service
public class ProdutoService {
    // Injeção de dependência do repositório de produtos

    @Autowired

    private ProdutoRepository produtoRepository;

    // Método para salvar um produto

    public Produto save(Produto produto) {

        return produtoRepository.save(produto); // Salva o produto no repositório e retorna o produto salvo

    }

    // Método para buscar todos os produtos

    public List<Produto> findAll() {

        return produtoRepository.findAll(); // Retorna todos os produtos do repositório

    }

    // Método para buscar um produto pelo id

    public Produto findById(Integer id) {

        return produtoRepository.findById(id).orElse(null); // Retorna o produto com o id especificado ou null se não
                                                            // existir

    }

    // Método para excluir um produto pelo id

    public void deleteById(Integer id) {

        produtoRepository.deleteById(id); // Exclui o produto com o id especificado do repositório

    }
}
