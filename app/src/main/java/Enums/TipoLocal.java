package Enums;

/**
 * Created by Pefoce on 14/12/2017.
 */

public enum TipoLocal
{
    EXTERNO("Externo"),INTERNO("Interno");

    String valor;

    public String getValor() {
        return valor;
    }

    TipoLocal(String s) {
        this.valor = s;
    }
}
