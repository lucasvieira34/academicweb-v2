package com.lucasvieira.academicweb.application.service;

import com.lucasvieira.academicweb.domain.repository.DisciplinaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DisciplinaService {

    private final DisciplinaRepository repository;

    public Integer quantidadeDisciplinas() {
        return Math.toIntExact(repository.count());
    }
}
