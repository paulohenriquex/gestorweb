package br.com.gestorweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Planejamento;
import br.com.gestorweb.model.Usuario;

public interface PlanejamentoRepository extends JpaRepository<Planejamento, Long> {
    List<Planejamento> findByUsuario(Usuario usuario);
}
