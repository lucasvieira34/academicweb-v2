package com.lucasvieira.academicweb.application.service;

import com.lucasvieira.academicweb.application.model.CadastrarAlunoModel;
import com.lucasvieira.academicweb.domain.entity.*;
import com.lucasvieira.academicweb.domain.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecretariaService {

    private final AlunoRepository alunoRepository;
    private final RoleRepository roleRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final AlunoDisciplinaRepository alunoDisciplinaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void cadastrarAluno(CadastrarAlunoModel alunoModel, HttpServletRequest request) {
        Role role = roleRepository.findByNome("ROLE_ALUNO");
        String password = gerarSenha();
        Usuario usuario = Usuario.builder()
                .roles(new ArrayList<>(List.of(role)))
                .email(alunoModel.getEmail())
                .nome(alunoModel.getNome())
                .username(alunoModel.getEmail().split("@")[0])
                .password(new BCryptPasswordEncoder().encode(password))
                .build();

        Aluno aluno = modelMapper.map(alunoModel, Aluno.class);
        aluno.setMatricula(gerarMatricula());
        aluno.setUsuario(usuario);
        aluno = alunoRepository.save(aluno);

        for (int i = 1; i < disciplinaRepository.count(); i++) {
            Disciplina disciplina = Disciplina.builder()
                    .id((long) i)
                    .build();
            AlunoDisciplina alunoDisciplina = AlunoDisciplina.builder()
                    .aluno(aluno)
                    .disciplina(disciplina)
                    .build();
            AlunoDisciplinaPK alunoDisciplinaPK = new AlunoDisciplinaPK(aluno.getId(), disciplina.getId());
            alunoDisciplina.setId(alunoDisciplinaPK);
            alunoDisciplinaRepository.save(alunoDisciplina);
        }
    }

    private static String gerarSenha(){
        int qtdeMaximaCaracteres = 8;
        String[] caracteres = { "0", "1", "b", "2", "4", "5", "6", "7", "8",
                "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z"};

        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < qtdeMaximaCaracteres; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            senha.append(caracteres[posicao]);
        }
        return senha.toString();
    }

    private static String gerarMatricula(){
        int qtdeMaximaCaracteres = 11;
        String[] caracteres = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

        StringBuilder matricula = new StringBuilder();

        for (int i = 0; i <= qtdeMaximaCaracteres; i++) {
            int posicao = (int) (Math.random() * caracteres.length);
            matricula.append(caracteres[posicao]);
        }
        return matricula.toString();
    }
}
