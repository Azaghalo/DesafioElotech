package com.elotech.desafioElotech.pessoa;

import com.elotech.desafioElotech.contato.Contato;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo não informado!")
    private String nome;

    @CPF(message = "Campo invalido!")
    @NotBlank(message = "Campo não informado!")
    private String cpf;

    @Past(message = "Data informada invalida!")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "dd/MM/yyyy")
    @NotNull(message = "Campo não informado!")
    private Date dataNascimento;

    @OneToMany(mappedBy = "pessoa",cascade = CascadeType.ALL, orphanRemoval = true)
    @NotEmpty(message = "Obrigatório ter pelo menos um contato!")
    private List<Contato> contatos;

    public Pessoa() {
    }

    public Pessoa(Long id, String nome, String cpf, Date dataNascimento, List<Contato> contatos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.contatos = contatos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
