package com.elotech.desafioElotech.contato;

import com.elotech.desafioElotech.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotBlank(message = "Campo não informado!")
    private String nome;

    @NotBlank
    @NotBlank(message = "Campo não informado!")
    private String telefone;

    @Email(message = "Campo invalido!")
    @NotBlank(message = "Campo não informado!")
    private String email;

    @JsonIgnore
    @ManyToOne
    private Pessoa pessoa;

    public Contato() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
