package br.project.gestorweb.receita.controller;

import br.project.gestorweb.receita.entity.Receita; // Importa a entidade Receita
import br.project.gestorweb.auth.entity.Usuario; // Importa a entidade Usuario
import br.project.gestorweb.receita.service.ReceitaService; // Importa o serviço ReceitaService
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receitas")
public class ReceitaController {

    private final ReceitaService receitaService;

    public ReceitaController(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping
    public ResponseEntity<List<Receita>> getAllReceitas(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(receitaService.listarReceitas(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> getReceitaById(@PathVariable Long id) {
        return receitaService.buscarReceitaPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Receita> createReceita(@RequestBody Receita receita,
            @AuthenticationPrincipal Usuario usuario) {
        receita.setUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(receitaService.salvarReceita(receita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends Object> updateReceita(@PathVariable Long id, @RequestBody Receita receita,
            @AuthenticationPrincipal Usuario usuario) {
        return receitaService.buscarReceitaPorId(id)
                .map(existingReceita -> {
                    if (!existingReceita.getUsuario().getId().equals(usuario.getId())) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                    receita.setId(id);
                    receita.setUsuario(usuario);
                    return ResponseEntity.ok(receitaService.salvarReceita(receita));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReceita(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        return receitaService.buscarReceitaPorId(id)
                .map(existingReceita -> {
                    if (!existingReceita.getUsuario().getId().equals(usuario.getId())) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                    receitaService.excluirReceita(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}