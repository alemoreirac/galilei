package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum SinalizacaoPare {
    VERTICAL("Vertical")
    ,HORIZONTAL("Horizontal")
    ,HORIZONTAL_APAGADO("Horizontal Apagado")
    ,AUSENTE("Ausente");

    String valor;

    public String getValor() {
        return valor;
    }


    SinalizacaoPare(String s) {
        this.valor = s;
    }

}
