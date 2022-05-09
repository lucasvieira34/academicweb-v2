package com.lucasvieira.academicweb.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "aluno")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "id_turma")
    private Turma turma;

    @OneToMany(mappedBy = "aluno")
    Set<AlunoDisciplina> extratos;
}
