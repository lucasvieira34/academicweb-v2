package com.lucasvieira.academicweb.domain.repository;

import com.lucasvieira.academicweb.domain.entity.Aluno;
import com.lucasvieira.academicweb.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
