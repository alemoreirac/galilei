package Enums.Transito;

/**
 * Created by Pefoce on 03/07/2017.
 */

public enum HumidadePista {

    SECA("Seca"),MOLHADA("Molhada");


    String valor;

    public String getValor() {
        return valor;
    }


    HumidadePista(String s) {
        this.valor = s;
    }
}
