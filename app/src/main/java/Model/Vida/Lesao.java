package Model.Vida;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;

import Enums.Vida.LocalizacaoLesao;
import Enums.Vida.NaturezaLesao;
import Enums.Vida.ParteCorpo;
import Enums.Vida.Secao;

/**
 * Created by Pefoce on 20/11/2017.
 */

public class Lesao extends SugarRecord
{
    @Expose
    Date dataInclusao;
    @Expose
    LocalizacaoLesao localizacaoLesao;
    @Expose
    boolean compatibilidade;
    @Expose
    NaturezaLesao natureza;
    @Expose
    Secao secaoLesao;
    @Expose
    ParteCorpo parteCorpo;

    public Lesao()
    {
        dataInclusao = Calendar.getInstance().getTime();
    }

    public ParteCorpo getParteCorpo()
    {
        return parteCorpo;
    }

    public void setParteCorpo(ParteCorpo parteCorpo)
    {
        this.parteCorpo = parteCorpo;
    }

    public LocalizacaoLesao getLocalizacaoLesao()
    {
        return localizacaoLesao;
    }

    public void setLocalizacaoLesao(LocalizacaoLesao localizacaoLesao)
    {
        this.localizacaoLesao = localizacaoLesao;
    }

    public boolean isCompatibilidade()
    {
        return compatibilidade;
    }

    public void setCompatibilidade(boolean compatibilidade)
    {
        this.compatibilidade = compatibilidade;
    }

    public NaturezaLesao getNatureza()
    {
        return natureza;
    }

    public void setNatureza(NaturezaLesao natureza)
    {
        this.natureza = natureza;
    }

    public Secao getSecaoLesao()
    {
        return secaoLesao;
    }

    public void setSecaoLesao(Secao secaoLesao)
    {
        this.secaoLesao = secaoLesao;
    }

    @Override
    public String toString()
    {
        if (getSecaoLesao() != null && getNatureza() != null && getLocalizacaoLesao() != null)
        {
            if (compatibilidade)
                return "Lesão do tipo " + getNatureza().getValor().toLowerCase() + ", na seção " + getSecaoLesao().getValor().toLowerCase() + ", na parte " + getLocalizacaoLesao().getValor().toLowerCase() + ", compatível com a ocorrência.";
            else
                return "Lesão do tipo " + getNatureza().getValor().toLowerCase() + ", na seção " + getSecaoLesao().getValor().toLowerCase() + ", na parte " + getLocalizacaoLesao().getValor().toLowerCase() + ", incompatível com a ocorrência.";
        } else
            return "Lesão de tipo não definido";
    }

    public String exportarLaudo()
    {
        String lesao = "Do tipo ";

        if (this.natureza != null)
            lesao += natureza.getValor().toLowerCase();

        if (this.localizacaoLesao != null)
            lesao += ", no terço " + localizacaoLesao.getValor().toLowerCase();

        if (this.compatibilidade)
            lesao += " compatível com a colisão; ";

        else
            lesao += " incompatível com a colisão; ";

        return lesao;
    }
}
