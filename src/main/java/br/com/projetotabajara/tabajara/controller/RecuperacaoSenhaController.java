package br.com.projetotabajara.tabajara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.projetotabajara.tabajara.service.UsuarioService;

@Controller
public class RecuperacaoSenhaController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/esqueci-senha")
    public String  EsqueciSenha() {
        return "esqueci-senha";
    }

    @PostMapping("/esqueci-senha")
    public String processarEsqueciSenha(@RequestParam String email, Model model) {
        String token = usuarioService.gerarTokenRecuperacao(email);
        if (token == null) {
            model.addAttribute("mensagem", "Email não encontrado.");
            return "esqueci-senha";
        } 
            String link = "http://localhost:8080/redefinir-senha?token=" + token;
            model.addAttribute("mensagem", "Um link de recuperação (simulação): " + link);
        return "esqueci-senha";
    }

    @GetMapping("/redefinir-senha")
    public String redefinirSenha(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "redefinir-senha";
    }

    @PostMapping("/redefinir-senha")
    public String salvarNovaSenha(@RequestParam String token, @RequestParam String novaSenha, @RequestParam String confirmarSenha, Model model) {
        if (!novaSenha.equals(confirmarSenha)) {
            model.addAttribute("erro", "As senhas não coincidem.");
            model.addAttribute("token", token);
            return "redefinir-senha";
        }
        boolean sucesso = usuarioService.redefinirSenha(token, novaSenha);
        if (sucesso) {
            model.addAttribute("mensagem", "Senha redefinida com sucesso!");
            return "redirect:/login?resetSucesso";
        } else {
            model.addAttribute("erro", "Erro ao redefinir senha. Token inválido ou expirado.");
            model.addAttribute("token", token);
            return "redefinir-senha";
        }
    }
}
