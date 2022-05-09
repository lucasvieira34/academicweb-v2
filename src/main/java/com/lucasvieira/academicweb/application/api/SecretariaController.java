package com.lucasvieira.academicweb.application.api;

import com.lucasvieira.academicweb.application.model.CadastrarAlunoModel;
import com.lucasvieira.academicweb.application.service.*;
import com.lucasvieira.academicweb.domain.entity.Turma;
import com.lucasvieira.academicweb.domain.entity.Usuario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller()
@RequestMapping("/secretaria")
@RequiredArgsConstructor
public class SecretariaController {

    private final AlunoService alunoService;
    private final ProfessorService professorService;
    private final DisciplinaService disciplinaService;
    private final UsuarioService usuarioService;
    private final TurmaService turmaService;
    private final SecretariaService secretariaService;

    private Usuario usuarioLogado;

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
    public ModelAndView viewCadastrarAluno(@ModelAttribute("cadastrarAlunoModel") CadastrarAlunoModel alunoModel) {
        usuarioLogado();
        ModelAndView mv = new ModelAndView("secretaria/cadastrar-aluno");
        List<Turma> turmas = turmaService.findAllTurmas();
        mv.addObject("turmas", turmas);
        mv.addObject("usuarioLogado", usuarioLogado);
        return mv;
    }

    @PostMapping("/cadastrar/aluno")
    public String cadastrarAluno(@ModelAttribute("cadastrarAlunoModel") @Valid CadastrarAlunoModel alunoModel,
                                 BindingResult result,
                                 @RequestParam("fileUsuario") MultipartFile file, Model model, HttpServletRequest request) {
        usuarioLogado();
        Usuario emailExistente = usuarioService.findByEmail(alunoModel.getEmail());
        if (!Objects.isNull(emailExistente)) {
            result.rejectValue("email", null, "Já existe uma conta registrada com este endereço de email.");
        }

        if (result.hasErrors()) {
            model.addAttribute("usuarioLogado", usuarioLogado);
            return "secretaria/cadastrar-aluno";
        }

        secretariaService.cadastrarAluno(alunoModel, request);

        return "redirect:/secretaria/cadastrar/aluno?success";
    }

    private void usuarioLogado() {
        Authentication autenticado = SecurityContextHolder.getContext().getAuthentication();
        if (!(autenticado instanceof AnonymousAuthenticationToken)) {
            String login = autenticado.getName();
            usuarioLogado = usuarioService.findByUsername(login);
        }
    }
}
