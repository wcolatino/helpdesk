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
        Tecnico tec1 = new Tecnico(null, "Willie Colatino", "71390880125", "wcolatino@gmail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Linus", "05541146151", "linus@mail.com", "123");

        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);

        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }

}
