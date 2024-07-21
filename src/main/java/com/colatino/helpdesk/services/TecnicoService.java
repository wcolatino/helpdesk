package com.colatino.helpdesk.services;

import com.colatino.helpdesk.domain.Pessoa;
import com.colatino.helpdesk.domain.Tecnico;
import com.colatino.helpdesk.domain.dtos.TecnicoDTO;
import com.colatino.helpdesk.repositories.PessoaRepository;
import com.colatino.helpdesk.repositories.TecnicoRepository;
import com.colatino.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.colatino.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id){
       Optional<Tecnico> tecnico = repository.findById(id);
       return tecnico.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: "+id));
    }

    public List<Tecnico> findAll(){
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null); //Se vir ID seta NUll, caso seja UPDATE;
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = new Tecnico(objDTO); //FOi necessário criar um construtor em técnico recebendo TecnidoDTO;
        return repository.save(newObj);
    }

    public Tecnico update(Integer id, TecnicoDTO objDto) {
        objDto.setId(id); //Seta o id o DTO como o ID que veio, evitando que sejam enviados novvos ID's
        Tecnico oldObj = findById(id);
        validaPorCpfEEmail(objDto);
        oldObj = new Tecnico(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getChamados().size()>0){
            throw new DataIntegrityViolationException("O técnico possui ordens de serviço e não pode ser deletado!");
        }else{
            repository.deleteById(id);
        }
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());

        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){ //Validará também na alteração
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){ //Validará também na alteração
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }



}
