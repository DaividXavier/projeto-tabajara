package br.com.projetotabajara.tabajara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetotabajara.tabajara.entity.Produto;
// métodos básicos estão prontos na jpa repository
//extends é herança
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
