package Model.Vida;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 02/12/2017.
 */

public class VestigioVidaOcorrencia extends SugarRecord<VestigioVidaOcorrencia>
{
        VestigioVida vestigioVida;
        OcorrenciaVida ocorrenciaVida;

    public VestigioVidaOcorrencia()
    {

    }

    public VestigioVida getVestigioVida()
    {
        return vestigioVida;
    }

    public void setVestigioVida(VestigioVida vestigioVida)
    {
        this.vestigioVida = vestigioVida;
    }

    public OcorrenciaVida getOcorrenciaVida()
    {
        return ocorrenciaVida;
    }

    public void setOcorrenciaVida(OcorrenciaVida ocorrenciaVida)
    {
        this.ocorrenciaVida = ocorrenciaVida;
    }
}
