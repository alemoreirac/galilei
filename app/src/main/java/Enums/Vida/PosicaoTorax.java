package Enums.Vida;

/**
 * Created by Pefoce on 17/11/2017.
 */

public enum PosicaoTorax
{
    DECUBITO_DORSAL("Decúbito dorsal"),DECUBITO_FRONTAL("Decúbito frontal"),DECUBITO_LATERAL_ESQUERDO("Decúbito lateral esquerdo"),DECUBITO_LATERAL_DIREITO("Decúbito lateral direito");

    String valor;

    PosicaoTorax(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
