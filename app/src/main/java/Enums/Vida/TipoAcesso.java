package Enums.Vida;

/**
 * Created by Pefoce on 15/11/2017.
 */

public enum TipoAcesso
{
    ACESSO("Acesso"), LOCAL("Próprio Local");

    String valor;

    public String getValor() {
        return valor;
    }


    TipoAcesso(String s) {
        this.valor = s;
    }

}
