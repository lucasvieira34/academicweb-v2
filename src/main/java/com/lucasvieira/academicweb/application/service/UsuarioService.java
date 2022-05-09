package com.lucasvieira.academicweb.application.service;

import com.lucasvieira.academicweb.domain.entity.Usuario;
import com.lucasvieira.academicweb.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public Usuario findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public Usuario findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
