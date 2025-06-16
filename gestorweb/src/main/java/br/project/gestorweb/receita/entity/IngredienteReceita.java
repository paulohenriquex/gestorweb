package br.project.gestorweb.receita.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.project.gestorweb.produto.entity.Produto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ingredientes_receitas")
public class IngredienteReceita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produtoId")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "receitaId")
    @JsonBackReference
    private Receita receita;
}
