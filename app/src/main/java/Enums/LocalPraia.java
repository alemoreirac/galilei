package Enums;

/**
 * Created by Pefoce on 24/11/2017.
 */

public enum LocalPraia
{
    FALESIAS("Falésias"),
    ROCHAS("Rochas"),
    AREIA("Areia"),
    DUNA("Duna"),
    MAR("Mar"),
    MANGUE("Mangue");
//    VEGETACAO_RASTEIRA("Vegetação rasteira"),
//    VEGETACAO_MEDIA("Vegetação média"),
//    VEGETACAO_ALTA("Vegetação alta")

    String valor;

    LocalPraia(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
