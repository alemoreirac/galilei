package Enums.Vida;

/**
 * Created by Pefoce on 17/11/2017.
 */

public enum PosicaoBraco
{
    ESTENDIDO("Estendido"),DOBRADO("Dobrado"),ACIMA_OUTRO("Por cima do outro braço"),BAIXO_OUTRO("Por baixo do outro braço"),ACIMA_PERNA_DIREITA("Acima da perna direita"),ACIMA_PERNA_ESQUERDA("Acima da perna esquerda");

    String valor;

    PosicaoBraco(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
