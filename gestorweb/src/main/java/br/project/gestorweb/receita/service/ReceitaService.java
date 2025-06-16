package br.project.gestorweb.receita.service;

import br.project.gestorweb.receita.entity.Receita; // Importe a entidade Receita
import br.project.gestorweb.auth.entity.Usuario; // Importe a entidade Usuario
import br.project.gestorweb.receita.repository.ReceitaRepository; // Importe o repositório ReceitaRepository
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @Transactional(readOnly = true) // <--- CRUCIAL para listar e evitar LazyInitializationException
    public List<Receita> listarReceitas(Usuario usuario) {
        return receitaRepository.findByUsuario(usuario);
    }

    @Transactional(readOnly = true) // <--- CRUCIAL para buscar por ID e evitar LazyInitializationException
    public Optional<Receita> buscarReceitaPorId(Long id) {
        // Ao usar findById, o Spring Data JPA pode retornar um proxy.
        // O @Transactional garante que o acesso a coleções lazy-loaded aconteça dentro
        // da sessão.
        return receitaRepository.findById(id);
    }

    @Transactional // <--- Para operações de escrita (salvar/modificar)
    public Receita salvarReceita(Receita receita) {
        if (receita.getIngredientes() != null) {
            // Garante que a receita seja definida para os ingredientes antes de salvar.
            // O CascadeType.ALL e orphanRemoval=true em Receita garantem que ingredientes
            // sejam persistidos/atualizados/removidos junto com a receita.
            receita.getIngredientes().forEach(ingrediente -> {
                ingrediente.setReceita(receita);
                // N.B.: Se o 'produto' no IngredienteReceita fosse apenas um ID no JSON
                // e não um objeto completo, você precisaria buscar o produto no banco
                // para anexá-lo à sessão antes de salvar o IngredienteReceita.
                // Mas, como estamos mandando o objeto produto com ID, o JPA geralmente resolve.
            });
        }
        return receitaRepository.save(receita);
    }

    @Transactional // <--- Para operações de escrita (deletar)
    public void excluirReceita(Long id) {
        receitaRepository.deleteById(id);
    }
}
