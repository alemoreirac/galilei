package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum Logradouro {
    SIMPLES("Simples")
    ,COMPOSTO("Composto")
    ,SENTIDO_UNICO("Sentido Único")
    ,VICE_VERSA ("Vice Versa");

    String valor;

    public String getValor() {
        return valor;
    }


    Logradouro(String s) {
        this.valor = s;
    }
}
