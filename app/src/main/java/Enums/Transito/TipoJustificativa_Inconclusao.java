package Enums.Transito;

/**
 * Created by Pefoce on 16/10/2017.
 */

public enum TipoJustificativa_Inconclusao
{
    POSICAO_ALTERADA("Posição final dos veículos alterada")
    ,VESTIGIOS_INSUFICIENTES("Vestígios insuficientes para determinar o fato")
    ,LOCAL_VIOLADO("Local violado")
    ,CONDUTOR_EVADIU("Condutor se evadiu")
    ,ENVOLVIDO_EVADIU("Envolvido se evadiu");

    String valor;

    public String getValor()
    {
        return this.valor;
    }

     TipoJustificativa_Inconclusao(String s)
    {
        this.valor = s;
    }
}
