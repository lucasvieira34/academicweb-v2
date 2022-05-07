package com.lucasvieira.academicweb.application.service;

import com.lucasvieira.academicweb.domain.entity.Usuario;
import com.lucasvieira.academicweb.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.findByUsername(username);

        if (Objects.isNull(user)) {
            log.warn("Usuário não encontrado na base de dados ----> {}", username);
            throw new UsernameNotFoundException("O usuário não foi encontrado.");
        }

        return new User(user.getUsername(),
                        user.getPassword(),
                        user.isEnabled(),
                true,
                true,
                true,
                user.getAuthorities());
    }
}
