package br.com.gestorweb.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role implements GrantedAuthority {

    @Id
    private String nome;

    @Override
    public String getAuthority() {
        return this.nome;
    }

    // Getters e Setters (necessários para JPA)
    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

}
