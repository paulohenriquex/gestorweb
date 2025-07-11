package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestorweb.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
