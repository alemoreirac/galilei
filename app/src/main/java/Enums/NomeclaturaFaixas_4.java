package Enums;

/**
 * Created by Pefoce on 25/08/2017.
 */


public enum NomeclaturaFaixas_4
{
    PRIMEIRA_ESQUERDA("Primeira Faixa da Esquerda"), SEGUNDA_ESQUERDA("Segunda Faixa da Eqsuerda"),SEGUNDA_DIREITA("Segunda Faixa da Direita"),PRIMEIRA_DIREITA("Primeira Faixa da Direita");

    String valor;

    public String getValor() {
        return valor;
    }


    NomeclaturaFaixas_4(String s) {
        this.valor = s;
    }
}
