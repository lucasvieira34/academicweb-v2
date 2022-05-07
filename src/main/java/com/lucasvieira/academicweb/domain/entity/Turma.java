package com.lucasvieira.academicweb.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;

    @ManyToMany
    @JoinTable(name = "AlunosTurmas", uniqueConstraints = @UniqueConstraint(columnNames = { "id_aluno", "id_turma" }),
    joinColumns = @JoinColumn(name = "id_turma"),
    inverseJoinColumns = @JoinColumn(name = "id_aluno"))
    private List<Aluno> alunos;
}
