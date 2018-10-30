package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;

import Enums.Transito.TipoVestigioTransito;

/**
 * Created by Pefoce on 12/10/2017.
 */

public class VestigioTransito extends SugarRecord

{
    @Expose
    private TipoVestigioTransito tipoVestigio;
    @Expose
    private Float Distancia;
    @Expose
    private Float Area;
    @Expose
    private boolean determinante;
    @Expose
    Date dataInclusao;


    public TipoVestigioTransito getTipoVestigio()
    {
        return tipoVestigio;
    }

    public void setTipoVestigio(TipoVestigioTransito tipoVestigio)
    {
        this.tipoVestigio = tipoVestigio;
    }

    public Float getDistancia()
    {
        return Distancia;
    }

    public void setDistancia(Float distancia)
    {
        Distancia = distancia;
    }

    public Float getArea()
    {
        return Area;
    }

    public void setArea(Float area)
    {
        Area = area;
    }

    public boolean isDeterminante()
    {
        return determinante;
    }

    public void setDeterminante(boolean determinante)
    {
        this.determinante = determinante;
    }

    public VestigioTransito()
    {
        dataInclusao = Calendar.getInstance().getTime();

        setArea(0f);
        setDistancia(0f);
        setDeterminante(false);
    }

    @Override
    public String toString()
    {
        String vestigio = "";

        if(tipoVestigio!=null)
        vestigio += tipoVestigio.getValor();

        if(getDistancia()!=null && getDistancia()>0.0)
            if(getDistancia()!=0f)
            vestigio +=  ", distância: " + this.getDistancia().toString() + "m";
        if(getArea()!=null)
            if(getArea()!=0f)
            vestigio +=  ", área: " + this.getArea().toString() + "m²";

        if(this.determinante)
            vestigio += ", determina o local do acidente.";
        else
            vestigio += ", não determina o local do acidente.";

        return vestigio;
    }
}
