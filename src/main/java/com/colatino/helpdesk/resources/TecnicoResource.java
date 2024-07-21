package com.colatino.helpdesk.resources;

import com.colatino.helpdesk.domain.Tecnico;
import com.colatino.helpdesk.domain.dtos.TecnicoDTO;
import com.colatino.helpdesk.repositories.TecnicoRepository;
import com.colatino.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> findaById(@PathVariable Integer id){ //Returno o DTO ao invés da entidade
        Tecnico obj = tecnicoService.findById(id);
        return ResponseEntity.ok(new TecnicoDTO(obj)); //Passo o DTO como objeto e o obj como parâmetro, já que tenho construtor obrigado.
    }
}
