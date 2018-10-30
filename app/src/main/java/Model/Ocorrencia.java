package Model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Enums.TipoOcorrencia;
import Model.Transito.OcorrenciaTransito;
import Model.Vida.OcorrenciaVida;
import Util.TempoUtil;

/**
 * Created by Pefoce on 05/10/2017.
 */

public class Ocorrencia extends SugarRecord  implements Comparable<Ocorrencia> {

    TipoOcorrencia tipoOcorrencia;

    OcorrenciaTransito ocorrenciaTransito;

    OcorrenciaVida ocorrenciaVida;

    private Usuario perito;

    private Date dataChamado;

    private Date dataInclusao;

    public Ocorrencia()
    {
        dataInclusao = Calendar.getInstance().getTime();
        this.dataChamado= TempoUtil.stringToDate("01/01/2000");
    }



    public String getDataChamado_MesExtenso()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ocorrenciaTransito.getDataAtendimento());

        switch(calendar.MONTH)
        {
            case 1:
                return calendar.DAY_OF_MONTH + " de Janeiro de " + calendar.YEAR;
            case 2:
                return calendar.DAY_OF_MONTH + " de Fevereiro de " + calendar.YEAR;
            case 3:
                return calendar.DAY_OF_MONTH + " de Março de " + calendar.YEAR;
            case 4:
                return calendar.DAY_OF_MONTH + " de Abril de " + calendar.YEAR;
            case 5:
                return calendar.DAY_OF_MONTH + " de Maio de " + calendar.YEAR;
            case 6:
                return calendar.DAY_OF_MONTH + " de Junho de " + calendar.YEAR;
            case 7:
                return calendar.DAY_OF_MONTH + " de Julho de " + calendar.YEAR;
            case 8:
                return calendar.DAY_OF_MONTH + " de Agosto de " + calendar.YEAR;
            case 9:
                return calendar.DAY_OF_MONTH + " de Setembro de " + calendar.YEAR;
            case 10:
                return calendar.DAY_OF_MONTH + " de Outubro de " + calendar.YEAR;
            case 11:
                return calendar.DAY_OF_MONTH + " de Novembro de " + calendar.YEAR;
            case 12:
                return calendar.DAY_OF_MONTH + " de Dezembro de " + calendar.YEAR;
        }

        return "";
    }

    public TipoOcorrencia getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    public void setTipoOcorrencia(TipoOcorrencia tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }


    public void setDataChamado(String s)
    {
        final Calendar c  = Calendar.getInstance();
        c.setTime(TempoUtil.stringToDate(s));

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        final Calendar cSave = Calendar.getInstance();
        cSave.setTime(this.getDataChamado());
        cSave.set(Calendar.YEAR,year);
        cSave.set(Calendar.MONTH,month);
        cSave.set(Calendar.DAY_OF_MONTH,day);

        this.dataChamado= cSave.getTime();
    }

    public OcorrenciaTransito getOcorrenciaTransito() {
        return ocorrenciaTransito;
    }

    public void setOcorrenciaTransito(OcorrenciaTransito ocorrenciaTransito) {
        this.ocorrenciaTransito = ocorrenciaTransito;
    }

    public Date getDataChamado() {
        return dataChamado;
    }

    public void setDataChamado(Date dataChamado) {
        this.dataChamado = dataChamado;
    }
    public Usuario getPerito() {
        return perito;
    }

    public void setPerito(Usuario perito) {
        this.perito = perito;
    }

    public String getHoraChamadoString()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(this.dataChamado);
    }
    public String getDataHoraFormatada()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return timeFormat.format(this.dataChamado);
    }

    public OcorrenciaVida getOcorrenciaVida()
    {
        return ocorrenciaVida;
    }

    public void setOcorrenciaVida(OcorrenciaVida ocorrenciaVida)
    {
        this.ocorrenciaVida = ocorrenciaVida;
    }

    @Override
    public int compareTo(@NonNull Ocorrencia o)
    {
        return getDataChamado().compareTo(o.getDataChamado())*-1;
    }
    @Override
    public String toString()
    {
        return this.getId().toString();
    }
}
