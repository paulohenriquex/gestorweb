package br.project.gestorweb.planejamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.project.gestorweb.auth.entity.Usuario;
import br.project.gestorweb.planejamento.entity.Planejamento;
import br.project.gestorweb.planejamento.repository.PlanejamentoRepository;

@Service
public class PlanejamentoService {
    private final PlanejamentoRepository planejamentoRepository;

    public PlanejamentoService(PlanejamentoRepository planejamentoRepository) {
        this.planejamentoRepository = planejamentoRepository;
    }

    public List<Planejamento> listarPlanejamentos(Usuario usuario) {
        return planejamentoRepository.findByUsuario(usuario);
    }

    public Optional<Planejamento> buscarPlanejamentoPorId(Long id) {
        return planejamentoRepository.findById(id);
    }

    public Planejamento salvarPlanejamento(Planejamento planejamento) {
        return planejamentoRepository.save(planejamento);
    }

    public void deletarPlanejamento(Long id) {
        planejamentoRepository.deleteById(id);
    }

}
