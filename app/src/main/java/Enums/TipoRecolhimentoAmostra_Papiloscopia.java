package Enums;

/**
 * Created by Pefoce on 22/01/2018.
 */

public enum TipoRecolhimentoAmostra_Papiloscopia
{
    DECALQUE_FITA("Decalque - Fita"),
    DECALQUE_SUPORTE_ARTICULADO("Decalque - Suporte articulado"),
    MIDIA("Mídia");

    String valor;

    TipoRecolhimentoAmostra_Papiloscopia(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
