package Enums.Transito;

/**
 * Created by Pefoce on 30/05/2017.
 */
public enum TipoVeiculo
{
    CARRO("Carro")
    ,MOTO("Moto")
    ,CAMINHAO("Caminhão")
    ,ONIBUS("Ônibus");

    String valor;

    public String getValor() {
        return valor;
    }

    TipoVeiculo(String s) {
        this.valor = s;
    }
}
