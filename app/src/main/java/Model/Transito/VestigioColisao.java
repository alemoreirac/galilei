package Model.Transito;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 16/10/2017.
 */

public class VestigioColisao extends SugarRecord
{

    VestigioTransito vestigio;
    ColisaoTransito colisaoTransito;

    public VestigioColisao(VestigioTransito vest, ColisaoTransito colisaoTransito)
    {
        this.vestigio = vest;
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

    public VestigioTransito getVestigio()
    {
        return vestigio;
    }

    public void setVestigio(VestigioTransito vestigio)
    {
        this.vestigio = vestigio;
    }
}
