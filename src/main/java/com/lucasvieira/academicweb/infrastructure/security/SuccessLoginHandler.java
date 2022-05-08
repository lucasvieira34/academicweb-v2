package com.lucasvieira.academicweb.infrastructure.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

@Slf4j
@Component
public class SuccessLoginHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = null;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            log.info("ROLE: {}", grantedAuthority.getAuthority());
            if (grantedAuthority.getAuthority().equals("ROLE_SECRETARIA")) {
                redirectUrl = "/secretaria/dashboard";
                break;
            }
            // TODO IMPLEMENTAR REDIRECT DE ALUNO
            // TODO IMPLEMENTAR REDIRECT DE PROFESSOR
        }
        log.info("REDIRECT URL: {}", redirectUrl);
        if (Objects.isNull(redirectUrl)) {
            log.info("Falha ao redirecionar o usuário.");
            throw new IllegalStateException();
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
