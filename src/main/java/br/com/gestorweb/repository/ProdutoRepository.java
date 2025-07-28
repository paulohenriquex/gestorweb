package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
