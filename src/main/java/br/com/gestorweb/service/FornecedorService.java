package br.com.gestorweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestorweb.model.Fornecedor;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.FornecedorRepository;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> listarFornecedores(Usuario usuario) {
        return fornecedorRepository.findByUsuario(usuario);
    }

    public Fornecedor salvarFornecedor(Fornecedor fornecedor, Usuario usuario) {
        fornecedorRepository.findByCnpj(fornecedor.getCnpj()).ifPresent(forn -> {
            if (!forn.getId().equals(fornecedor.getId())) {
                throw new RuntimeException("Erro: CNPJ já cadastrado");
            }
        });
        fornecedor.setUsuario(usuario);
        return fornecedorRepository.save(fornecedor);
    }

    public void deletarFornecedor(Long id, Usuario usuario) {
        Fornecedor fornecedor = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
        if (!fornecedor.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("Operacao não permitida, fornecedor não pertence ao usuário.");
        }
        fornecedorRepository.delete(fornecedor);
    }

}
