package Enums;

/**
 * Created by Pefoce on 15/11/2017.
 */

public enum TipoLocal
{
    RESIDENCIAL("Residêncial"),RURAL("Rural"),PRAIA("Praia"),VIA_PUBLICA("Via Pública");

    String valor;

    public String getValor() {
        return valor;
    }


    TipoLocal(String s) {
        this.valor = s;
    }

}