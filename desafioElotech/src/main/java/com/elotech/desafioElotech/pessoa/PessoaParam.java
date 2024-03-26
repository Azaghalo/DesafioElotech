package com.elotech.desafioElotech.pessoa;

import com.elotech.desafioElotech.contato.Contato;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PessoaParam {

    private String nome;

    @CPF(message = "Campo invalido!")
    private String cpf;

    @Past(message = "Data informada invalida!")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "dd/MM/yyyy")
    private Date dataNascimento;

    public PessoaParam() {
    }

    public PessoaParam(String nome, String cpf, Date dataNascimento, List<Contato> contatos) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public void UpdatePessoa(Pessoa updatedPessoa){
        this.nome = updatedPessoa.getNome();
        this.cpf = updatedPessoa.getCpf();
        this.dataNascimento = updatedPessoa.getDataNascimento();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Specification<Pessoa> toSpec() {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.hasText(nome)){
               Path<String> campoNome = root.<String>get("nome");
               Predicate predicadoNome = criteriaBuilder.like(campoNome, "%"+nome+"%");
               predicates.add(predicadoNome);
            }
            if(StringUtils.hasText(cpf)){
                Path<String> campoCPF = root.<String>get("cpf");
                Predicate predicadoCPF = criteriaBuilder.like(campoCPF, "%"+cpf+"%");
                predicates.add(predicadoCPF);
            }
            if(dataNascimento != null){
                Path<String> campoData = root.<String>get("dataNascimento");
                Predicate predicadoData = criteriaBuilder.equal(campoData, dataNascimento);
                predicates.add(predicadoData);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
