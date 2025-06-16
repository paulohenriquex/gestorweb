package br.project.gestorweb.planejamento.controller;

import br.project.gestorweb.planejamento.entity.Planejamento; // Importa a entidade Planejamento
import br.project.gestorweb.auth.entity.Usuario; // Importa a entidade Usuario
import br.project.gestorweb.planejamento.service.PlanejamentoService; // Importa o serviço PlanejamentoService
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planejamentos")
public class PlanejamentoController {

    private final PlanejamentoService planejamentoService;

    public PlanejamentoController(PlanejamentoService planejamentoService) {
        this.planejamentoService = planejamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Planejamento>> getAllPlanejamentos(@AuthenticationPrincipal Usuario usuario) {
        return ResponseEntity.ok(planejamentoService.listarPlanejamentos(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planejamento> getPlanejamentoById(@PathVariable Long id) {
        return planejamentoService.buscarPlanejamentoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Planejamento> createPlanejamento(@RequestBody Planejamento planejamento,
            @AuthenticationPrincipal Usuario usuario) {
        planejamento.setUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(planejamentoService.salvarPlanejamento(planejamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends Object> updatePlanejamento(@PathVariable Long id,
            @RequestBody Planejamento planejamento, @AuthenticationPrincipal Usuario usuario) {
        return planejamentoService.buscarPlanejamentoPorId(id)
                .map(existingPlanejamento -> {
                    if (!existingPlanejamento.getUsuario().getId().equals(usuario.getId())) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                    planejamento.setId(id);
                    planejamento.setUsuario(usuario);
                    return ResponseEntity.ok(planejamentoService.salvarPlanejamento(planejamento));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlanejamento(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {
        return planejamentoService.buscarPlanejamentoPorId(id)
                .map(existingPlanejamento -> {
                    if (!existingPlanejamento.getUsuario().getId().equals(usuario.getId())) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                    }
                    planejamentoService.deletarPlanejamento(id);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}