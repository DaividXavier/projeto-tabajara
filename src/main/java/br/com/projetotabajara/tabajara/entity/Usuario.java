package br.com.projetotabajara.tabajara.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Integer idUsuario;

    @Column(nullable = false, length = 100)
    private String nomeUsuario;

    @Column(nullable = false, length = 100)
    private String emailUsuario;

    @Column(nullable = false, length = 40)
    private String loginUsuario;

    // 🔥 CORREÇÃO AQUI
    @Column(nullable = false, length = 100)
    private String senhaUsuario;

    private String role = "ROLE_USER";          

    private String resetToken;

    private LocalDateTime tokenExpiracao;
}