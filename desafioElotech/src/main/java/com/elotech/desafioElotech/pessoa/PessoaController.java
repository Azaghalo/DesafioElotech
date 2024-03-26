package com.elotech.desafioElotech.pessoa;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll(){
        return new ResponseEntity<>(pessoaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Pessoa>> buscaPaginadaDinamica(@RequestBody @Valid PessoaParam pessoaBuscaParam, Pageable pageable){
        return new ResponseEntity<>(pessoaService.findAll(pessoaBuscaParam, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findPessoaById(@PathVariable Long id){
        Pessoa pessoa = pessoaService.findPessoaById(id);
        return pessoa != null ? new ResponseEntity<>(pessoa, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createPessoa(@RequestBody @Valid Pessoa pessoa){
        try{
            pessoaService.createPessoa(pessoa);
            return  new ResponseEntity<>("Pessoa adicionada com sucesso", HttpStatus.CREATED);
        }catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePessoa(@PathVariable Long id){
        return pessoaService.deletePessoa(id) ? new ResponseEntity<>("Cadastro excluido com sucesso", HttpStatus.OK)
                : new ResponseEntity<>("Não existe nenhum cadastro com a ID informada", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePessoa(@PathVariable Long id, @RequestBody @Valid PessoaParam updatedPessoa){
        return pessoaService.updatePessoa(id, updatedPessoa) ? new ResponseEntity<>("Cadastro atualizado com sucesso", HttpStatus.OK)
                : new ResponseEntity<>("Não existe nenhum cadastro com a ID informada", HttpStatus.NOT_FOUND);
    }

}
