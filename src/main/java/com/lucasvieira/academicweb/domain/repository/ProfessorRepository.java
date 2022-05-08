package com.lucasvieira.academicweb.domain.repository;

import com.lucasvieira.academicweb.domain.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    @Query("SELECT SUM(salario) FROM Professor")
    BigDecimal totalSalarios();
}
