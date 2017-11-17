package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum IluminacaoVia
{
    BOA("Boa"), RUIM("Ruim"), AUSENTE("Ausente");

    String valor;

    public String getValor()
    {
        return valor;
    }

    IluminacaoVia(String s)
    {
        this.valor = s;
    }
}
