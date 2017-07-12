package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum Iluminacao {
    BOA("Boa")
    ,RUIM("Ruim")
    ,AUSENTE("Ausente");

    String valor;

    public String getValor() {
        return valor;
    }


    Iluminacao(String s) {
        this.valor = s;
    }
}
