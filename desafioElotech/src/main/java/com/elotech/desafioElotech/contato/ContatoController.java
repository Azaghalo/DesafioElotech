package com.elotech.desafioElotech.contato;

import com.elotech.desafioElotech.pessoa.Pessoa;
import com.elotech.desafioElotech.pessoa.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas/{pessoaId}")
public class ContatoController {
    private ContatoService contatoService;
    private PessoaService pessoaService;

    public ContatoController(ContatoService contatoService, PessoaService pessoaService) {
        this.contatoService = contatoService;
        this.pessoaService = pessoaService;
    }

    @GetMapping("/contatos")
    public ResponseEntity<List<Contato>> findAll(@PathVariable Long pessoaId){
        return new ResponseEntity<>(contatoService.findAll(pessoaId), HttpStatus.OK);
    }

    @PostMapping("/contatos")
    public ResponseEntity<String> addContato(@PathVariable Long pessoaId, @RequestBody @Valid Contato contato){
        Pessoa pessoa = pessoaService.findPessoaById(pessoaId);
        return contatoService.addContato(pessoa, contato) ? new ResponseEntity<>("Contato adicionado com sucesso", HttpStatus.CREATED)
                : new ResponseEntity<>("Nenhuma pessoa com o ID informado", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/contatos/{id}")
    public ResponseEntity<String> updateContato(@PathVariable Long pessoaId, @PathVariable Long id, @RequestBody @Valid Contato contato){
        Pessoa pessoa = pessoaService.findPessoaById(pessoaId);
        return contatoService.updateContato(pessoa, id, contato) ? new ResponseEntity<>("Contato atualizado com sucesso", HttpStatus.CREATED)
                : new ResponseEntity<>("Não foi possivel atualizar o contato", HttpStatus.NOT_FOUND);
    }
}
