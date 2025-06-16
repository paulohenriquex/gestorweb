package br.project.gestorweb.produto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.project.gestorweb.auth.entity.Usuario;
import br.project.gestorweb.produto.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByUsuario(Usuario usuario);
}
