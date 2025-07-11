package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
