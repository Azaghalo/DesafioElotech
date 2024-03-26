package com.elotech.desafioElotech.pessoa;

import com.elotech.desafioElotech.contato.Contato;
import com.elotech.desafioElotech.contato.ContatoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    PessoaRepository pessoaRepository;

    @Autowired
    ContatoService contatoService;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }

    public List<Pessoa> findAll(PessoaParam pessoaBuscaParam, Pageable pageable){
        return pessoaRepository.findAll(pessoaBuscaParam.toSpec(), pageable).getContent();
    }

    @Transactional(rollbackOn = Exception.class)
    public void createPessoa(Pessoa pessoa) throws Exception {
        List<Contato> contatos = pessoa.getContatos();

        pessoaRepository.save(pessoa);

        for (Contato contato : contatos) {
            contato.setPessoa(pessoa);
        }
        contatoService.saveAllContato(contatos);
    }
    
    public Pessoa findPessoaById(Long id){
        return pessoaRepository.findById(id).orElse(null);
    }

    public Boolean deletePessoa(Long id){
        try {
            pessoaRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean updatePessoa(Long id, PessoaParam updatedPessoa){
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);

        if(pessoaOptional.isPresent()){
            Pessoa pessoa = pessoaOptional.get();
            if(updatedPessoa.getNome() != null){
                pessoa.setNome(updatedPessoa.getNome());
            }
            if(updatedPessoa.getCpf() != null){
                pessoa.setCpf(updatedPessoa.getCpf());
            }
            if(updatedPessoa.getDataNascimento() != null){
                pessoa.setDataNascimento(updatedPessoa.getDataNascimento());
            }
            pessoaRepository.save(pessoa);
            return true;
        }

        return false;
    }

}
