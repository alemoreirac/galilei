package Enums;

/**
 * Created by Pefoce on 29/05/2017.
 */

public enum TipoDano {
    CONTUSO ("Dano Contuso"),
    FRICCAO ("Dano de Fricção"),
    CORTANTE ("Dano Cortante"),
    PERFURANTE("Dano Perfurante");


    String valor;

    public String getValor() {
        return valor;
    }


    TipoDano(String s) {
        this.valor = s;
    }



    }
