package Enums;

/**
 * Created by Pefoce on 24/11/2017.
 */

public enum TiposVestigioBiologico
{
    SANGUE("Sangue"),
    URINA("Urina"),
    SEMEN("Sêmen"),
    SUOR("Suor"),
    SALIVA("Saliva"),
    CABELO("Cabelo"),
    PELO("Pêlo"),
    OSSO("Osso"),
    MUSCULO("Músculo"),
    CARTILAGEM("Cartilagem"),
    FEZES("Fezes"),
    MEMBRO_AMPUTADO("Membro amputado"),
    UNHA("Unha"),
    VOMITO("Vômito"),
    VISCERA("Víscera"),
    DESCONHECIDO("Desconhecido");

    String valor;

    TiposVestigioBiologico(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
