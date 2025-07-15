package br.com.gestorweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    List<Fornecedor> findByUsuarioId(Long id);

}
