package com.lucasvieira.academicweb.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricula;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private BigDecimal valorMensalidade;
    private String emailResponsavel;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", unique = true)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(name = "AlunosTurmas", uniqueConstraints = @UniqueConstraint(columnNames = { "id_aluno", "id_turma" }),
            joinColumns = @JoinColumn(name = "id_aluno"),
            inverseJoinColumns = @JoinColumn(name = "id_turma"))
    private List<Turma> turmas;

    @OneToMany(mappedBy = "aluno")
    Set<AlunoDisciplina> extratos;
}
