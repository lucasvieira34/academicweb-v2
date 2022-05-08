package com.lucasvieira.academicweb.application.service;

import com.lucasvieira.academicweb.domain.entity.Usuario;
import com.lucasvieira.academicweb.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecretariaService {

    private final UsuarioRepository usuarioRepository;
    private Usuario usuarioLogado;

    private void usuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String login = autenticado.getName();
            usuarioLogado = usuarioRepository.findByUsername(login);
        }
    }
}
