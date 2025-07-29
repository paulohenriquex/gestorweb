package br.com.gestorweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestorweb.model.Produto;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarProdutosDoUsuario(Usuario usuario) {
        return produtoRepository.findByUsuario(usuario);
    }

    public Produto salvarProduto(Produto produto, Usuario usuario) {
        produto.setUsuario(usuario);
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Long id, Usuario usuario) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        if (!produto.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("Operacao não permitida, produto não pertence ao usuário.");
        }
        produtoRepository.delete(produto);
    }
}
