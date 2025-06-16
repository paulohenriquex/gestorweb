package br.project.gestorweb.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import br.project.gestorweb.auth.entity.Usuario;
import br.project.gestorweb.auth.service.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UsuarioService usuarioService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> registerUser(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);
            Usuario responseUser = new Usuario();
            responseUser.setId(novoUsuario.getId());
            responseUser.setUsername(novoUsuario.getUsername());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody Usuario loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok("Login bem-sucedido!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
        }
    }
}
