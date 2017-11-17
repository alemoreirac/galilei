package Enums.Vida;

/**
 * Created by Pefoce on 17/11/2017.
 */
public enum UnidadeTempo
{
    MINUTO("Minuto"),HORA("Hora"),DIA("Dia"),SEMANA("Semana"),MES("Mês"),ANO("Ano");

    String valor;

    UnidadeTempo(String s)
    {
        this.valor = s;
    }

    public String getValor()
    {
        return this.valor;
    }
}
