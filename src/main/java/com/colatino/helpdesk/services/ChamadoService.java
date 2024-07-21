package com.colatino.helpdesk.services;

import com.colatino.helpdesk.domain.Chamado;
import com.colatino.helpdesk.domain.Cliente;
import com.colatino.helpdesk.domain.Tecnico;
import com.colatino.helpdesk.domain.dtos.ChamadoDTO;
import com.colatino.helpdesk.domain.enums.Prioridade;
import com.colatino.helpdesk.domain.enums.Status;
import com.colatino.helpdesk.repositories.ChamadoRepository;
import com.colatino.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id){
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto ão encontrado! Id: " +id));
    }

    public List<Chamado> findAll(){
        return repository.findAll();
    }

    public Chamado create(ChamadoDTO objDTO) {
        return repository.save(newChamado(objDTO));
    }

    public Chamado update(Integer id, ChamadoDTO objDTO) {
        objDTO.setId(id); //garante que não receba valores inválidos no update
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDTO); //Pegará os dados o Dto Antigo e passará para o novo chamado
        return repository.save(oldObj);
    }

    private Chamado newChamado(ChamadoDTO obj){
        Cliente cliente = clienteService.findById(obj.getCliente());
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());

        Chamado chamado = new Chamado(); //Se o Id vier vazio, é novo chamado, se vier preenchido, é atualização
        if (obj.getId()!=null){
            chamado.setId(obj.getId());
        }

        if (obj.getStatus().equals(2)){
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        chamado.setStatus(Status.toEnum(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }


}
