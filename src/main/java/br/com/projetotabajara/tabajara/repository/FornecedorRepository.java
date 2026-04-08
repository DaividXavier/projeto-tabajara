package br.com.projetotabajara.tabajara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetotabajara.tabajara.entity.Fornecedor;

@Repository
public interface FornecedorRepository extends JpaRepository <Fornecedor, Integer> {
    
}
