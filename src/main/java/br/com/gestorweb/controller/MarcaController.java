package br.com.gestorweb.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestorweb.model.Marca;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.UsuarioRepository;
import br.com.gestorweb.service.MarcaService;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Método para obter o usuário logado, com tratamento para Principal nulo
    public Usuario getUsuarioLogado(Principal principal) {
        // Verifica se o Principal é nulo. Se for, significa que não há usuário
        // autenticado.
        if (principal == null) {
            // Lança uma exceção de acesso negado, indicando que a autenticação é
            // necessária.
            throw new AccessDeniedException("Acesso negado: Usuário não autenticado.");
        }

        // Obtém o nome (geralmente o email ou username) do Principal
        String email = principal.getName();

        // Busca o usuário no repositório. Usa orElseThrow para lançar uma exceção
        // caso o usuário não seja encontrado (o que também indica um problema de dados
        // ou autenticação).
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário logado não encontrado no banco de dados: " + email));
    }

    @GetMapping
    public ResponseEntity<List<Marca>> listarMarcas(Principal principal) {
        Usuario usuario = getUsuarioLogado(principal);
        List<Marca> marcas = marcaService.listarMarcasDoUsuario(usuario);
        return ResponseEntity.ok(marcas);
    }

    @PostMapping
    public ResponseEntity<Marca> criarMarca(@RequestBody Marca marca, Principal principal) {
        Usuario usuario = getUsuarioLogado(principal);
        Marca novaMarca = marcaService.saveMarca(marca, usuario);
        return ResponseEntity.ok(novaMarca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizarMarca(@PathVariable Long id, @RequestBody Marca marca, Principal principal) {
        Usuario usuario = getUsuarioLogado(principal);
        marca.setId(id);
        Marca marcaAtualizada = marcaService.saveMarca(marca, usuario);
        return ResponseEntity.ok(marcaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMarca(@PathVariable Long id, Principal principal) {
        Usuario usuario = getUsuarioLogado(principal);
        marcaService.deletarMarca(id, usuario);
        return ResponseEntity.noContent().build();
    }

}
