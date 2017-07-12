package Model;

import com.orm.SugarRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Enums.DocumentoSolicitacao;
import Enums.EstadoSitioColisao;
import Enums.Orgao;
import Enums.PreservacaoLocal;
import Util.TempoUtil;


/**
 * Created by Pefoce on 29/05/2017.
 */

public class OcorrenciaTransito extends SugarRecord<OcorrenciaTransito> {

    private List<Veiculo> veiculos;

    private List<EnderecoTransito> enderecos;
    private List<EnvolvidoTransito> vitimas;

    private Date dataOcorrencia;
    private Date dataChamado;
    private Date dataAtendimento;

    private CarroVitima carroVitima;
    private CarroCarro carroCarro;

    private List<Foto> fotos;

    private String Observacoes;

    private DocumentoOcorrencia documentoOcorrencia;

    private EstadoSitioColisao estadoSitioColisao;
    
    private PreservacaoLocal preservacaoLocal;

    private Pessoa perito;

    private String orgaoOrigem;

    private String orgaoDestino;


    private Orgao orgaoPresente;
    private String Viatura;
    private String Comandante;

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



    public OcorrenciaTransito()
    {
            this.dataAtendimento = TempoUtil.stringToDate("01/01/2000");
            this.dataChamado = TempoUtil.stringToDate("01/01/2000");
            this.dataOcorrencia = TempoUtil.stringToDate("01/01/2000");
    }

    public String getOrgaoDestino() {
        return orgaoDestino;
    }

    public void setOrgaoDestino(String orgaoDestino) {
        this.orgaoDestino = orgaoDestino;
    }


    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public List<EnderecoTransito> getEnderecos() {
        return enderecos;
    }

    public List<EnvolvidoTransito> getVitimas() {
        return vitimas;
    }

    public void setVitimas(List<EnvolvidoTransito> vitimas) {
        this.vitimas = vitimas;
    }

    public Date getDataChamado() {

        return dataChamado;
    }

    public String getHoraChamadoString()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(this.dataChamado);
    }

    public void setDataChamado(Date dataChamado) {
        this.dataChamado = dataChamado;
    }


    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public String getDataAtendimentoString(){

        // Get Current Date

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar c = Calendar.getInstance();
        c.setTime(this.getDataAtendimento());


        String data = Calendar.DAY_OF_MONTH + "/" + Calendar.MONTH + "/" + Calendar.YEAR;

        data = format.format(c.getTime());

        return data;
    }

    public String getHoraAtendimentoString()
    {

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(this.getDataAtendimento());

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


    public void setDataAtendimento(int ano,int mes, int dia)
    {

        final Calendar c = Calendar.getInstance();
        c.setTime(this.getDataAtendimento());

        c.set(ano,mes,dia);

        this.setDataAtendimento(c.getTime());

    }


    public void setEnderecos(List<EnderecoTransito> enderecos) {
        this.enderecos = enderecos;
    }

    public Date getDataOcorrencia() {
        return dataOcorrencia;
    }

    public String getHoraOcorrenciaString()
    {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        return timeFormat.format(this.dataOcorrencia);
    }
    public void setDataOcorrencia(Date dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }


    public CarroVitima getCarroVitima() {
        return carroVitima;
    }

    public void setCarroVitima(CarroVitima carroVitima) {
        this.carroVitima = carroVitima;
    }

    public CarroCarro getCarroCarro() {
        return carroCarro;
    }

    public void setCarroCarro(CarroCarro carroCarro) {
        this.carroCarro = carroCarro;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }

    public String getObservacoes() {
        return Observacoes;
    }

    public void setObservacoes(String observacoes) {
        Observacoes = observacoes;
    }

    public Pessoa getPerito() {
        return perito;
    }

    public void setPerito(Pessoa perito) {
        this.perito = perito;
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
}
