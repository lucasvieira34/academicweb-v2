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
    @JoinTable(name = "ProfessoresTurmas", uniqueConstraints = @UniqueConstraint(columnNames = { "id_professor", "id_turma" }),
    joinColumns = @JoinColumn(name = "id_turma"),
    inverseJoinColumns = @JoinColumn(name = "id_professor"))
    private List<Professor> professores;

    @OneToMany(mappedBy = "turma")
    private List<Aluno> alunos;

    //IMPLEMENTAR TURMA - PROFESSOR
    // ENRICHMENT - TURMA
    // ITEMS - ALUNOS
}
