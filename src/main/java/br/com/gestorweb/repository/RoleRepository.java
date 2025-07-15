package br.com.gestorweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestorweb.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}
