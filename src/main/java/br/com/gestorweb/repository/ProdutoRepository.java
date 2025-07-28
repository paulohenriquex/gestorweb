package br.com.gestorweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestorweb.model.Produto;
import br.com.gestorweb.model.Usuario;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByUsuario(Usuario usuario);
}
