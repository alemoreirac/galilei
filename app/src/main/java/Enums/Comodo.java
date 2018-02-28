package Enums;

/**
 * Created by Pefoce on 15/11/2017.
 */

public enum Comodo
{
    AREA_SERVICO("Área de Serviço"),
    BANHEIRO("Banheiro"),
    COPA("Copa"),
    CORREDOR("Corredor")
    ,COZINHA("Cozinha"),
    ESCRITORIO("Escritório")
    ,GABINETE("Gabinete"),
    GARAGEM("Garagem")
    ,PORAO("Porão")
    ,PORTARIA("Portaria")
    ,QUARTO("Quarto"),
    SALA("Sala de Estar")
    ,TERRACO("Terraço"),

    VARANDA("Varanda");

    String valor;

    public String getValor() {
        return valor;
    }


    Comodo(String s) {
        this.valor = s;
    }

}
