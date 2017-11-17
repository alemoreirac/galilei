package Enums;

/**
 * Created by Pefoce on 15/11/2017.
 */

public enum Meteorologia
{
    TEMPO_ABERTO("Céu Sem Nuvens"), CHUVA("Chuva"),NEBLINA("Neblina"),SERENAGEM("Serenagem"),GRANIZO("Chuva de Granizo"),NEVE("Neve");

    String valor;

    public String getValor() {
        return valor;
    }


    Meteorologia(String s) {
        this.valor = s;
    }

}
