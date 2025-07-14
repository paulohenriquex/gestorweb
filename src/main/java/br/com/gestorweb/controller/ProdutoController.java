package br.com.gestorweb.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestorweb.model.Produto;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto,
            @AuthenticationPrincipal Usuario usuario) {
        produto.setUsuario(usuario);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutosDoUsuario(@AuthenticationPrincipal Usuario usuario) {
        List<Produto> produtos = produtoService.findByUsuarioId(usuario.getId());
        return ResponseEntity.ok(produtos);
    }

}
