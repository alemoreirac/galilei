package Model;

import com.orm.SugarRecord;

import Enums.OrientacaoGeografica;
import Enums.TipoColisao;

/**
 * Created by Pefoce on 06/06/2017.
 */

public class CarroVitima extends SugarRecord<CarroVitima> {

    Long Carro_ID;
    Long Vitima_ID;


    TipoColisao tipoColisao;

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


    public CarroVitima(Long carro_ID, Long vitima_ID) {
        Carro_ID = carro_ID;
        Vitima_ID = vitima_ID;
    }
}
