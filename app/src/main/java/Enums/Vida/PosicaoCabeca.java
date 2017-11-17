package Enums.Vida;

/**
 * Created by Pefoce on 17/11/2017.
 */

public enum PosicaoCabeca
{
    APOIADA_SOLO("Apoiada ao solo"), SOBRE_BRACO("Sobre os braços");

    String valor;

    PosicaoCabeca(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
