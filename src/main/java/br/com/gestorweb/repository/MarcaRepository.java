package br.com.gestorweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gestorweb.model.Marca;
import br.com.gestorweb.model.Usuario;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByUsuario(Usuario usuario);
}
