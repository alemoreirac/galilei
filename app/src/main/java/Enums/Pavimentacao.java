package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum Pavimentacao {
    ASFALTO("Asfalto")
    ,PARALELEPIPEDO("Paralelepipedo")
    ,CONCRETO("Concreto")
    ,TERRA("Terra");

    String valor;

    public String getValor() {
        return valor;
    }

    Pavimentacao(String s) {
        this.valor = s;
    }
}
