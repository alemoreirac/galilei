package Model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

/**
 * Created by Pefoce on 17/07/2017.
 */
public class OcorrenciaVeiculo extends SugarRecord<OcorrenciaVeiculo> {

    @Expose
    private OcorrenciaTransito ocorrenciaTransito;
    @Expose
    private Veiculo veiculo;


    public OcorrenciaTransito getOcorrenciaTransito() {
        return ocorrenciaTransito;
    }

    public void setOcorrenciaTransito(OcorrenciaTransito ocorrenciaTransito) {
        this.ocorrenciaTransito = ocorrenciaTransito;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }


    public OcorrenciaVeiculo() {
    }

    public OcorrenciaVeiculo(OcorrenciaTransito ocorrenciaTransito, Veiculo Veiculo) {
        this.ocorrenciaTransito = ocorrenciaTransito;
        this.veiculo = Veiculo;
    }


}
