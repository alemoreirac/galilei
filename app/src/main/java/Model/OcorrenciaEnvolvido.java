package Model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

/**
 * Created by Pefoce on 27/06/2017.
 */

public class OcorrenciaEnvolvido extends SugarRecord<OcorrenciaEnvolvido> {

    @Expose
    private OcorrenciaTransito ocorrenciaTransito;
    @Expose
    private EnvolvidoTransito envolvidoTransito;

    public OcorrenciaEnvolvido() {
    }

    public OcorrenciaEnvolvido(OcorrenciaTransito ocorrenciaTransito, EnvolvidoTransito envolvidoTransito) {
        this.ocorrenciaTransito = ocorrenciaTransito;
        this.envolvidoTransito = envolvidoTransito;
    }

    public OcorrenciaTransito getOcorrenciaTransito() {
        return ocorrenciaTransito;
    }

    public void setOcorrenciaTransito(OcorrenciaTransito ocorrenciaTransito) {
        this.ocorrenciaTransito = ocorrenciaTransito;
    }

    public EnvolvidoTransito getEnvolvidoTransito() {
        return envolvidoTransito;
    }

    public void setEnvolvidoTransito(EnvolvidoTransito EnvolvidoTransito) {
        this.envolvidoTransito = EnvolvidoTransito;
    }
}

