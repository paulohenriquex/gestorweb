package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

}
