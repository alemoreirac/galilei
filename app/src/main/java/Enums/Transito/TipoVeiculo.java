package Enums.Transito;

/**
 * Created by Pefoce on 30/05/2017.
 */
public enum TipoVeiculo
{
     CARRO("Carro")
    ,MOTOCICLETA("Motocicleta")
    ,MOTONETA("Motoneta")
    ,CICLOMOTOR("Ciclomotor")
    ,BICICLETA("Bicicleta")
    ,CAMINHAO("Caminhão")
    ,ONIBUS("Ônibus")
    ,PICAPE("Picape")
    ,CAMINHONETE("Caminhonete")
    ,CAMINHONETA("Caminhoneta")
    ,UTILITARIO("Utilitário")
    ,CARROCA("Carroça")
    ,CHARRETE("Charrete");

    String valor;

    public String getValor() {
        return valor;
    }

    TipoVeiculo(String s) {
        this.valor = s;
    }
}
