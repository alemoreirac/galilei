package Enums;

/**
 * Created by Pefoce on 05/10/2017.
 */

public enum TipoOcorrencia {
    TRANSITO ("Trânsito")
    ,VIDA("Crime contra a Vida")
    ,PATRIMONIO("Patrimônio");

    String valor;

    public String getValor() {
        return valor;
    }

    TipoOcorrencia(String s) {
        this.valor = s;
    }
}
