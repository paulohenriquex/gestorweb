package br.com.gestorweb.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestorweb.model.Marca;
import br.com.gestorweb.repository.MarcaRepository;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    private final MarcaRepository marcaRepository;

    public MarcaController(MarcaRepository marcaRepository) {
        this.marcaRepository = marcaRepository;
    }

    @PostMapping
    public ResponseEntity<Marca> cadastrarMarca(@PathVariable Marca marca) {
        return ResponseEntity.ok(marcaRepository.save(marca));
    }

    @GetMapping
    public ResponseEntity<List<Marca>> listarMarcas() {
        return ResponseEntity.ok(marcaRepository.findAll());
    }
}
