package com.lucasvieira.academicweb.infrastructure.security;

import com.lucasvieira.academicweb.application.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoginService loginService;
    private final SuccessLoginHandler successLoginHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/",
                            "/materialize/**",
                            "/css/**",
                            "/fonts/**",
                            "/img/**",
                            "/js/**",
                            "/vendor/**",
                            "/resources/**").permitAll()
                .antMatchers("/secretaria/**").hasRole("SECRETARIA")
                    .anyRequest().authenticated()
                .and()
                    .formLogin().permitAll().loginPage("/login").successHandler(successLoginHandler)
                .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
