package Enums.Vida;

/**
 * Created by Pefoce on 20/11/2017.
 */
public enum LocalizacaoLesao
{
    FRONTAL("Frontal"),
    ESQUERDA("Esquerda"),
    DIREITA("Direita"),
    TRASEIRA("Traseira");

    String valor;

    public String getValor() {
        return valor;
    }

    LocalizacaoLesao(String s) {
        this.valor = s;
    }
}
