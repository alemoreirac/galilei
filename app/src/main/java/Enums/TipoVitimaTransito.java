package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum TipoVitimaTransito {
    MOTORISTA("Motorista")
    ,PASSAGEIRO("Passageiro")
    ,PEDESTRE("Pedestre");


    String valor;

    public String getValor() {
        return valor;
    }


    TipoVitimaTransito(String s) {
        this.valor = s;
    }
}
