package Model;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 27/06/2017.
 */

public class OcorrenciaEndereco extends SugarRecord<OcorrenciaEndereco> {
    private OcorrenciaTransito ocorrenciaTransito;
    private EnderecoTransito enderecoTransito;

    public OcorrenciaEndereco() {
    }

    public OcorrenciaEndereco(OcorrenciaTransito ocorrenciaTransito, EnderecoTransito enderecoTransito) {
        this.ocorrenciaTransito = ocorrenciaTransito;
        this.enderecoTransito = enderecoTransito;
    }

    public OcorrenciaTransito getOcorrenciaTransito() {
        return ocorrenciaTransito;
    }

    public void setOcorrenciaTransito(OcorrenciaTransito ocorrenciaTransito) {
        this.ocorrenciaTransito = ocorrenciaTransito;
    }

    public EnderecoTransito getEnderecoTransito() {
        return enderecoTransito;
    }

    public void setEnderecoTransito(EnderecoTransito enderecoTransito) {
        this.enderecoTransito = enderecoTransito;
    }
}
