package com.colatino.helpdesk.domain.enums;

public enum Status {

    ABERTO (0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

    private Integer codigo;
    private String descricao;

    Status(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /*Converte para Enum o codigo do status que for passado*/
    public static Status toEnum(Integer cod){

        if (cod ==null){ //Se não existir o código, retorna null
            return  null;
        }

        for (Status x : Status.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Status Inválido");
    }
}
