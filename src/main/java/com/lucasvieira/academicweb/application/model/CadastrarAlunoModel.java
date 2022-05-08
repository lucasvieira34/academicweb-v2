package com.lucasvieira.academicweb.application.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class CadastrarAlunoModel {
    @NotEmpty(message = "A matrícula não pode estar vazia.")
    @Size(min = 4 ,message = "A matrícula precisa ter no mínimo 4 dígitos.")
    private String matricula;

    @NotEmpty(message = "O nome não pode estar vazio.")
    private String nome;

    @CPF
    @NotEmpty(message = "O cpf não pode estar vazio.")
    private String cpf;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dataNascimento;

    @DecimalMin(value = "0", message = "A mensalidade precisa ser maior que zero.")
    private BigDecimal mensalidade;

    @NotEmpty(message = "O email do responsável não pode estar vazio.")
    @Email(message = "Este email não é válido.")
    private String emailResponsavel;

    @NotEmpty(message = "O email do aluno não pode estar vazio.")
    @Email(message = "Este email não é válido.")
    private String email;
}
