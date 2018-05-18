package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import Enums.Transito.SetorDano;
import Enums.Transito.TercoDano;
import Enums.Transito.TipoDano;


public class Dano extends SugarRecord
{
    @Expose
    public TipoDano tipo;
    @Expose
    public TercoDano terco;
    @Expose
    public SetorDano setor;
    @Expose
    public Boolean Compatibilidade;


    //TODO trocar para varios tipos de dano

    public TipoDano getTipo()
    {
        return tipo;
    }

    public void setTipo(TipoDano tipo)
    {
        this.tipo = tipo;
    }

    public TercoDano getTerco()
    {
        return terco;
    }

    public void setTerco(TercoDano terco)
    {
        this.terco = terco;
    }

    public SetorDano getSetor()
    {
        return setor;
    }

    public void setSetor(SetorDano setor)
    {
        this.setor = setor;
    }

    public Boolean getCompatibilidade()
    {
        return Compatibilidade;
    }

    public void setCompatibilidade(Boolean compatibilidade)
    {
        Compatibilidade = compatibilidade;
    }


    public Dano()
    {
    }

    public Dano(TipoDano tipod, TercoDano tercod, SetorDano setord, Boolean compatibilidade)
    {
        this.setor = setord;
        this.tipo = tipod;
        this.terco = tercod;
        this.Compatibilidade = compatibilidade;
    }

    @Override
    public String toString()
    {
        String dano = "Dano ";

        if (this.tipo != null)
            dano += tipo.getValor() + " ";

        if (this.terco != null)
            dano += "no terço " + terco.getValor() + " ";

        if (this.setor != null)
            dano += "no setor " + setor.getValor()+". ";

        if (this.getCompatibilidade())
            dano += "Compatível com a colisão.";
        else
            dano += "Incompatível com a colisão.";

        return dano;
    }

    public String exportarLaudo()
    {
        String dano = "Do tipo ";

        if (this.tipo != null)
            dano += tipo.getValor();

        if (this.terco != null)
            dano += ", no terço " + terco.getValor();

        if (this.setor != null)
            dano += "no Setor " + setor.getValor() + ". ";

        if (this.getCompatibilidade())
            dano += "Compatível com a colisão.";

        else
            dano += "Incompatível com a colisão.";

        return dano;
    }
}

