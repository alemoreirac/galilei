package Model.Vida;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 10/01/2018.
 */

public class OcorrenciaEnvolvidoVida extends SugarRecord<OcorrenciaEnvolvidoVida>
{
    public OcorrenciaEnvolvidoVida()
    {

    }

    OcorrenciaVida ocorrenciaVida;
    EnvolvidoVida envolvidoVida;

    public OcorrenciaVida getOcorrenciaVida()
    {
        return ocorrenciaVida;
    }

    public void setOcorrenciaVida(OcorrenciaVida ocorrenciaVida)
    {
        this.ocorrenciaVida = ocorrenciaVida;
    }

    public EnvolvidoVida getEnvolvidoVida()
    {
        return envolvidoVida;
    }

    public void setEnvolvidoVida(EnvolvidoVida envolvidoVida)
    {
        this.envolvidoVida = envolvidoVida;
    }
}
