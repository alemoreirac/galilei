package Enums.Transito;

/**
 * Created by Pefoce on 12/10/2017.
 */

public enum TipoVestigioTransito
{
    FRAGMENTOS_VITREOS("Fragmentos vítreos")
    ,FRENAGEM("Frenagem")
    ,FRAGMENTOS_METALICOS("Fragmentos metálicos")
    ,FRAGMENTOS_PLASTICOS("Fragmentos plásticos")
    ,DERRAPAGEM("Derrapagem")
    ,DERRAPAGEM_CURVA("Derrapagem em curva")
    ,SULCAGEM("Sulcagem")
    ,SANGUE("Marcas de sangue");

    String valor;

    TipoVestigioTransito(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}


