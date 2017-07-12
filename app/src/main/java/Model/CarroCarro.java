package Model;

import com.orm.SugarRecord;

import Enums.OrientacaoGeografica;
import Enums.TipoColisao;

/**
 * Created by Pefoce on 06/06/2017.
 */
public class CarroCarro extends SugarRecord<CarroCarro> {


    Long Carro_ID;
    Long Vitima_ID;


    TipoColisao tipoColisao;



    OrientacaoGeografica culpadoInicio;
    OrientacaoGeografica culpadoDestino;
    OrientacaoGeografica vitimaInicio;


    OrientacaoGeografica vitimaDestino;

    public TipoColisao getTipoColisao() {
        return tipoColisao;
    }

    public void setTipoColisao(TipoColisao tipoColisao) {
        this.tipoColisao = tipoColisao;
    }



    public Long getCarro_ID() {
        return Carro_ID;
    }

    public void setCarro_ID(Long carro_ID) {
        Carro_ID = carro_ID;
    }

    public Long getVitima_ID() {
        return Vitima_ID;
    }

    public void setVitima_ID(Long vitima_ID) {
        Vitima_ID = vitima_ID;
    }


    public OrientacaoGeografica getCulpadoInicio() {
        return culpadoInicio;
    }

    public void setCulpadoInicio(OrientacaoGeografica culpadoInicio) {
        this.culpadoInicio = culpadoInicio;
    }

    public OrientacaoGeografica getCulpadoDestino() {
        return culpadoDestino;
    }

    public void setCulpadoDestino(OrientacaoGeografica culpadoDestino) {
        this.culpadoDestino = culpadoDestino;
    }

    public OrientacaoGeografica getVitimaInicio() {
        return vitimaInicio;
    }

    public void setVitimaInicio(OrientacaoGeografica vitimaInicio) {
        this.vitimaInicio = vitimaInicio;
    }

    public OrientacaoGeografica getVitimaDestino() {
        return vitimaDestino;
    }

    public void setVitimaDestino(OrientacaoGeografica vitimaDestino) {
        this.vitimaDestino = vitimaDestino;
    }
}
