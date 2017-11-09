package Model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

/**
 * Created by Pefoce on 29/08/2017.
 */

public class OcorrenciaColisao extends SugarRecord<OcorrenciaColisao>
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

    public OcorrenciaColisao() {
    }

    public OcorrenciaColisao(OcorrenciaTransito ocorrenciaTransito, ColisaoTransito colisaoTransito) {
        this.ocorrenciaTransito = ocorrenciaTransito;
        this.colisaoTransito = colisaoTransito;
    }

    @Expose
    OcorrenciaTransito ocorrenciaTransito;

    @Expose
    ColisaoTransito colisaoTransito;
}
