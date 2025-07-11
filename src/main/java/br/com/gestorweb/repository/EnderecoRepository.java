package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}
