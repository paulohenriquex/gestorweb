package br.com.gestorweb.service; // Certifique-se de que o pacote está correto

import br.com.gestorweb.model.Usuario; // Importe sua classe Usuario
import br.com.gestorweb.repository.UsuarioRepository; // Importe seu UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User; // Importe a classe User do Spring Security
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Serviço de detalhes do usuário customizado para o Spring Security.
 * Responsável por carregar os detalhes do usuário do banco de dados.
 */
@Service // Marca esta classe como um componente de serviço do Spring
public class CustomUserDetailsService implements UserDetailsService {

    // Injeção de dependência do UsuarioRepository para acessar os dados do usuário
    // no banco de dados
    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carrega os detalhes do usuário pelo nome de usuário (neste caso, o email).
     * Este método é chamado pelo Spring Security durante o processo de
     * autenticação.
     *
     * @param email O email do usuário que está tentando se autenticar.
     * @return Um objeto UserDetails contendo as informações do usuário.
     * @throws UsernameNotFoundException Se o usuário com o email fornecido não for
     *                                   encontrado.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo email.
        // Se o usuário não for encontrado, lança uma UsernameNotFoundException.
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));

        // Constrói e retorna um objeto UserDetails que o Spring Security pode usar.
        // User.withUsername: Define o nome de usuário (email).
        // .password: Define a senha do usuário. É CRÍTICO que esta senha JÁ ESTEJA
        // CODIFICADA
        // no seu banco de dados (ex: usando BCryptPasswordEncoder).
        // .roles: Define os papéis (roles) do usuário. Você pode ter um campo de
        // "roles" no seu
        // modelo Usuario e mapeá-lo aqui. Por simplicidade, "USER" é usado como padrão.
        // Se você tiver múltiplos roles, pode usar .roles("ROLE1", "ROLE2").
        return User.withUsername(usuario.getEmail())
                .password(usuario.getPassword()) // A senha deve ser a senha codificada do banco de dados
                .roles("USER") // Exemplo de role. Adapte conforme sua lógica de negócio.
                .build();
    }
}
