package Enums.Vida;

/**
 * Created by Pefoce on 24/11/2017.
 */

public enum TipoVestigioVida
{
    BIOLOGICO("Biológico"),
    MUNICAO("Munição"),
    ARMA_DE_FOGO("Arma de fogo"),
    VESTIGIO_PAPILOSCOPICO("Vestígio papiloscópico"),
    DOCUMENTO("Documento"),
    OUTRO("Outro");

    String valor;

    TipoVestigioVida(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}