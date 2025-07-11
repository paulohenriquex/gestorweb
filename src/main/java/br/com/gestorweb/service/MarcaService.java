package br.com.gestorweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.gestorweb.model.Marca;
import br.com.gestorweb.repository.MarcaRepository;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public ResponseEntity<Marca> save(Marca marca) {
        return ResponseEntity.ok(marcaRepository.save(marca));
    }

    public ResponseEntity<List<Marca>> findAll() {
        return ResponseEntity.ok(marcaRepository.findAll());
    }
}
