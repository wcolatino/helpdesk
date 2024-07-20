package com.colatino.helpdesk.domain.enums;

public enum Perfil {

    ADMIN (0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), TECNICO(2, "ROLE_TECNICO");

    private Integer codigo;
    private String descricao;

    Perfil(Integer codigo, String descricao) {
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

    /*Converte para Enum o codigo do perfil que for passado*/
    public static Perfil toEnum(Integer cod){

        if (cod ==null){ //Se não existir o código, retorna null
            return  null;
        }

        for (Perfil x : Perfil.values()){
            if (cod.equals(x.getCodigo())){
                return x;
            }
        }
        throw new IllegalArgumentException("Perfil Inválido");
    }
}
