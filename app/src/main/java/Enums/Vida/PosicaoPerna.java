package Enums.Vida;

/**
 * Created by Pefoce on 17/11/2017.
 */

public enum PosicaoPerna
{
    ESTENDIDO("Estendido"),FLEXIONADO("Flexionado"),SEMIFLEXIONADO("Semiflexionado");

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
