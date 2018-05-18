package Model.Vida;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import Model.Foto;

/**
 * Created by Pefoce on 03/01/2018.
 */


public class OcorrenciaVidaFoto extends SugarRecord
{

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public OcorrenciaVida getOcorrenciaVida()
    {
        return ocorrenciaVida;
    }

    public void setOcorrenciaVida(OcorrenciaVida ocorrenciaVida)
    {
        this.ocorrenciaVida = ocorrenciaVida;
    }

    public OcorrenciaVidaFoto(OcorrenciaVida ocorrenciaVida, Foto foto)
    {
        this.ocorrenciaVida = ocorrenciaVida;
        this.foto = foto;
    }

    public OcorrenciaVidaFoto()
    {
    }

    @Expose
    OcorrenciaVida ocorrenciaVida;
    @Expose
    Foto foto;
}

