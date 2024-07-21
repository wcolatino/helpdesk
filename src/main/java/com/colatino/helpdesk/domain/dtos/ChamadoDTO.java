package com.colatino.helpdesk.domain.dtos;

import com.colatino.helpdesk.domain.Chamado;
import com.colatino.helpdesk.domain.Cliente;
import com.colatino.helpdesk.domain.Tecnico;
import com.colatino.helpdesk.domain.enums.Prioridade;
import com.colatino.helpdesk.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ChamadoDTO {

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    //Mudados de objeto para Integer, uma vez que só precisarei do Id do técnico e do Cliente para trabalhar no front
    @NotNull(message = "O campo PRIORIDADE não pode ser vazio")
    private Integer prioridade;
    @NotNull(message = "O campo STATUS não pode ser vazio")
    private Integer status;
    @NotNull(message = "O campo TITULO não pode ser vazio")
    private String titulo;
    @NotNull(message = "O campo OBSERVAÇÕES não pode ser vazio")
    private String observacoes;

    //Mudados de objeto para Integer, uma vez que só precisarei do Id do técnico e do Cliente para trabalhar no front
    @NotNull(message = "O campo TECNICO não pode ser vazio")
    private Integer tecnico;
    @NotNull(message = "O campo CLIENTE não pode ser vazio")
    private Integer cliente;

    private String nomeTecnico; //Para retornar apenas nome quando eu fize rum findAll
    private String nomeCliente;

    public ChamadoDTO(){
        super();
    }

    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getCodigo();
        this.status = obj.getStatus().getCodigo();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getTecnico().getId();
        this.cliente = obj.getCliente().getId();
        this.nomeTecnico = obj.getTecnico().getNome();
        this.nomeCliente = obj.getCliente().getNome();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
