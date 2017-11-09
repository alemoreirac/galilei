package Model;

import com.orm.SugarRecord;

import Enums.TipoVestigio;

/**
 * Created by Pefoce on 12/10/2017.
 */

public class Vestigio extends SugarRecord<Vestigio>
{

    private TipoVestigio tipoVestigio;
    private Float Distancia;
    private Float Area;
    private boolean determinante;


    public TipoVestigio getTipoVestigio()
    {
        return tipoVestigio;
    }

    public void setTipoVestigio(TipoVestigio tipoVestigio)
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

    public Vestigio()
    {
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
            vestigio +=  ", distância: " + this.getDistancia().toString() + "m";
        if(getArea()!=null)
            vestigio +=  ", área: " + this.getArea().toString() + "m²";

        if(this.determinante)
            vestigio += ", determina o local do acidente.";
        else
            vestigio += ", não determina o local do acidente.";

        return vestigio;
    }
}
