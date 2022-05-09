package com.lucasvieira.academicweb.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "disciplina")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;

    @OneToMany(mappedBy = "disciplina")
    Set<AlunoDisciplina> extratos;

    @ManyToMany
    @JoinTable(name = "ProfessoresDisciplinas", uniqueConstraints = @UniqueConstraint(columnNames = { "id_professor", "id_disciplina" }),
    joinColumns = @JoinColumn(name = "id_disciplina"),
    inverseJoinColumns = @JoinColumn(name = "id_professor"))
    private List<Professor> professores;
}
