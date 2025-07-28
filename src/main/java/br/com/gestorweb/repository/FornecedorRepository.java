package br.com.gestorweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestorweb.model.Fornecedor;
import br.com.gestorweb.model.Usuario;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    List<Fornecedor> findByUsuario(Usuario usuario);

    Optional<Fornecedor> findByCnpj(String cnpj);
}
