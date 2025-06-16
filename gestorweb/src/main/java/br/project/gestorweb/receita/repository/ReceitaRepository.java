package br.project.gestorweb.receita.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.project.gestorweb.auth.entity.Usuario;
import br.project.gestorweb.receita.entity.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByUsuario(Usuario usuario);
}
