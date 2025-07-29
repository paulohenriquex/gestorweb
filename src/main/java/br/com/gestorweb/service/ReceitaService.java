package br.com.gestorweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gestorweb.model.Receita;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.ReceitaRepository;

@Service
public class ReceitaService {
    @Autowired
    private ReceitaRepository receitaRepository;

    public List<Receita> listarReceitasDoUsuario(Usuario usuario) {
        return receitaRepository.findByUsuario(usuario);
    }

    public Receita salvarReceita(Receita receita, Usuario usuario) {
        receita.getIngredientes().forEach(ingrediente -> ingrediente.setReceita(receita));
        receita.setUsuario(usuario);
        return receitaRepository.save(receita);
    }

    public void deletarReceita(Long id, Usuario usuario) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));
        if (!receita.getUsuario().getId().equals(usuario.getId())) {
            throw new RuntimeException("Operacao não permitida, receita não pertence ao usuário.");
        }
        receitaRepository.delete(receita);
    }
}
