package br.com.gestorweb.dto;

import br.com.gestorweb.model.UserRole;

public record RegisterDTO(String login, String senha, UserRole role) {

}
