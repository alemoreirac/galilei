package Enums.Transito;

/**
 * Created by Pefoce on 25/08/2017.
 */

public enum NomeclaturaFaixas_1
{
    FAIXA_UNICA("Faixa Única");

    String valor;

    public String getValor() {
        return valor;
    }


    NomeclaturaFaixas_1(String s) {
        this.valor = s;
    }
}
