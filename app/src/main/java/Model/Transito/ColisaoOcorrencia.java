package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

/**
 * Created by Pefoce on 01/09/2017.
 */

public class ColisaoOcorrencia extends SugarRecord<ColisaoOcorrencia>
{

    @Expose
    ColisaoTransito colisaoTransito;

    OcorrenciaTransito ocorrenciaTransito;

    public ColisaoOcorrencia(ColisaoTransito colisaoTransito, OcorrenciaTransito ocorrenciaTransito) {
        this.colisaoTransito = colisaoTransito;
        this.ocorrenciaTransito = ocorrenciaTransito;
    }

    public ColisaoOcorrencia() {

    }

    public ColisaoTransito getColisaoTransito() {
        return colisaoTransito;
    }

    public void setColisaoTransito(ColisaoTransito colisaoTransito) {
        this.colisaoTransito = colisaoTransito;
    }

    public OcorrenciaTransito getOcorrenciaTransito() {
        return ocorrenciaTransito;
    }

    public void setOcorrenciaTransito(OcorrenciaTransito ocorrenciaTransito) {
        this.ocorrenciaTransito = ocorrenciaTransito;
    }

}
