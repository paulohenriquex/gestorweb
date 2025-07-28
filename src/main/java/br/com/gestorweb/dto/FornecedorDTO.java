package br.com.gestorweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorDTO {
    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String telefone;
    private String email;
    private EnderecoDTO endereco;
}
