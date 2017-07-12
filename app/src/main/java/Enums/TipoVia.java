package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum TipoVia {
    RUA("Rua")
    ,AVENIDA("Avenida")
    ,ESTRADA("Estrada");



    String valor;

    public String getValor() {
        return valor;
    }

    TipoVia(String s) {
        this.valor = s;
    }
}
