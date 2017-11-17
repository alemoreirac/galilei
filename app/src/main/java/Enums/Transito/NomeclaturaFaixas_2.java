package Enums.Transito;

/**
 * Created by Pefoce on 25/08/2017.
 */

public enum NomeclaturaFaixas_2
{
    FAIXA_DIREITA("Faixa da Direita"),FAIXA_ESQUERDA("Faixa da Esquerda");

    String valor;

    public String getValor() {
        return valor;
    }


    NomeclaturaFaixas_2(String s) {
        this.valor = s;
    }
}