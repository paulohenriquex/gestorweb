package com.gestorweb.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class IngredienteReceita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float percapita;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto ingrediente;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "receita_id", nullable = false)
    private Receita receita;
}
