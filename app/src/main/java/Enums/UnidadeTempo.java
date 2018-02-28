package Enums;

/**
 * Created by Pefoce on 17/11/2017.
 */
public enum UnidadeTempo
{
    MINUTO("Minuto(s)"),HORA("Hora(s)"),DIA("Dia(s)"),SEMANA("Semana(s)"),MES("Mês(es)"),ANO("Ano(s)");

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
