package com.lucasvieira.academicweb.domain.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @ManyToMany(mappedBy = "roles")
    private Collection<Usuario> usuarios;

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
