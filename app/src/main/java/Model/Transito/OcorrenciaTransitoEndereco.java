package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

/**
 * Created by Pefoce on 27/06/2017.
 */

public class OcorrenciaTransitoEndereco extends SugarRecord
{


    @Expose
    private OcorrenciaTransito ocorrenciaTransito;
    @Expose
    private EnderecoTransito endereco;

    public OcorrenciaTransito getOcorrencia() {
        return ocorrenciaTransito;
    }

    public void setOcorrencia(OcorrenciaTransito ocorrencia) {
        this.ocorrenciaTransito = ocorrencia;
    }

    public EnderecoTransito getEnderecoTransito() {
        return endereco;
    }

    public void setEndereco(EnderecoTransito endereco_transito) {
        this.endereco = endereco_transito;
    }


    public OcorrenciaTransitoEndereco() {
    }


    public OcorrenciaTransitoEndereco(OcorrenciaTransito ot,EnderecoTransito et) {
        this.endereco = et;
        this.ocorrenciaTransito = ot;
    }

}
