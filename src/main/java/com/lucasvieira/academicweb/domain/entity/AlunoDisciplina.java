package com.lucasvieira.academicweb.domain.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class AlunoDisciplina {

    @EmbeddedId
    AlunoDisciplinaPK id;

    @ManyToOne
    @MapsId("id_aluno")
    @JoinColumn(name = "id_aluno")
    Aluno aluno;

    @ManyToOne
    @MapsId("id_disciplina")
    @JoinColumn(name = "id_disciplina")
    Disciplina disciplina;

    private Double a1;
    private Double a2;
    private Integer faltas;

    public AlunoDisciplina() {
        super();
    }

    public AlunoDisciplina(AlunoDisciplinaPK id, Aluno aluno, Disciplina disciplina, Double a1, Double a2, Integer faltas) {
        super();
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
        this.a1 = a1;
        this.a2 = a2;
        this.faltas = faltas;
    }

    public AlunoDisciplina(AlunoDisciplinaPK id, Aluno aluno, Disciplina disciplina) {
        super();
        this.id = id;
        this.aluno = aluno;
        this.disciplina = disciplina;
    }
}
