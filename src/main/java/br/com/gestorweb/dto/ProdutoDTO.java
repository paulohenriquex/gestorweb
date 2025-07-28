package br.com.gestorweb.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private String medida;
    private String nomeCategoria;
    private String nomeMarca;
}
