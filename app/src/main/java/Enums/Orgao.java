package Enums;

/**
 * Created by Pefoce on 03/07/2017.
 */
public enum Orgao
{
    AM("Autarquia Municipal"), PRF("Polícia Rodoviária Federal"), PRE("Polícia Rodoviária Estadual"),PM("Polícia Militar"),GM("Guarda Municipal");

    String valor;

    public String getValor() {
        return valor;
    }


    Orgao(String s) {
        this.valor = s;
    }

}
