package br.com.gestorweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestorweb.model.Endereco;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.service.EnderecoService;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;;

    @PostMapping
    public ResponseEntity<Endereco> cadastrarEndereco(@RequestBody Endereco endereco,
            @AuthenticationPrincipal Usuario usuario) {
        endereco.setUsuario(usuario);
        return ResponseEntity.ok(enderecoService.save(endereco));
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarEndereco() {
        return ResponseEntity.ok(enderecoService.findAll());
    }
}
