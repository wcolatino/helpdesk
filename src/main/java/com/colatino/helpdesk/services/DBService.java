package com.colatino.helpdesk.services;

import com.colatino.helpdesk.domain.Chamado;
import com.colatino.helpdesk.domain.Cliente;
import com.colatino.helpdesk.domain.Tecnico;
import com.colatino.helpdesk.domain.enums.Perfil;
import com.colatino.helpdesk.domain.enums.Prioridade;
import com.colatino.helpdesk.domain.enums.Status;
import com.colatino.helpdesk.repositories.ChamadoRepository;
import com.colatino.helpdesk.repositories.ClienteRepository;
import com.colatino.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;


    public void instanciaDB(){
        Tecnico tec1 = new Tecnico(null, "Willie Colatino", "713.908.801-25", "wcolatino@gmail.com", "123");
        Tecnico tec2 = new Tecnico(null, "Wilker Monteiro", "247.470.681-04", "wilker@gmail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);
        tec2.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus", "055.411.461-51", "linus@mail.com", "123");
        Cliente cli2 = new Cliente(null, "Willie", "359.194.950-73", "wille@mail.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
        Chamado c2 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 02", "Segundo chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1, tec2));
        clienteRepository.saveAll(Arrays.asList(cli1, cli2));
        chamadoRepository.saveAll(Arrays.asList(c1,c2));
    }

}
