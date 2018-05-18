package Enums.Transito;

/**
 * Created by Pefoce on 02/05/2018.
 */

public enum TipoPropriedadeVeiculo
{
     PARTICULAR("Particular")
    ,EMPRESA("Empresa")
    ,ORGAO_PUBLICO("Órgão Público")
    ,ONIBUS("Ônibus");

    String valor;

    public String getValor()
    {
        return this.valor;
    }

    TipoPropriedadeVeiculo(String s)
    {
        this.valor = s;
    }
}
