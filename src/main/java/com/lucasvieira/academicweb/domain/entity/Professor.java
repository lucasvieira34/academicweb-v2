package com.lucasvieira.academicweb.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricula;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private BigDecimal salario;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", unique = true)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(name = "ProfessoresDisciplinas", uniqueConstraints = @UniqueConstraint(columnNames = { "id_professor", "id_disciplina" }),
    joinColumns = @JoinColumn(name = "id_professor"),
    inverseJoinColumns = @JoinColumn(name = "id_disciplina"))
    private List<Disciplina> disciplinas;
}
