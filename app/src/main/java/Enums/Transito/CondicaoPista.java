package Enums.Transito;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum CondicaoPista {
    BOA("Boa")
    ,REGULAR("Regular")
    ,RUIM("Má");



    String valor;

    public String getValor() {
        return valor;
    }


    CondicaoPista(String s) {
        this.valor = s;
    }
}
