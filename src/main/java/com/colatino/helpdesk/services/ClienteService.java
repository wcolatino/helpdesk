package com.colatino.helpdesk.services;

import com.colatino.helpdesk.domain.Cliente;
import com.colatino.helpdesk.domain.Pessoa;
import com.colatino.helpdesk.domain.dtos.ClienteDTO;
import com.colatino.helpdesk.domain.dtos.TecnicoDTO;
import com.colatino.helpdesk.repositories.ClienteRepository;
import com.colatino.helpdesk.repositories.PessoaRepository;
import com.colatino.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.colatino.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
       Optional<Cliente> cliente = repository.findById(id);
       return cliente.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: "+id));
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null); //Se vir ID seta NUll, caso seja UPDATE;
        validaPorCpfEEmail(objDTO);
        Cliente newObj = new Cliente(objDTO); //FOi necessário criar um construtor em técnico recebendo TecnidoDTO;
        return repository.save(newObj);
    }

    public Cliente update(Integer id, ClienteDTO objDto) {
        objDto.setId(id); //Seta o id o DTO como o ID que veio, evitando que sejam enviados novvos ID's
        Cliente oldObj = findById(id);
        validaPorCpfEEmail(objDto);
        oldObj = new Cliente(objDto);
        return repository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size()>0){
            throw new DataIntegrityViolationException("O cliente possui ordens de serviço e não pode ser deletado!");
        }else{
            repository.deleteById(id);
        }
    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {
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
