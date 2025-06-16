package br.project.gestorweb.planejamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.project.gestorweb.auth.entity.Usuario;
import br.project.gestorweb.planejamento.entity.Planejamento;

public interface PlanejamentoRepository extends JpaRepository<Planejamento, Long> {
    List<Planejamento> findByUsuario(Usuario usuario);
}
