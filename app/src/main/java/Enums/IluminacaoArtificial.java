package Enums;

/**
 * Created by Pefoce on 16/11/2017.
 */

public enum IluminacaoArtificial
{
    ILUMINACAO_NATURAL_BOA("Iluminação natural boa"),ILUMINACAO_NATURAL_RUIM("Iluminação natural ruim"),ILUMINACAO_ARTIFICIAL_BOA("Iluminação artificial boa"),ILUMINACAO_ARTIFICIAL_RUIM("Iluminação artificial ruim");

    String valor;

    public String getValor()
    {
        return valor;
    }

    IluminacaoArtificial(String s)
    {
        this.valor = s;
    }
}
