package br.com.gestorweb.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestorweb.dto.FornecedoresResponseDTO;
import br.com.gestorweb.model.Fornecedor;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.service.FornecedorService;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    public ResponseEntity<FornecedoresResponseDTO> cadastrarFornecedor(@RequestBody Fornecedor fornecedor,
            @AuthenticationPrincipal Usuario usuario) {
        fornecedor.setUsuario(usuario);
        Fornecedor novoFornecedor = fornecedorService.save(fornecedor);
        return ResponseEntity.ok(new FornecedoresResponseDTO(novoFornecedor));
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listarFornecedores(@AuthenticationPrincipal Usuario usuario) {
        List<Fornecedor> fornecedores = fornecedorService.findByUsuarioId(usuario.getId());
        return ResponseEntity.ok(fornecedores);
    }

}
