package Enums;

/**
 * Created by Pefoce on 20/07/2017.
 */

public enum Genero {
    MASCULINO("Masculino"),FEMININO("Feminino") ,NAO_IDENTIFICADO("Não Identificado");

    String valor;

    public String getValor() {
        return valor;
    }


    Genero(String s) {
        this.valor = s;
    }
}
