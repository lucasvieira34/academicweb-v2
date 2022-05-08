package com.lucasvieira.academicweb.application.service;

import com.lucasvieira.academicweb.domain.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;

    public Integer quantidadeAlunos() {
        return Math.toIntExact(repository.count());
    }

    public BigDecimal totalMensalidades() {
        return repository.totalMensalidades();
    }
}
