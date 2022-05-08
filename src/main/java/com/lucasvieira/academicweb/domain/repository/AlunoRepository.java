package com.lucasvieira.academicweb.domain.repository;

import com.lucasvieira.academicweb.domain.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Query("SELECT SUM(valorMensalidade) from Aluno")
    BigDecimal totalMensalidades();
}
