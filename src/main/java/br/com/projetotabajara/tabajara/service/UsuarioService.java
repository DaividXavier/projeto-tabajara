package br.com.projetotabajara.tabajara.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.projetotabajara.tabajara.entity.Usuario;
import br.com.projetotabajara.tabajara.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario save(Usuario usuario) {
        usuario.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public void excluir(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario findAll(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public String gerarTokenRecuperacao(String email) {
        Usuario usuario = usuarioRepository.findByEmailUsuario(email).orElse(null);
        if (usuario == null) {
            return null;
        }
        String token = UUID.randomUUID().toString();
        usuario.setResetToken(token);
        usuario.setTokenExpiracao(LocalDateTime.now().plusMinutes(30));
        usuarioRepository.save(usuario);
        return token;
    }

    public boolean redefinirSenha(String token, String novaSenha){
        Usuario usuario = usuarioRepository.findByResetToken(token).orElse(null);
        if (usuario == null || usuario.getTokenExpiracao().isBefore(LocalDateTime.now())) {
            return false;
        }
        usuario.setSenhaUsuario(passwordEncoder.encode(novaSenha));
        usuario.setResetToken(null);
        usuario.setTokenExpiracao(null);
        usuarioRepository.save(usuario);
        return true;
    }
}