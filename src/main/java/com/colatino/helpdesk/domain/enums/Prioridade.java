package com.colatino.helpdesk.domain.enums;

public enum Prioridade {

    BAIXA (0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");

    private Integer codigo;
    private String descricao;

    Prioridade(Integer codigo, String descricao) {
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
    public static Prioridade toEnum(Integer cod){

        if (cod ==null){ //Se não existir o código, retorna null
            return  null;
        }

        for (Prioridade x : Prioridade.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Prioridade Inválida");
    }
}
