package br.com.gestorweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByUsuarioId(Long id);

}
