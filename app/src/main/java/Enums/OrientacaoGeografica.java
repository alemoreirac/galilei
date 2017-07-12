package Enums;

/**
 * Created by Pefoce on 05/06/2017.
 */

public enum OrientacaoGeografica {
    NORTE("Norte")
    ,SUL("Sul")
    ,LESTE("Leste")
    ,OESTE("Oeste")
    ,NOROESTE("Noroeste")
    ,NORDESTE("Nordeste")
    ,SUDOESTE("Sudoeste")
    ,SUDESTE("Sudeste");


    String valor;

    public String getValor() {
        return valor;
    }


    OrientacaoGeografica(String s) {
        this.valor = s;
    }

    }
