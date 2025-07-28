package br.com.gestorweb.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String role;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Produto> produtos;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Marca> marcas;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Fornecedor> fornecedores;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Receita> receitas;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Planejamento> planejamentos;
}
