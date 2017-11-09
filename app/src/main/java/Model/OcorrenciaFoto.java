package Model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

/**
 * Created by Pefoce on 13/09/2017.
 */

public class OcorrenciaFoto extends SugarRecord<OcorrenciaFoto>{

    public OcorrenciaTransito getOcorrenciaTransito() {
        return ocorrenciaTransito;
    }

    public void setOcorrenciaTransito(OcorrenciaTransito ocorrenciaTransito) {
        this.ocorrenciaTransito = ocorrenciaTransito;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public OcorrenciaFoto() {
    }

    public OcorrenciaFoto(OcorrenciaTransito ocorrenciaTransito, Foto foto) {
        this.ocorrenciaTransito = ocorrenciaTransito;
        this.foto = foto;
    }

    @Expose
    OcorrenciaTransito ocorrenciaTransito;
    @Expose
    Foto foto;
}
