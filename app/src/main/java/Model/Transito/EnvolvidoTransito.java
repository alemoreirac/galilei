package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Enums.DocumentoPessoa;
import Enums.Genero;
import Enums.Transito.LesaoTransito;
import Enums.Transito.TipoEnvolvidoTransito;
import Enums.Vida.PresencaEnvolvido;
import Util.TempoUtil;


/**
 * Created by Pefoce on 30/05/2017.
 */

public class EnvolvidoTransito extends SugarRecord
{
    @Expose
    String nome;
    @Expose
    DocumentoPessoa documentoTipo;
    @Expose
    String documentoValor;
    @Expose
    TipoEnvolvidoTransito tipoEnvolvido;
    @Expose
    PresencaEnvolvido presencaEnvolvido;
    @Expose
    Date nascimento;
    @Expose
    Genero genero;
    @Expose
    Date dataInclusao;
    @Expose
    LesaoTransito lesaoTransito;
    @Expose
    Veiculo veiculoEnvolvido;

    public Veiculo getVeiculoEnvolvido()
    {
        return veiculoEnvolvido;
    }

    public void setVeiculoEnvolvido(Veiculo veiculoEnvolvido)
    {
        this.veiculoEnvolvido = veiculoEnvolvido;
    }

    public LesaoTransito getLesaoTransito()
    {
        return lesaoTransito;
    }

    public void setLesaoTransito(LesaoTransito lesaoTransito)
    {
        this.lesaoTransito = lesaoTransito;
    }


    public Genero getGenero()
    {
        return genero;
    }

    public void setGenero(Genero genero)
    {
        this.genero = genero;
    }


    public EnvolvidoTransito()
    {
        dataInclusao = Calendar.getInstance().getTime();
        this.setLesaoTransito(LesaoTransito.LEVE);
        this.setNome("");
        this.setGenero(Genero.NAO_IDENTIFICADO);
        this.setTipoEnvolvido(TipoEnvolvidoTransito.PEDESTRE);
        this.nascimento = null;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public DocumentoPessoa getDocumentoTipo()
    {
        return documentoTipo;
    }

    public void setDocumentoTipo(DocumentoPessoa documentoTipo)
    {
        this.documentoTipo = documentoTipo;
    }


    public void setDataNascimentoString(String s)
    {
        final Calendar c = Calendar.getInstance();
        try
        {
            c.setTime(TempoUtil.stringToDate(s));
        } catch (Exception e)
        {
            this.nascimento = null;
            return;
        }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        final Calendar cSave = Calendar.getInstance();

        cSave.set(Calendar.YEAR, year);
        cSave.set(Calendar.MONTH, month);
        cSave.set(Calendar.DAY_OF_MONTH, day);

        this.nascimento = cSave.getTime();
    }

    public String getDocumentoValor()
    {
        return documentoValor;
    }

    public void setDocumentoValor(String documentoValor)
    {
        this.documentoValor = documentoValor;
    }


    public TipoEnvolvidoTransito getTipoEnvolvido()
    {
        return tipoEnvolvido;
    }

    public void setTipoEnvolvido(TipoEnvolvidoTransito tipoEnvolvido)
    {
        this.tipoEnvolvido = tipoEnvolvido;
    }

    public Date getNascimento()
    {
        return nascimento;
    }


    public String getNascimentoString()
    {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar c = Calendar.getInstance();

        if (this.getNascimento() != null)
            c.setTime(this.getNascimento());
        else
            return "--/--/----";

        return format.format(c.getTime());
    }

    public void setNascimento(Date nascimento)
    {
        this.nascimento = nascimento;
    }

    @Override
    public String toString()
    {
        if (this.lesaoTransito.equals(LesaoTransito.FATAL))
            return this.nome + "\n" + this.getNascimentoString() + "\n" + "Vítima Fatal";
        if (this.lesaoTransito.equals(LesaoTransito.GRAVE))
            return this.nome + "\n" + this.getNascimentoString() + "\n" + "Ferido Gravemente";
        if (this.lesaoTransito.equals(LesaoTransito.LEVE))
            return this.nome + "\n" + this.getNascimentoString() + "\n" + "Ferido Levemente";
        return "";
    }

    public PresencaEnvolvido getPresencaEnvolvido()
    {
        return presencaEnvolvido;
    }

    public void setPresencaEnvolvido(PresencaEnvolvido presencaEnvolvido)
    {
        this.presencaEnvolvido = presencaEnvolvido;
    }

    //    public String toStringDoc()
//    {
//        if(this.lesaoTransito.equals(LesaoTransito.FATAL))
//            return this.nome + " " + this.documentoTipo.getValor() + " " + this.documentoValor + " "+"Vítima Fatal";
//        if(this.lesaoTransito.equals(LesaoTransito.GRAVE))
//            return this.nome + " " + this.documentoTipo.getValor() + " " + this.documentoValor + " " +"Ferido Gravemente";
//        if(this.lesaoTransito.equals(LesaoTransito.LEVE))
//            return this.nome + " " + this.documentoTipo.getValor() + " " + this.documentoValor + " " +"Ferido Levemente";
//        return "";
//    }
}
