package Enums;

/**
 * Created by Pefoce on 31/08/2017.
 */

public enum OrientacaoGeograficaComposta
{
    NORTE_SUL("Norte-Sul")
    ,SUL_NORTE("Sul-Norte")

    ,LESTE_OESTE("Leste-Oeste")
    ,OESTE_LESTE("Oeste-Leste")

    ,NOROESTE_SUDESTE("Noroeste-Sudeste")
    ,SUDESTE_NOROESTE("Sudeste-Noroeste")

    ,NORDESTE_SUDOESTE("Nordeste-Sudoeste")
    ,SUDOESTE_NORDESTE("Sudoeste-Nordeste");

    String valor;

    public String getValor() {
        return valor;
    }


    OrientacaoGeograficaComposta(String s) {
        this.valor = s;
    }

}
