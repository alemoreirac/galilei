package Enums.Transito;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum SinalizacaoPare {
    VERTICAL("Vertical")
    ,HORIZONTAL("Horizontal")
    ,VERTICAL_E_HORIZONTAL("Vertical e Horizontal")
    ,MAS_CONDICOES("Más Condições")
    ,AUSENTE("Ausente");

    String valor;

    public String getValor() {
        return valor;
    }


    SinalizacaoPare(String s) {
        this.valor = s;
    }

}
