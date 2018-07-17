package Enums.Vida;

/**
 * Created by Pefoce on 15/11/2017.
 */

public enum TipoMorte
{
    HOMICIDIO("Homicídio")
    ,SUICIDIO("Suicídio")
    ,AFOGAMENTO("Afogamento")
    ,ACIDENTE("Acidente")
    ,MORTE_NATURAL("Morte natural")
    ,MORTE_SUSPEITA("Morte suspeita")
    ,ACHADO_DE_CADAVER("Achado de cadáver")
    ,ACHADO_DE_OSSADA("Achado de ossada")
    ,ACHADO_DE_FETO("Achado de feto");

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
