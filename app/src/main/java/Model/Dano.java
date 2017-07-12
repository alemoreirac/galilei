package Model;

import com.orm.SugarRecord;

import Enums.SetorDano;
import Enums.TercoDano;
import Enums.TipoDano;


public class Dano extends SugarRecord<Dano>
{
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

     public TipoDano tipo;
     public TercoDano terco;
     public SetorDano setor;
     public Boolean Compatibilidade;


     public Dano()
     { }

     public Dano(TipoDano tipod, TercoDano tercod,SetorDano setord, Boolean compatibilidade)
     {
         this.setor = setord;
         this.tipo = tipod;
         this.terco = tercod;
         this.Compatibilidade = compatibilidade;
     }

 }

