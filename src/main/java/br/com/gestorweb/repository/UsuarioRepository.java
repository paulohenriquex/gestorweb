package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
