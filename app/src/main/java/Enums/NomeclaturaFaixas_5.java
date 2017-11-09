package Enums;

/**
 * Created by Pefoce on 25/08/2017.
 */


public enum NomeclaturaFaixas_5
{
    PRIMEIRA_ESQUERDA("Primeira Faixa da Esquerda"), SEGUNDA_ESQUERDA("Segunda Faixa da Eqsuerda"),FAIXA_CENTRAL("Faixa Central"),SEGUNDA_DIREITA("Segunda Faixa da Direita"),PRIMEIRA_DIREITA("Primeira Faixa da Direita");

    String valor;

    public String getValor() {
        return valor;
    }

    NomeclaturaFaixas_5(String s) {
        this.valor = s;
    }
}
