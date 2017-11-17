package Enums.Transito;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum CondicaoPista {
    BOA("Boa")
    ,RUIM("Má");



    String valor;

    public String getValor() {
        return valor;
    }


    CondicaoPista(String s) {
        this.valor = s;
    }
}
