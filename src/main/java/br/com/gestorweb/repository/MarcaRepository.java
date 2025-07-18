package br.com.gestorweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByUsuarioId(Long id);

}
