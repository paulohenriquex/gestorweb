package br.project.gestorweb.receita.entity;

import java.util.List;
import lombok.EqualsAndHashCode; // <--- Importe esta anotação!
import com.fasterxml.jackson.annotation.JsonManagedReference;

import br.project.gestorweb.auth.entity.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "receitas")
@EqualsAndHashCode(callSuper = false)
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String modoDePreparo;

    @OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    private List<IngredienteReceita> ingredientes;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    @EqualsAndHashCode.Exclude
    private Usuario usuario;
}
