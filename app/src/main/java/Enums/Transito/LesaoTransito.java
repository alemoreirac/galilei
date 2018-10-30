package Enums.Transito;

/**
 * Created by Pefoce on 11/08/2017.
 */

public enum LesaoTransito
{

    ILESO("Sem lesões"), LEVE("Leve"), GRAVE("Grave"), FATAL("Fatal");

    String valor;

    public String getValor()
    {
        return valor;
    }


    LesaoTransito(String s)
    {
        this.valor = s;
    }
}
