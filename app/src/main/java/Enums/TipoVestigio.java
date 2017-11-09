package Enums;

/**
 * Created by Pefoce on 12/10/2017.
 */

public enum TipoVestigio
{
    FRAGMENTOS_VITREOS("Fragmentos vítreos")
    ,FRENAGEM("Frenagem")
    ,FRAGMENTOS_METALICOS("Fragmentos metálicos")
    ,DERRAPAGEM("Derrapagem")
    ,DERRAPAGEM_CURVA("Derrapagem em curva")
    ,SULCAGEM("Sulcagem")
    ,SANGUE("Marcas de sangue");

    String valor;

    TipoVestigio(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}


