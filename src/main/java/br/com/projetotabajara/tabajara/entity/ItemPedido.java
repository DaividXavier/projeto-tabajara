package br.com.projetotabajara.tabajara.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ItemPedido {
    
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer idItem;

private Integer quantidade;
private double preco;  
private double subtotal;
@ManyToOne
@JoinColumn(name = "idProduto_fk")
private Produto produto;

@ManyToOne
@JoinColumn(name = "idPedido_fk")
private Pedido pedido;

//calcular subtotal
public double calcularSubtotal() {
    return quantidade * preco;
}

//atualizar subtotal
public void atualizarSubtotal() {
    this.subtotal = calcularSubtotal();


}
}