package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum TipoEnvolvidoTransito
{
    NAO_IDENTIFICADO("Não Identificado")
    ,PEDESTRE("Pedestre")
    ,MOTORISTA("Motorista")
    ,PASSAGEIRO("Passageiro");


    String valor;

    public String getValor() {
        return valor;
    }


    TipoEnvolvidoTransito(String s) {
        this.valor = s;
    }
}
