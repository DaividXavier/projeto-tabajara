package br.com.projetotabajara.tabajara.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Fornecedor {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer idFornecedor;

@Column (nullable = false, length = 50)
private String nomeFornecedor;

@Column (nullable = false, length = 25)
private String cnpjFornecedor;

@Column (nullable = false, length = 11)
private String  telefoneFornecedor;

@Column (nullable = false, length = 50)
private String enderecoFornecedor;

@Column (nullable = false, length = 20)
private String cidadeFornecedor;
}
