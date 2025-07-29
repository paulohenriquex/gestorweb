package br.com.gestorweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Importar PasswordEncoder
import org.springframework.stereotype.Service;

import br.com.gestorweb.exception.BusinessException;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Injetar o PasswordEncoder

    /**
     * Cria um novo usuário, codificando a senha antes de salvar.
     * 
     * @param usuario O objeto Usuario a ser criado.
     * @return O objeto Usuario salvo.
     * @throws BusinessException Se o email já estiver em uso.
     */
    public Usuario criarUsuario(Usuario usuario) {
        // Verifica se o email já está em uso para evitar duplicidade
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new BusinessException("O email já está em uso");
        }
        // Codifica a senha do usuário antes de salvá-la no banco de dados
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    /**
     * Autentica um usuário, comparando a senha fornecida com a senha codificada no
     * banco de dados.
     * NOTA: Para um sistema de autenticação completo com Spring Security, este
     * método de login
     * geralmente não é chamado diretamente. O Spring Security lida com a
     * autenticação através
     * de seu próprio fluxo (ex: formulário de login, HTTP Basic, JWT).
     * No entanto, se você tem um fluxo de login customizado, esta é a forma correta
     * de comparar senhas.
     *
     * @param usuario O objeto Usuario com email e senha para login.
     * @return O objeto Usuario encontrado e autenticado.
     * @throws RuntimeException Se o usuário ou senha forem inválidos.
     */
    public Usuario loginUsuario(Usuario usuario) {
        // Busca o usuário pelo email
        Usuario encontrado = usuarioRepository.findByEmail(usuario.getEmail())
                .orElse(null);

        // Verifica se o usuário foi encontrado e se a senha fornecida (em texto puro)
        // corresponde à senha codificada armazenada no banco de dados.
        // O método 'matches' do PasswordEncoder é usado para essa comparação segura.
        if (encontrado != null && passwordEncoder.matches(usuario.getPassword(), encontrado.getPassword())) {
            return encontrado;
        }

        throw new RuntimeException("Usuário ou senha inválidos");
    }
}
