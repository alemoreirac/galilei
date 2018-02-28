package Enums;

/**
 * Created by Pefoce on 15/11/2017.
 */

public enum TipoLocalCrime
{
    RESIDENCIAL("Residencial"),RURAL("Rural"),PRAIA("Praia"),VIA_PUBLICA("Via pública"),OUTRO("Outro");

    String valor;

    public String getValor() {
        return valor;
    }

    TipoLocalCrime(String s) {
        this.valor = s;
    }

}