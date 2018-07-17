package Enums.Transito;

/**
 * Created by Pefoce on 11/08/2017.
 */

public enum Lesao
{

    ILESO("Sem lesões"), LEVE("Leve"), GRAVE("Grave"), FATAL("Fatal");

    String valor;

    public String getValor()
    {
        return valor;
    }


    Lesao(String s)
    {
        this.valor = s;
    }
}
