package br.project.gestorweb.planejamento.entity;

import java.time.LocalDate;
import java.util.List;

import br.project.gestorweb.auth.entity.Usuario;
import br.project.gestorweb.receita.entity.Receita;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
public class Planejamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refeicao;
    private LocalDate data;

    @ManyToMany
    @JoinTable(name = "planejamentoReceita", joinColumns = @JoinColumn(name = "planejamentoId"), inverseJoinColumns = @JoinColumn(name = "receitaId"))
    @EqualsAndHashCode.Exclude // <--- Adicione esta linha
    private List<Receita> receitas;

    private int quantitativo;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    @EqualsAndHashCode.Exclude // <--- Adicione esta linha
    private Usuario usuario;
}
