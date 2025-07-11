package br.com.gestorweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.gestorweb.model.Categoria;
import br.com.gestorweb.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public ResponseEntity<Categoria> save(Categoria categoria) {
        return ResponseEntity.ok(categoriaRepository.save(categoria));
    }

    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }
}
