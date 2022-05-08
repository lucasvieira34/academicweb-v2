package com.lucasvieira.academicweb.application.service;

import com.lucasvieira.academicweb.domain.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;

    public Integer quantidadeProfessores() {
        return Math.toIntExact(repository.count());
    }

    public BigDecimal totalSalarios() {
        return repository.totalSalarios();
    }
}
