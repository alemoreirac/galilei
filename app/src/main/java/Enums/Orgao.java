package Enums;

/**
 * Created by Pefoce on 03/07/2017.
 */
public enum Orgao
{
    NP("Sem autoridade presente"),
    AM("Autarquia Municipal"),
    BO("Bombeiros"),
    DEM("DEMUTRAN"),
    PC("Polícia Civil"),
    PF("Polícia Federal"),
    PRF("Polícia Rodoviária Federal"),
    PRE("Polícia Rodoviária Estadual"),
    PM("Polícia Militar"),
    GM("Guarda Municipal");

    String valor;

    public String getValor() {
        return valor;
    }

    Orgao(String s) {
        this.valor = s;
    }

}
