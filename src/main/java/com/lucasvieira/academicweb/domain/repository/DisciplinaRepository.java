package com.lucasvieira.academicweb.domain.repository;

import com.lucasvieira.academicweb.domain.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
