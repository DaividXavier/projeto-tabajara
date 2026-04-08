package br.com.projetotabajara.tabajara.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetotabajara.tabajara.entity.Fornecedor;

import br.com.projetotabajara.tabajara.repository.FornecedorRepository;

@Service
public class FornecedorService {
    // Injeção de dependência do repositório de produtos

    @Autowired

    private FornecedorRepository fornecedorRepository;

    // Método para salvar um produto

    public Fornecedor save(Fornecedor fornecedor) {

        return fornecedorRepository.save(fornecedor); // Salva o produto no repositório e retorna o produto salvo

    }

    // Método para buscar todos os produtos

    public List<Fornecedor> findAll() {

        return fornecedorRepository.findAll(); // Retorna todos os produtos do repositório

    }

    // Método para buscar um produto pelo id
    public Fornecedor findById(Integer id) {

        return fornecedorRepository.findById(id).orElse(null); // Retorna o produto com o id especificado ou null se não
                                                               // existir

    }

    // Método para excluir um produto pelo id
    public void deleteById(Integer id) {

        fornecedorRepository.deleteById(id); // Exclui o produto com o id especificado do repositório
    }
}
