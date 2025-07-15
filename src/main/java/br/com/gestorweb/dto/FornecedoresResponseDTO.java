package br.com.gestorweb.dto;

import br.com.gestorweb.model.Endereco;
import br.com.gestorweb.model.Fornecedor;

public record FornecedoresResponseDTO(Long id, String nomeFantasia, String cnpj, String email, Endereco endereco) {

    public FornecedoresResponseDTO(Fornecedor fornecedor) {
        this(fornecedor.getId(), fornecedor.getNomeFantasia(), fornecedor.getCnpj(), fornecedor.getEmail(),
                fornecedor.getEndereco());
    }
}
