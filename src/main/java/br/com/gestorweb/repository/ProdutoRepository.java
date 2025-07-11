package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestorweb.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
