package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */
public enum TipoVeiculo {
    MOTO("Moto")
    ,CARRO("Carro")
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
