package br.project.gestorweb.produto.entity;

import br.project.gestorweb.auth.entity.Usuario;
import jakarta.persistence.Column;
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
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "produto", nullable = false)
    private String nome;

    @Column(name = "preco", nullable = false)
    private Double preco;

    @Column(name = "medida", nullable = false)
    private String medida;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "marca", nullable = true)
    private String marca;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;
}
