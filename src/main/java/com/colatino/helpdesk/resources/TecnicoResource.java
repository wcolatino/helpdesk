package com.colatino.helpdesk.resources;

import com.colatino.helpdesk.domain.Tecnico;
import com.colatino.helpdesk.domain.dtos.TecnicoDTO;
import com.colatino.helpdesk.repositories.TecnicoRepository;
import com.colatino.helpdesk.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList()); //Converte a lista de Tecnicos em TecnicoDTO;
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
        Tecnico newObj = tecnicoService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri(); //Para devolver a URI, já que esei o "created" como resposta
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@Valid @PathVariable Integer id, @RequestBody TecnicoDTO objDto){
        Tecnico obj = tecnicoService.update(id, objDto);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
