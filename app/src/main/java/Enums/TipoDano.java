package Enums;

/**
 * Created by Pefoce on 29/05/2017.
 */

public enum TipoDano {
    CONTUSO ("Contuso"),
    FRICCAO ("Fricção"),
    CORTANTE ("Cortante"),
    PERFURANTE("Perfurante");


    String valor;

    public String getValor() {
        return valor;
    }


    TipoDano(String s) {
        this.valor = s;
    }



    }
