package br.project.gestorweb.receita.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.project.gestorweb.receita.entity.IngredienteReceita;

public interface IngredienteReceitaRepository extends JpaRepository<IngredienteReceita, Long> {

}
