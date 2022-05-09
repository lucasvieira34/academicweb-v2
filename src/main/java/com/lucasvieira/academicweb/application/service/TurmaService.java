package com.lucasvieira.academicweb.application.service;

import com.lucasvieira.academicweb.domain.entity.Turma;
import com.lucasvieira.academicweb.domain.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository repository;

    public List<Turma> findAllTurmas() {
        return repository.findAll();
    }
}
