package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Enums.AreaIntegradaSeguranca;
import Enums.DocumentoSolicitacao;
import Enums.Transito.EstadoSitioColisao;
import Enums.Orgao;
import Enums.PreservacaoLocal;
import Model.DocumentoOcorrencia;
import Model.Gravacao;
import Util.TempoUtil;


/**
 * Created by Pefoce on 29/05/2017.
 */

public class OcorrenciaTransito extends SugarRecord
{

    @Expose
    Long ocorrenciaID;
    @Expose
    private Date dataChamado;
    @Expose
    private Date dataAtendimento;
    @Expose
    private String Observacoes;
    @Expose
    private DocumentoOcorrencia documentoOcorrencia;
    @Expose
    private String numIncidencia;
    @Expose
    private AreaIntegradaSeguranca ais;
    @Expose
    private EstadoSitioColisao estadoSitioColisao;
    @Expose
    private PreservacaoLocal preservacaoLocal;
    @Expose
    private String orgaoOrigem;
    @Expose
    private String orgaoDestino;
    @Expose
    private Orgao orgaoPresente;
    @Expose
    private String Viatura;
    @Expose
    private String Comandante;
    @Expose
    public Gravacao gravacaoConclusao;
    @Expose
    private boolean ultimaForma;


    public boolean getUltimaForma()
    {
        return ultimaForma;
    }

    public void setUltimaForma(boolean ultimaForma)
    {
        this.ultimaForma = ultimaForma;
    }


    public Orgao getOrgaoPresente() {
        return orgaoPresente;
    }

    public void setOrgaoPresente(Orgao orgaoPresente) {
        this.orgaoPresente = orgaoPresente;
    }

    public String getViatura() {
        return Viatura;
    }

    public void setViatura(String viatura) {
        Viatura = viatura;
    }

    public String getComandante() {
        return Comandante;
    }

    public void setComandante(String comandante) {
        Comandante = comandante;
    }

    public Gravacao getGravacaoConclusao() {
        return gravacaoConclusao;
    }

    public void setGravacaoConclusao(Gravacao gravacaoConclusao) {
        this.gravacaoConclusao = gravacaoConclusao;
    }

    public OcorrenciaTransito()
    {
            this.dataAtendimento =  Calendar.getInstance().getTime();
            this.dataChamado =  Calendar.getInstance().getTime();
            this.setViatura("");

    }

    public String getOrgaoDestino() {
        return orgaoDestino;
    }

    public void setOrgaoDestino(String orgaoDestino) {
        this.orgaoDestino = orgaoDestino;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getDataAtendimentoString()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");

        if(dataAtendimento!=null)
            return timeFormat.format(this.dataAtendimento);
        else
            return "--/--/----";
    }

    public String getDataChamadoString()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("dd/MM/yyyy");

        if(dataChamado!=null)
            return timeFormat.format(this.dataChamado);
        else
            return "--/--/----";
    }

    public String getDataPath()
    {
            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy/MM/dd");
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

        this.dataChamado= cSave.getTime();
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

    public Date getDataChamado() {
        return dataChamado;
    }

    public String getHoraAtendimentoString()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        if(dataAtendimento!=null)
            return timeFormat.format(this.dataAtendimento);
        else
            return "--:--";
    }

    public String getHoraChamadoString()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        if(dataChamado!=null)
            return timeFormat.format(this.dataChamado);
        else
            return "--:--";
    }


    public void setDataChamado(Date dataChamado) {
        this.dataChamado = dataChamado;
    }


    public String getObservacoes() {
        return Observacoes;
    }

    public void setObservacoes(String observacoes) {
        Observacoes = observacoes;
    }



    public DocumentoOcorrencia getDocumentoOcorrencia() {
        return documentoOcorrencia;
    }

    public void setDocumentoOcorrencia(DocumentoOcorrencia documentoOcorrencia) {
        this.documentoOcorrencia = documentoOcorrencia;
    }


    public EstadoSitioColisao getEstadoSitioColisao() {
        return estadoSitioColisao;
    }

    public void setEstadoSitioColisao(EstadoSitioColisao estadoSitioColisao) {
        this.estadoSitioColisao = estadoSitioColisao;
    }

    public PreservacaoLocal getPreservacaoLocal() {
        return preservacaoLocal;
    }

    public void setPreservacaoLocal(PreservacaoLocal preservacaoLocal) {
        this.preservacaoLocal = preservacaoLocal;}
    
    public String getOrgaoOrigem() {
        return orgaoOrigem;
    }

    public void setOrgaoOrigem(String orgaoOrigem) {
        this.orgaoOrigem = orgaoOrigem;
    }

    public void setEstadoSitioColisao(DocumentoSolicitacao documentoSolicitacao) {
    }

    public String getNumIncidencia() {
        return numIncidencia;
    }

    public void setNumIncidencia(String numIncidencia) {
        this.numIncidencia = numIncidencia;
    }

    public AreaIntegradaSeguranca getAis() {
        return ais;
    }

    public void setAis(AreaIntegradaSeguranca ais) {
        this.ais = ais;
    }

    public Long getOcorrenciaID() {
        return ocorrenciaID;
    }

    public void setOcorrenciaID(Long ocorrenciaID) {
        this.ocorrenciaID = ocorrenciaID;
    }

}
