package Model.Transito;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 16/10/2017.
 */

public class VestigioColisao extends SugarRecord
    {

    Long vestigioId;
    ColisaoTransito colisaoTransito;

    public VestigioColisao(Long vId, ColisaoTransito colisaoTransito)
    {
        this.vestigioId = vId;
        this.colisaoTransito = colisaoTransito;
    }

    public VestigioColisao()
    {
    }


    public ColisaoTransito getColisaoTransito()
    {
        return colisaoTransito;
    }

    public void setColisaoTransito(ColisaoTransito colisaoTransito)
    {
        this.colisaoTransito = colisaoTransito;
    }

    public Long getVestigioId()
    {
        return vestigioId;
    }

    public void setVestigioId(Long vestigioId)
    {
        this.vestigioId = vestigioId;
    }
}
