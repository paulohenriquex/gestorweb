package br.com.gestorweb.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestorweb.model.Categoria;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.UsuarioRepository;
import br.com.gestorweb.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuarioLogado(Principal principal) {
        String email = principal.getName();
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias(Principal principal) {
        Usuario usuario = getUsuarioLogado(principal);
        List<Categoria> categorias = categoriaService.listarCategoriasDoUsuario(usuario);
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria, Principal principal) {
        Usuario usuario = getUsuarioLogado(principal);
        Categoria novaCategoria = categoriaService.salvarCategoria(categoria, usuario);
        return ResponseEntity.ok(novaCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoria,
            Principal principal) {
        Usuario usuario = getUsuarioLogado(principal);
        categoria.setId(id);
        Categoria categoriaAtualizada = categoriaService.salvarCategoria(categoria, usuario);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id, Principal principal) {
        Usuario usuario = getUsuarioLogado(principal);
        categoriaService.deletarCategoria(id, usuario);
        return ResponseEntity.noContent().build();
    }
}
