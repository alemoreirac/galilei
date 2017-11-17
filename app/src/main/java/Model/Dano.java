package Model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import Enums.Transito.SetorDano;
import Enums.Transito.TercoDano;
import Enums.Transito.TipoDano;


public class Dano extends SugarRecord<Dano>
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

    public TipoDano getTipo() {
        return tipo;
    }

    public void setTipo(TipoDano tipo) {
        this.tipo = tipo;
    }

    public TercoDano getTerco() {
        return terco;
    }

    public void setTerco(TercoDano terco) {
        this.terco = terco;
    }

    public SetorDano getSetor() {
        return setor;
    }

    public void setSetor(SetorDano setor) {
        this.setor = setor;
    }

    public Boolean getCompatibilidade() {
        return Compatibilidade;
    }

    public void setCompatibilidade(Boolean compatibilidade) {
        Compatibilidade = compatibilidade;
    }


     public Dano()
     { }

     public Dano(TipoDano tipod, TercoDano tercod,SetorDano setord, Boolean compatibilidade)
     {
         this.setor = setord;
         this.tipo = tipod;
         this.terco = tercod;
         this.Compatibilidade = compatibilidade;
     }

    @Override
    public String toString()
    {
        if(this.getCompatibilidade())
            return "Dano "+ this.tipo.getValor() + "; Terço " + this.terco.getValor() + "; Compatível com a Colisão" + " - " + this.setor.getValor();

        else
            return "Dano "+ this.tipo.getValor() + "; Terço: " + this.terco.getValor() + "; Incompatível com a Colisão" + " - " + this.setor.getValor();
    }

    public String exportarLaudo()
    {
        if(this.getCompatibilidade())
            return "Do tipo "+ this.tipo.getValor() + ", situado na seção " + this.setor.getValor() + ", compatível com a Colisão.";
        else
            return "Do tipo "+ this.tipo.getValor() + ", situado na seção " + this.setor.getValor() + ", incompatível com a Colisão.";
    }
 }

