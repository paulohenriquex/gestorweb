package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

}
