package Enums.Transito;

/**
 * Created by Pefoce on 03/07/2017.
 */

public enum TipoCNH
{
    A("A"),B("B"),C("C"),D("D"),E("E"),AB("AB"),AC("AC"),AD("AD"),AE("AE"),NP("Não Possui");

    String valor;

    public String getValor() {
        return valor;
    }


    TipoCNH(String s) {
        this.valor = s;
    }
}
