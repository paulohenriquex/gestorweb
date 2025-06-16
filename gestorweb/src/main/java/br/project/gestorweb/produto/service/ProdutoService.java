package br.project.gestorweb.produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.project.gestorweb.auth.entity.Usuario;
import br.project.gestorweb.produto.entity.Produto;
import br.project.gestorweb.produto.repository.ProdutoRepository;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);

    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public List<Produto> listarPorUsuario(Usuario usuario) {
        return produtoRepository.findByUsuario(usuario);
    }

}
