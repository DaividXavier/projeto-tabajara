package br.com.projetotabajara.tabajara.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPedido;

    private LocalDate dataPedido;
    private double totalPedido;
    @ManyToOne
    @JoinColumn(name = "idUsuario_fk")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;
    
    //metodo para calcular o total da compra
    public double calcularTotal() {
         double total = 0;
        if (itens != null) {
            for (ItemPedido item : itens) {
                total += item.getSubtotal();
            }
        }
        return total;
    }
    //atualizar o total do pedido
    public void atualizarTotal() {
        this.totalPedido = calcularTotal();
    }
}
