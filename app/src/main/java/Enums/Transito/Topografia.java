package Enums.Transito;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum Topografia {
    RETA_PLANA("Plana")
    ,ACLIVE("Aclive")
    ,DECLIVE("Declive");

    String valor;

    public String getValor() {
        return valor;
    }

    Topografia(String s) {
        this.valor = s;
    }
}
