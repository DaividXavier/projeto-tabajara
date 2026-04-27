package br.com.projetotabajara.tabajara.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetotabajara.tabajara.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
    
}
