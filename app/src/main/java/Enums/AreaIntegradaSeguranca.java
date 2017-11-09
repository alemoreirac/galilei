package Enums;

/**
 * Created by Pefoce on 16/08/2017.
 */

public enum AreaIntegradaSeguranca
{
     AIS_1("AIS 1")
    ,AIS_2("AIS 2")
    ,AIS_3("AIS 3")
    ,AIS_4("AIS 4")
    ,AIS_5("AIS 5")
    ,AIS_6("AIS 6")
    ,AIS_7("AIS 7")
    ,AIS_8("AIS 8")
    ,AIS_9("AIS 9")
    ,AIS_10("AIS 10");

    String valor;

    public String getValor() {
        return valor;
    }


    AreaIntegradaSeguranca(String s) {
        this.valor = s;
    }
}
