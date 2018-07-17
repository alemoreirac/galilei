package Enums.Vida;

/**
 * Created by Pefoce on 17/11/2017.
 */

public enum PosicaoBraco
{
    ESTENDIDO("Estendido"),FLEXIONADO("Flexionado"),SEMIFLEXIONADO("Semiflexionado");

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
