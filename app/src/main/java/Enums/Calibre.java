package Enums;

/**
 * Created by Pefoce on 22/01/2018.
 */

public enum Calibre
{
    PONTO_22_CURTO(".22 curto"),
    PONTO_22(".22"),
    PONTO_25_AUTO(".25 Auto"),
    PONTO_32_AUTO(".32 Auto"),
    PONTO_32_SWL(".32 S&WL"),
    PONTO_357(".357"),
    PONTO_38(".38"),
    PONTO_380(".380"),
    PONTO_40(".40"),
    PONTO_44(".44"),
    PONTO_45(".45"),
    CALIBRE_9MM("9mm"),
    CALIBRE_556("5.56"),
    CALIBRE_762("7.62"),
    CALIBRE_12("12"),
    CALIBRE_16("16"),
    CALIBRE_20("20"),
    CALIBRE_28("28"),
    CALIBRE_36("36"),
    NAO_IDENTIFICADO("Não identificado");

    String valor;

    public String getValor() {
        return valor;
    }

    Calibre(String s) {
        this.valor = s;
    }
}
