package Enums;

/**
 * Created by Pefoce on 29/05/2017.
 */

public enum TipoColisao {
    COLISAO_CRUZ("Colisão em Cruzamento")
    ,COLISAO ("Colisão")
    ,CHOQUE ("Choque")
    ,ATROPELAMENTO ("Atropelamento")
    ,ABALROAMENTO("Abalroaemento");



    String valor;

    public String getValor() {
        return valor;
    }

    TipoColisao(String s) {
        this.valor = s;
    }
}
