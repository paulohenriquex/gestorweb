package br.com.gestorweb.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestorweb.model.Marca;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.service.MarcaService;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

    private final MarcaService marcaService;

    public MarcaController(MarcaService marcaService) {
        this.marcaService = marcaService;
    }

    @PostMapping
    public ResponseEntity<Marca> cadastrarMarca(@RequestBody Marca marca, @AuthenticationPrincipal Usuario usuario) {
        marca.setUsuario(usuario);
        return marcaService.save(marca);
    }

    @GetMapping
    public ResponseEntity<List<Marca>> listarMarcas() {
        return marcaService.findAll();
    }
}
