package com.colatino.helpdesk.services;

import com.colatino.helpdesk.domain.Tecnico;
import com.colatino.helpdesk.domain.dtos.TecnicoDTO;
import com.colatino.helpdesk.repositories.TecnicoRepository;
import com.colatino.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id){
       Optional<Tecnico> tecnico = repository.findById(id);
       return tecnico.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id: "+id));
    }

    public List<Tecnico> findAll(){
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null); //Se vir ID seta NUll, caso seja UPDATE;
        Tecnico newObj = new Tecnico(objDTO); //FOi necessário criar um construtor em técnico recebendo TecnidoDTO;
        return repository.save(newObj);
    }
}
