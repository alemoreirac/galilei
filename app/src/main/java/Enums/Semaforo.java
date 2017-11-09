package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum Semaforo
{
    AUSENTE("Ausente")
    ,ATIVO("Ativo")
    ,INATIVO("Inativo");

    String valor;

    public String getValor() {
        return valor;
    }

    Semaforo(String s) {
        this.valor = s;
    }
}
