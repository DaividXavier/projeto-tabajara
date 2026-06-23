package br.com.projetotabajara.tabajara.repository;

import br.com.projetotabajara.tabajara.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByLoginUsuario(String loginUsuario);
    Optional<Usuario> findByEmailUsuario(String emailUsuario);
    
    Optional<Usuario> findByResetToken(String resetToken);
}