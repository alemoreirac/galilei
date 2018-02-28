package Enums;

/**
 * Created by Pefoce on 24/11/2017.
 */
public enum LocalVeiculo
{
    BANCO_MOTORISTA("Banco do motorista"),BANCO_CARONA("Banco do carona"), BANCO_TRAS("Banco de trás"),BAGAGEIRO("Bagageiro"),CABINE_ESTENDIDA("Cabine estendida");

    String valor;

    LocalVeiculo(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
