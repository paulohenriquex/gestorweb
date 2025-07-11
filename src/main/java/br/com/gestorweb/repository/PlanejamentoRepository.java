package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestorweb.model.Planejamento;

@Repository
public interface PlanejamentoRepository extends JpaRepository<Planejamento, Long> {
    // TODO
}
