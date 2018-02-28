package Enums;

/**
 * Created by Pefoce on 22/01/2018.
 */

public enum TipoArma
{
    REVOLVER("Revólver"),
    PISTOLA("Pistola"),
    PISTOLA_PRESSAO("Pistola de pressão"),
    PISTOLA_SINALIZACAO("Pistola de sinalização"),
    FUZIL("Fuzil"),
    RIFLE("Rifle"),
    ESPINGARDA_SOCADEIRA("Espingarda socadeira"),
    GARRUCHA("Garrucha"),
    ARCABUZ("Arcabuz"),
    ESPINGARDA("Espingarda"),
    ESCOPETA("Escopeta"),
    SUB_METRALHADORA("Sub-metralhadora"),
    METRALHADORA("Metralhadora"),
    ARMA_ARTESANAL("Arma artesanal"),
    ARMA_DECORATIVA("Arma decorativa"),
    BACAMARTE("Bacamarte"),
    OUTRO("Outro"),
    NAO_IDENTIFICADA("Não identificada");

    String valor;

    public String getValor() {
        return valor;
    }

    TipoArma(String s) {
        this.valor = s;
    }
}
