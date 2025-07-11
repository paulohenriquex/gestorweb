package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestorweb.model.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {

}
