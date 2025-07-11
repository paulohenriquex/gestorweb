package br.com.gestorweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.gestorweb.model.Produto;
import br.com.gestorweb.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseEntity<Produto> save(Produto produto) {
        return ResponseEntity.ok(produtoRepository.save(produto));
    }

    public ResponseEntity<List<Produto>> findAll() {
        return ResponseEntity.ok(produtoRepository.findAll());
    }
}
