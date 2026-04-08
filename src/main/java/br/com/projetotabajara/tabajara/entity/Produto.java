package br.com.projetotabajara.tabajara.entity;


import jakarta.persistence.Column;
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

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


public class Produto {
    

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // auto cria id aleatórios estratégia do próprio método
    private Integer idProduto;

    @Column (nullable = false, length = 40)
    private String descricaoProduto;

    
    private Double valorProduto;

    @Column(nullable = false, length = 10)
    private Integer unidadeProduto;

    @Column(nullable = false, length = 30)
    private String marcaProduto;

    @ManyToOne
    @JoinColumn(name= "idFornecedor_fk") // deu nome a chave estrangeira
    private Fornecedor fornecedor;


}

// método construtor cheio = armazena tudo, lista depois
// método construtor vazio = armazena espaço na memória para produtos novos.
// set atribui valor a varivel
// get pega o valor no bd