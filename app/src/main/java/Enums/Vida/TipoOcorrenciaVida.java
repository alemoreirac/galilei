package Enums.Vida;

/**
 * Created by Pefoce on 15/11/2017.
 */

public enum TipoOcorrenciaVida
{
    HOMICIDIO("Homicídio"),SUICIDIO("Suicídio");

    String valor;

    TipoOcorrenciaVida(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
