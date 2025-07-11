package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Planejamento;

public interface PlanejamentoRepository extends JpaRepository<Planejamento, Long> {

}
