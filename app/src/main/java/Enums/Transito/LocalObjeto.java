package Enums.Transito;

/**
 * Created by Pefoce on 29/08/2017.
 */


public enum LocalObjeto
{

    PISTA("Pista"),ACOSTAMENTO("Acostamento"),CALCADA("Calçada"),PASSARELA("Passarela");

    String valor;

    public String getValor() {
        return valor;
    }


    LocalObjeto(String s) {
        this.valor = s;
    }
}

