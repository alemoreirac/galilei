package Enums;

/**
 * Created by Pefoce on 25/08/2017.
 */


public enum NomeclaturaFaixas_3
{
    FAIXA_ESQUERDA("Faixa da Esquerda"), FAIXA_CENTRAL("Faixa Central"), FAIXA_DIREITA("Faixa da Direita");

    String valor;

    public String getValor() {
        return valor;
    }


    NomeclaturaFaixas_3(String s) {
        this.valor = s;
    }


}
