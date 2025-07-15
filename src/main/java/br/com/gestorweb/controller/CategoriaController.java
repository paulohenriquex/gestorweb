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

import br.com.gestorweb.model.Categoria;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.service.CategoriaService;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> cadastrarCateforia(@RequestBody Categoria categoria,
            @AuthenticationPrincipal Usuario usuario) {
        categoria.setUsuario(usuario);
        return categoriaService.save(categoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        return categoriaService.findAll();
    }
}
