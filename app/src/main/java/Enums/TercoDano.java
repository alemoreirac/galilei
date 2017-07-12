package Enums;

/**
 * Created by Pefoce on 29/05/2017.
 */

public enum TercoDano {
    INFERIOR("Terço Inferior"),MEDIO("Terço Médio"),SUPERIOR("Terço Superior");

    String valor;

    TercoDano(String val) {
    this.valor = val;
    }


    public String getValor()
    {
        return this.valor;
    }
}
