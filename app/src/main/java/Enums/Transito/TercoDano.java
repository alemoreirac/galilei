package Enums.Transito;

/**
 * Created by Pefoce on 29/05/2017.
 */

public enum TercoDano

{
    INFERIOR("Inferior"),MEDIO("Médio"),SUPERIOR("Superior");

    String valor;

    TercoDano(String val) {
    this.valor = val;
    }


    public String getValor()
    {
        return this.valor;
    }
}
