package com.lucasvieira.academicweb.domain.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class AlunoDisciplinaPK implements Serializable {

    @Column(name = "id_aluno")
    Long idAluno;

    @Column(name = "id_disciplina")
    Long idDisciplina;

    public AlunoDisciplinaPK() {
        super();
    }

    public AlunoDisciplinaPK(Long idAluno, Long idDisciplina) {
        super();
        this.idAluno = idAluno;
        this.idDisciplina = idDisciplina;
    }
}
