package Model.Vida;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 10/01/2018.
 */

public class OcorrenciaVidaEndereco extends SugarRecord
{
    public OcorrenciaVidaEndereco()
    {

    }

    OcorrenciaVida ocorrenciaVida;

    EnderecoVida enderecoVida;

    public OcorrenciaVida getOcorrenciaVida()
    {
        return ocorrenciaVida;
    }

    public void setOcorrenciaVida(OcorrenciaVida ocorrenciaVida)
    {
        this.ocorrenciaVida = ocorrenciaVida;
    }

    public EnderecoVida getEnderecoVida()
    {
        return enderecoVida;
    }

    public void setEnderecoVida(EnderecoVida enderecoVida)
    {
        this.enderecoVida = enderecoVida;
    }
}
