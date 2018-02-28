package Enums.Transito;

/**
 * Created by Pefoce on 05/06/2017.
 */

public enum SetorDano {

    PAM("Porção Anterior Mediana"),
    PAD("Porção Anterior Direita"),
    PAE("Porção Anterior Esquerda"),

    AAD("Ângulo Anterior Direito"),
    LAD("Lateral Anterior Direita"),
    LMD("Lateral Mediana Direita"),
    LPD("Lateral Posterior Direita"),
    APD("Ângulo Posterior Direito"),

    PPD("Porção Posterior Direita"),
    PPM("Porção Posterior Mediana"),
    PPE("Porção Posterior Esquerda"),

    APE("Ângulo Posterior Esquerdo"),
    LPE("Lateral Posterior Esquerda"),
    LME("Lateral Mediana Esquerda"),
    LAE("Lateral Anterior Esquerda"),
    AAE("Ângulo Anterior Esquerdo");



    String abreviacao;

    String valor;

    public String getValor() {
        return valor;
    }

    public String getAbreviacao(){return abreviacao;}

    SetorDano(String s)
    {
    this.valor = s;

    }


}
