package Enums;

/**
 * Created by Pefoce on 15/11/2017.
 */

public enum TipoVegetacao
{
    CLAREIRA("Clareira"),SEM_VEGETACAO("Sem Vegetação"),RASTEIRA("Vegetação Rasteira"),MEDIA("Vegetação Média"),ALTA("Vegetação Alta");

    String valor;

    public String getValor() {
        return valor;
    }

    TipoVegetacao(String s) {
        this.valor = s;
    }

}
