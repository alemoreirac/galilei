package Enums;

/**
 * Created by Pefoce on 29/05/2017.
 */

public enum TipoInteracao
{
    CHOQUE ("Choque")
    ,ATROPELAMENTO ("Atropelamento")
    ,ADERNAMENTO("Adernamento")
    ,COLISAO_CRUZ("Colisão em Cruzamento")
    ,COLISAO ("Colisão Frontal")
    ,COLISAO_FRONTAL ("Colisão Frontal")
    ,COLISAO_LATERAL ("Colisão Lateral")
    ,COLISAO_TRASEIRA ("Colisão Traseira")
    ,ABALROAMENTO("Abalroamento");


    String valor;

    public String getValor() {
        return valor;
    }

    TipoInteracao(String s) {
        this.valor = s;
    }
}
