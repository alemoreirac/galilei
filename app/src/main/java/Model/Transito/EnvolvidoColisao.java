package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

/**
 * Created by Pefoce on 26/06/2018.
 */

public class EnvolvidoColisao extends SugarRecord
{

    @Expose
    EnvolvidoTransito envolvidoTransito;

    ColisaoTransito colisaoTransito;

    public EnvolvidoColisao(EnvolvidoTransito envolvido, ColisaoTransito colisao)
    {
        this.envolvidoTransito = envolvido;
        this.colisaoTransito = colisao;
    }

    public EnvolvidoColisao()
    {

    }

    public EnvolvidoTransito getEnvolvidoTransito()
    {
        return envolvidoTransito;
    }

    public void setEnvolvidoTransito(EnvolvidoTransito envolvidoTransito)
    {
        this.envolvidoTransito = envolvidoTransito;
    }

    public ColisaoTransito getOcorrenciaTransito()
    {
        return colisaoTransito;
    }

    public void setOcorrenciaTransito(ColisaoTransito ocorrenciaTransito)
    {
        this.colisaoTransito = ocorrenciaTransito;
    }
}
