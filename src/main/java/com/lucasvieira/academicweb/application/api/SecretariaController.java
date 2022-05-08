package com.lucasvieira.academicweb.application.api;

import com.lucasvieira.academicweb.application.model.CadastrarAlunoModel;
import com.lucasvieira.academicweb.application.service.*;
import com.lucasvieira.academicweb.domain.entity.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller()
@RequestMapping("/secretaria")
@RequiredArgsConstructor
public class SecretariaController {

    private final AlunoService alunoService;
    private final ProfessorService professorService;
    private final DisciplinaService disciplinaService;
    private final UsuarioService usuarioService;

    private Usuario usuarioLogado;

    @ModelAttribute("cadastrarAlunoModel")
    public CadastrarAlunoModel cadastrarAlunoModel() {
        return new CadastrarAlunoModel();
    }

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        usuarioLogado();
        ModelAndView mv = new ModelAndView("secretaria/secretaria-dashboard");
        mv.addObject("usuarioLogado", usuarioLogado);
        mv.addObject("quantidadeAlunos", alunoService.quantidadeAlunos());
        mv.addObject("quantidadeProfessores", professorService.quantidadeProfessores());
        mv.addObject("quantidadeDisciplinas", disciplinaService.quantidadeDisciplinas());
        mv.addObject("quantidadeFuncionarios", 0); //TODO IMPLEMENTAR FUNCIONARIOS
        mv.addObject("totalMensalidades", alunoService.totalMensalidades());
        mv.addObject("totalSalarios", professorService.totalSalarios());
        mv.addObject("balancoMensal", 0); //TODO IMPLEMENTAR FUNCIONARIOS
        return mv;
    }

    @GetMapping("/cadastrar/aluno")
    public ModelAndView viewCadastrarAluno() {
        usuarioLogado();
        ModelAndView mv = new ModelAndView("secretaria/cadastrar-aluno");
        mv.addObject("usuarioLogado", usuarioLogado);
        return mv;
    }

    private void usuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String login = autenticado.getName();
            usuarioLogado = usuarioService.findByUsername(login);
        }
    }
}
