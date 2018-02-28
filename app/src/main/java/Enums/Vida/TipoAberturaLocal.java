package Enums.Vida;

/**
 * Created by Pefoce on 14/12/2017.
 */

public enum TipoAberturaLocal
{
    ABERTO("Aberto"),FECHADO("Fechado");

    String valor;

    public String getValor() {
        return valor;
    }

    TipoAberturaLocal(String s) {
        this.valor = s;
    }

}
