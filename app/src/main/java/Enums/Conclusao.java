package Enums;

/**
 * Created by Pefoce on 18/09/2017.
 */

public enum Conclusao {
    CONDUTOR_ISENTO("O condutor não teve responsabilidade no ocorrido"),
    CONDUTOR_DESATENTO("ao Condutor estar desatento às condições de tráfego à frente"),
    AVANCO_PREFERENCIAL("ao avanço de preferencial em cruzamento"),
    MUDANCA_FAIXA("à mudança de faixa definida pelo local dos danos"),
    CONVERSAO_INAPROPRIADA("à conversão em momento inapropriado"),
    RETORNO_INAPROPRIADO("à manobra de retorno em momento inapropriado"),
    ULTRAPASSAGEM_INDEVIDA("à ultrapassagem mal executada"),
    CONTRA_MAO("ao veiculo se deslocar na contra-mão");

    String valor;

    public String getValor() {
        return valor;
    }


    Conclusao(String s) {
        this.valor = s;
    }
}
