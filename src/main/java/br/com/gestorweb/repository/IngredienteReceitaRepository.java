package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestorweb.model.IngredienteReceita;

@Repository
public interface IngredienteReceitaRepository extends JpaRepository<IngredienteReceita, Long> {
    // TODO
}
