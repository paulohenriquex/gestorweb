package br.project.gestorweb.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.project.gestorweb.auth.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
