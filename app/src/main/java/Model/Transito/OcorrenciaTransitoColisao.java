package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pefoce on 29/08/2017.
 */

public class OcorrenciaTransitoColisao extends SugarRecord
{
    public OcorrenciaTransito getOcorrenciaTransito() {
        return ocorrenciaTransito;
    }

    public void setOcorrenciaTransito(OcorrenciaTransito ocorrenciaTransito) {
        this.ocorrenciaTransito = ocorrenciaTransito;
    }

    public ColisaoTransito getColisaoTransito() {
        return colisaoTransito;
    }

    public void setColisaoTransito(ColisaoTransito colisaoTransito) {
        this.colisaoTransito = colisaoTransito;
    }

    public OcorrenciaTransitoColisao() {

        dataInclusao = Calendar.getInstance().getTime();
    }

    public OcorrenciaTransitoColisao(OcorrenciaTransito ocorrenciaTransito, ColisaoTransito colisaoTransito) {
        this.ocorrenciaTransito = ocorrenciaTransito;
        this.colisaoTransito = colisaoTransito;
    }

    @Expose
    OcorrenciaTransito ocorrenciaTransito;

    @Expose
    ColisaoTransito colisaoTransito;

    @Expose
    Date dataInclusao;
}
