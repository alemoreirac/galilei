package Enums.Transito;

/**
 * Created by Pefoce on 26/06/2017.
 */

public enum VeiculoSituacao
{
    PARADO("Parado")
    ,EM_TRANSITO("Em Trânsito")
    ,IMOBILIZADO("Imobilizado");


    String valor;

    public String getValor() {
        return valor;
    }

    VeiculoSituacao(String s) {
        this.valor = s;
    }
}
