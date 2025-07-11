package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestorweb.model.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    // TODO
}
