package br.com.gestorweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestorweb.model.Marca;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.MarcaRepository;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> listarMarcasDoUsuario(Usuario usuario) {
        return marcaRepository.findByUsuario(usuario);
    }

    public Marca saveMarca(Marca marca, Usuario usuario) {
        marca.setUsuario(usuario);
        return marcaRepository.save(marca);
    }

    public void deletarMarca(Long id, Usuario usuario) {
        Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca não encontrada com o ID: " + id));

        if (!marca.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("Operacao não permitida, marca não pertence ao usuário.");
        }
        marcaRepository.delete(marca);
    }
}
