package br.com.gestorweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestorweb.model.Categoria;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategoriasDoUsuario(Usuario usuario) {
        return categoriaRepository.findByUsuario(usuario);
    }

    public Categoria salvarCategoria(Categoria categoria, Usuario usuario) {
        categoria.setUsuario(usuario);
        return categoriaRepository.save(categoria);
    }

    public void deletarCategoria(Long id, Usuario usuario) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (!categoria.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("Operacao não permitida, categoria não pertence ao usuário.");
        }
        categoriaRepository.delete(categoria);
    }

}
