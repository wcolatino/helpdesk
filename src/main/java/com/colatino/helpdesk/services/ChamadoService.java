package com.colatino.helpdesk.services;

import com.colatino.helpdesk.domain.Chamado;
import com.colatino.helpdesk.repositories.ChamadoRepository;
import com.colatino.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto ão encontrado! Id: " +id));
    }

    public List<Chamado> findAll(){
        return repository.findAll();
    }
}
