package com.elotech.desafioElotech.contato;

import com.elotech.desafioElotech.pessoa.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContatoService {
    ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public List<Contato> findAll(Long pessoaId){
        return contatoRepository.findByPessoaId(pessoaId);
    }

    public Boolean addContato(Pessoa pessoa, Contato contato){
        if(pessoa != null){
            contato.setPessoa(pessoa);
            contatoRepository.save(contato);
            return true;
        }
        return false;
    }

    public Contato findContatoById(Long id){
        return contatoRepository.findById(id).orElse(null);
    }

    public void saveAllContato(List<Contato> contatos) throws Exception{
        try {
            contatoRepository.saveAll(contatos);
        }catch (Exception e){
            throw new Exception(e);
        }
    }

    public Boolean deleteContato(Long id){
        try {
            contatoRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean updateContato(Pessoa pessoa, Long id, Contato updatedContato){
        Optional<Contato> contatoOptional = contatoRepository.findById(id);


        if(contatoOptional.isPresent()){
            if(pessoa.getContatos().contains(contatoOptional.get())){
                Contato contato = contatoOptional.get();
                if(updatedContato.getNome() != null){
                    contato.setNome(updatedContato.getNome());
                }
                if(updatedContato.getEmail() != null){
                    contato.setEmail(updatedContato.getEmail());
                }
                if(updatedContato.getTelefone() != null){
                    contato.setTelefone(updatedContato.getTelefone());
                }
                contatoRepository.save(contato);
                return true;
            }
        }

        return false;
    }
}
