package Enums.Vida;

/**
 * Created by Pefoce on 15/11/2017.
 */

public enum TipoMorte
{
    HOMICIDIO("Homicídio"),SUICIDIO("Suicídio"),AFOGAMENTO("Afogamento"),ACIDENTE("Acidente"),MORTE_NATURAL("Morte natural"),MORTE_NAO_IDENTIFICADA("Morte não identificada");

    String valor;

    TipoMorte(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
