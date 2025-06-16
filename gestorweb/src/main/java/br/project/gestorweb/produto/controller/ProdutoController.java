package br.project.gestorweb.produto.controller;

import br.project.gestorweb.auth.entity.Usuario; // Importa a entidade Usuario

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import br.project.gestorweb.produto.entity.Produto;
import br.project.gestorweb.produto.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(produtoService.listarPorUsuario(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto,
            @AuthenticationPrincipal Usuario usuario) {
        produto.setUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvarProduto(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends Object> updateProduto(@PathVariable Long id, @RequestBody Produto produto,
            @AuthenticationPrincipal Usuario usuario) {
        return produtoService.buscarProdutoPorId(id)
                .map(existingProduto -> {
                    if (!existingProduto.getUsuario().getId().equals(usuario.getId())) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                    produto.setId(id);
                    produto.setUsuario(usuario);
                    return ResponseEntity.ok(produtoService.salvarProduto(produto));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduto(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        return produtoService.buscarProdutoPorId(id)
                .map(existingProduto -> {
                    if (!existingProduto.getUsuario().getId().equals(usuario.getId())) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                    produtoService.excluirProduto(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}