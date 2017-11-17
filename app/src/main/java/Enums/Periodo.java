package Enums;

/**
 * Created by Pefoce on 15/11/2017.
 */

public enum Periodo
{
    DIA("Dia"),NOITE("Noite");
    String valor;

    public String getValor() {
        return valor;
    }


    Periodo(String s) {
        this.valor = s;
    }

}
