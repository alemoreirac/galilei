package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

/**
 * Created by Pefoce on 17/07/2017.
 */
public class OcorrenciaTransitoVeiculo extends SugarRecord

{

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


    public OcorrenciaTransitoVeiculo() {
    }

    public OcorrenciaTransitoVeiculo(OcorrenciaTransito ocorrenciaTransito, Veiculo Veiculo) {
        this.ocorrenciaTransito = ocorrenciaTransito;
        this.veiculo = Veiculo;
    }


}
