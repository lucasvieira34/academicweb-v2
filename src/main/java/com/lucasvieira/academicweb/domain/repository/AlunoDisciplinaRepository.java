package com.lucasvieira.academicweb.domain.repository;

import com.lucasvieira.academicweb.domain.entity.AlunoDisciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoDisciplinaRepository extends JpaRepository<AlunoDisciplina, Long> {
}
