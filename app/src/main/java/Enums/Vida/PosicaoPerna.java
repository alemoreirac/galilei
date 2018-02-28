package Enums.Vida;

/**
 * Created by Pefoce on 17/11/2017.
 */

public enum PosicaoPerna
{
    ESTENDIDO("Estendido"),DOBRADO("Dobrado"),ACIMA_OUTRO("Por cima da outra perna"),BAIXO_OUTRO("Por baixo da outra perna");

    String valor;

    PosicaoPerna(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
