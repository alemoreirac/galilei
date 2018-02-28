package Enums;

/**
 * Created by Pefoce on 27/01/2018.
 */

public enum SecaoImagem
{
    ANTERIOR_MASCULINO("Corpo anterior masculino"),
    ANTERIOR_FEMININO("Corpo anterior feminino"),
    POSTERIOR_MASCULINO("Corpo posterior masculino"),
    POSTERIOR_FEMININO("Corpo posterior feminino"),
    CABECA_MASCULINO_ESQUERDA("Cabeça masculina esquerda"),
    CABECA_FEMININO_ESQUERDA("Cabeça feminina esquerda"),
    CABECA_MASCULINO_DIREITA("Cabeça masculina direita"),
    CABECA_FEMININO_DIREITA("Cabeça feminina direita");


    String valor;

    public String getSecaoValor()
    {
        switch (valor)
        {
            case "Cabeça feminina direita":
            case "Cabeça masculina direita":
                return "_Cabeca_Direita";

            case "Cabeça feminina esquerda":
            case "Cabeça masculina esquerda":
                return "_Cabeca_Esquerda";

            case "Corpo posterior masculino":
            case "Corpo posterior feminino":
                return "_Posterior";

            case "Corpo anterior masculino":
            case "Corpo anterior feminino":
                return "_Anterior";
        }
        return "";
    }

    public String getValor()
    {
        return valor;
    }

    SecaoImagem(String s)
    {
        this.valor = s;
    }
}
