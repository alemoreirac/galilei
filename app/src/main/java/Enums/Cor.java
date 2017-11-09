package Enums;

/**
 * Created by Pefoce on 12/07/2017.
 */

public enum Cor {
    AMARELO("Amarelo"),
    AZUL("Azul"),
    BRANCO("Branco"),
    CINZA("Cinza"),
    LARANJA("Laranja"),
    BEGE("Bege"),
    PRETO("Preto"),
    ROXO("Roxo"),
    ROSA("Rosa"),
    VERDE("Verde"),
    VERMELHO("Vermelho");

    String valor;

    public String getValor() {
        return valor;
    }


    Cor(String s) {
        this.valor = s;
    }

    }
