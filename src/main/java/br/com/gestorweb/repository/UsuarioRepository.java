package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestorweb.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
