package br.com.gestorweb.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestorweb.model.Produto;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.UsuarioRepository;
import br.com.gestorweb.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuarioLogado(Principal principal) {
        return usuarioRepository.findByEmail(principal.getName()).orElseThrow();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(Principal principal) {
        Usuario usuario = getUsuarioLogado(principal);
        return ResponseEntity.ok(produtoService.listarProdutosDoUsuario(usuario));
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto, Principal principal) {
        Usuario usuario = getUsuarioLogado(principal);
        Produto novoProduto = produtoService.salvarProduto(produto, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }
}
