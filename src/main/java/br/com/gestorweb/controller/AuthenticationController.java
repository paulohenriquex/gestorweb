package br.com.gestorweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gestorweb.dto.AuthenticationDTO;
import br.com.gestorweb.dto.LoginResponseDTO;
import br.com.gestorweb.dto.RegisterDTO;
import br.com.gestorweb.model.Usuario;
import br.com.gestorweb.repository.RoleRepository;
import br.com.gestorweb.repository.UsuarioRepository;
import br.com.gestorweb.service.TokenService;
import br.com.gestorweb.model.Role;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO data) {
        if (this.usuarioRepository.findByLogin(data.login()) != null) {
            return ResponseEntity.badRequest().body("Este login já está em uso.");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());

        // ---- Início da Lógica Corrigida ----
        Usuario newUser = new Usuario(); // 1. Cria um objeto Usuario vazio
        newUser.setLogin(data.login());
        newUser.setPassword(encryptedPassword);
        newUser.setUsername(data.login()); // Pode definir o username como o login ou outro valor

        // 2. Busca o perfil (Role) na base de dados
        Role userRole = roleRepository.findById(data.role().name())
                .orElseThrow(() -> new RuntimeException("Erro: Perfil (Role) não encontrado."));

        // 3. Define a lista de perfis do novo utilizador
        newUser.setRoles(List.of(userRole));
        // ---- Fim da Lógica Corrigida ----

        this.usuarioRepository.save(newUser);

        return ResponseEntity.ok().build();
    }
}