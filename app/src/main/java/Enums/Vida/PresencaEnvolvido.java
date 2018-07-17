package Enums.Vida;

/**
 * Created by Pefoce on 08/06/2018.
 */

public enum PresencaEnvolvido
{
    PRESENTE("Presente"),EVADIDO("Evadido(a)"),ENCAMINHADO_HOSPITAL("Encaminhado(a) ao Hospital")
    ,ENCAMINHADO_DELEGACIA("Encaminhado(a) à Delegacia");

    String valor;

    PresencaEnvolvido(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
