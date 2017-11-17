package Enums.Vida;

/**
 * Created by Pefoce on 17/11/2017.
 */

public enum NaturezaLesao
{
    CORTANTE("Cortante"),CONTUSA("Contusa"),BALISTICA("Balística"),QUEIMADURA("Queimadura");

    String valor;

    NaturezaLesao(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
