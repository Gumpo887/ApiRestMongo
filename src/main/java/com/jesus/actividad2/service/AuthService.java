package com.jesus.actividad2.service;

import com.jesus.actividad2.dto.AuthLoginRequest;
import com.jesus.actividad2.dto.AuthRegisterRequest;
import com.jesus.actividad2.dto.AuthResponse;
import com.jesus.actividad2.exception.BadRequestException;
import com.jesus.actividad2.model.Usuario;
import com.jesus.actividad2.repository.UsuarioRepository;
import com.jesus.actividad2.security.AppUserDetails;
import com.jesus.actividad2.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public AuthResponse register(AuthRegisterRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("El email ya está registrado");
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(request.getRol());
        usuarioRepository.save(usuario);
        String token = jwtService.generateToken(new AppUserDetails(usuario));
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthLoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        AppUserDetails userDetails = (AppUserDetails) auth.getPrincipal();
        return new AuthResponse(jwtService.generateToken(userDetails));
    }
}
