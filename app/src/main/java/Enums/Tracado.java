package Enums;

/**
 * Created by Pefoce on 03/07/2017.
 */
public enum Tracado {
    CURVA("Curva"), RETA("Reta");

    String valor;

    public String getValor() {
        return valor;
    }


    Tracado(String s) {
        this.valor = s;
    }
}
