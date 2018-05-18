package Model.Vida;
import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import Enums.AreaIntegradaSeguranca;
import Enums.Orgao;
import Enums.PreservacaoLocal;
import Model.DocumentoOcorrencia;
import Util.TempoUtil;

import static Enums.AreaIntegradaSeguranca.AIS_1;

/**
 * Created by Pefoce on 15/11/2017.
 */

public class OcorrenciaVida extends SugarRecord
{
    private Long ocorrenciaID;

    private Date dataChamado;

    private Date dataAtendimento;


    private DocumentoOcorrencia documento;

    private String numIncidencia;

    private String delegado;

    private Orgao autoridadePresente;

    private String Viatura;

    private String Comandante;

    private AreaIntegradaSeguranca ais;

    private String orgaoOrigem;

    private String orgaoDestino;

    private PreservacaoLocal preservacaoLocal;

    public OcorrenciaVida()
    {
        this.ais = AIS_1;
        this.preservacaoLocal =PreservacaoLocal.NAO_PRESERVADO;
        this.Comandante = "";
        this.numIncidencia = "";
        this.documento = new DocumentoOcorrencia();
        this.ocorrenciaID = 0l;
        this.dataChamado = Calendar.getInstance().getTime();
        this.dataAtendimento =  Calendar.getInstance().getTime();
    }

    public Long getOcorrenciaID()
    {
        return ocorrenciaID;
    }

    public void setOcorrenciaID(Long ocorrenciaID)
    {
        this.ocorrenciaID = ocorrenciaID;
    }

    public Date getDataChamado()
    {
        return dataChamado;
    }

    public void setDataChamado(Date dataChamado)
    {
        this.dataChamado = dataChamado;
    }

    public Date getDataAtendimento()
    {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento)
    {
        this.dataAtendimento = dataAtendimento;
    }



    public DocumentoOcorrencia getDocumento()
    {
        return documento;
    }

    public void setDocumento(DocumentoOcorrencia documento)
    {
        this.documento = documento;
    }

    public String getNumIncidencia()
    {
        return numIncidencia;
    }

    public void setNumIncidencia(String numIncidencia)
    {
        this.numIncidencia = numIncidencia;
    }

    public String getViatura()
    {
        return Viatura;
    }

    public void setViatura(String viatura)
    {
        Viatura = viatura;
    }

    public String getComandante()
    {
        return Comandante;
    }

    public void setComandante(String comandante)
    {
        Comandante = comandante;
    }

    public AreaIntegradaSeguranca getAis()
    {
        return ais;
    }

    public void setAis(AreaIntegradaSeguranca ais)
    {
        this.ais = ais;
    }

    public PreservacaoLocal getPreservacaoLocal()
    {
        return preservacaoLocal;
    }

    public void setPreservacaoLocal(PreservacaoLocal preservacaoLocal)
    {
        this.preservacaoLocal = preservacaoLocal;
    }

    public String getHoraChamadoString()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(this.dataChamado);
    }
    public String getHoraAtendimentoString()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(this.dataAtendimento);
    }

    public String getDataAtendimentoString()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");
        return timeFormat.format(this.dataAtendimento);
    }
    public String getDataChamadoString()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");
        return timeFormat.format(this.dataChamado);
    }

    public String getDataHoraFormatadaChamado()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return timeFormat.format(this.dataChamado);
    }
    public String getDataHoraFormatadaAtendimento()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return timeFormat.format(this.dataAtendimento);
    }

    public void setHoraChamado(String s)
    {
        final Calendar c  = Calendar.getInstance();
        c.setTime(TempoUtil.stringToTime(s));

        int hr = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        final Calendar cSave = Calendar.getInstance();
        cSave.setTime(this.getDataChamado());
        cSave.set(Calendar.HOUR_OF_DAY,hr);
        cSave.set(Calendar.MINUTE,min);

        this.dataChamado = cSave.getTime();
    }

    public void setHoraAtendimento(String s)
    {
        final Calendar c  = Calendar.getInstance();
        c.setTime(TempoUtil.stringToTime(s));

        int hr = c.get(Calendar.HOUR_OF_DAY);
        int min = c.get(Calendar.MINUTE);

        final Calendar cSave = Calendar.getInstance();
        cSave.setTime(this.getDataAtendimento());
        cSave.set(Calendar.HOUR_OF_DAY,hr);
        cSave.set(Calendar.MINUTE,min);

        this.dataAtendimento = cSave.getTime();
    }


    public void setDataAtendimento(String s)
    {
        final Calendar c  = Calendar.getInstance();
        c.setTime(TempoUtil.stringToDate(s));

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        final Calendar cSave = Calendar.getInstance();
        cSave.setTime(this.getDataAtendimento());
        cSave.set(Calendar.YEAR,year);
        cSave.set(Calendar.MONTH,month);
        cSave.set(Calendar.DAY_OF_MONTH,day);

        this.dataAtendimento = cSave.getTime();
    }
    public void setDataChamadoString(String s)
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

        this.dataChamado = cSave.getTime();
    }

    public String getOrgaoOrigem()
    {
        return orgaoOrigem;
    }

    public void setOrgaoOrigem(String orgaoOrigem)
    {
        this.orgaoOrigem = orgaoOrigem;
    }

    public String getOrgaoDestino()
    {
        return orgaoDestino;
    }

    public void setOrgaoDestino(String orgaoDestino)
    {
        this.orgaoDestino = orgaoDestino;
    }

    public String getDelegado()
    {
        return delegado;
    }

    public void setDelegado(String delegado)
    {
        this.delegado = delegado;
    }

    public Orgao getAutoridadePresente()
    {
        return autoridadePresente;
    }

    public void setAutoridadePresente(Orgao autoridadePresente)
    {
        this.autoridadePresente = autoridadePresente;
    }

    public String getDataPath()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy/MM/dd");
        return timeFormat.format(this.dataAtendimento);
    }

}
