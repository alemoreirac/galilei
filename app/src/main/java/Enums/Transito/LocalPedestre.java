package Enums.Transito;

/**
 * Created by Pefoce on 28/08/2017.
 */

public enum LocalPedestre
{
    SEM_FAIXA("Local sem faixa"),SOBRE_FAIXA("Pedestre na faixa"),PASSARELA("Passarela de pedestres"),PROXIMO_FAIXA("Próximo à faixa de pedestres"),PROXIMO_PASSARELA("Próximo à passarela de pedestres");

    String valor;

    public String getValor() {
        return valor;
    }

    LocalPedestre(String s) {
        this.valor = s;
    }
}
