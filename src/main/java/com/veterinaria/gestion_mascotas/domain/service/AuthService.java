package com.veterinaria.gestion_mascotas.domain.service;

import com.veterinaria.gestion_mascotas.persistence.crud.VeterinarioCrudRepository;
import com.veterinaria.gestion_mascotas.persistence.entity.Veterinario;
import com.veterinaria.gestion_mascotas.web.dto.LoginRequest;
import com.veterinaria.gestion_mascotas.web.dto.LoginResponse;
import com.veterinaria.gestion_mascotas.web.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AuthService {
    @Autowired
        VeterinarioCrudRepository veterinarioCrudRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest loginRequest) {
        List<Veterinario> veterinarios = veterinarioCrudRepository.findByNumLicencia(loginRequest.getNumLicencia());

        if (veterinarios.isEmpty()) {
            throw new RuntimeException("Veterinario no encontrado");
        }

        Veterinario veterinario = veterinarios.get(0);

        if (!passwordEncoder.matches(loginRequest.getContrasena(), veterinario.getContrasena())) {
            throw new RuntimeException("Credenciales incorrectas");
        }

        String token = jwtUtil.generateToken(veterinario.getNumLicencia());

        return new LoginResponse(token);
    }
}
