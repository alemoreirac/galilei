package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import Enums.Transito.CondicaoPista;
import Enums.IluminacaoVia;
import Enums.Transito.Logradouro;
import Enums.OrientacaoGeograficaComposta;
import Enums.Transito.Pavimentacao;
import Enums.Transito.Semaforo;
import Enums.Transito.SinalizacaoPare;
import Enums.Transito.TipoVia;
import Enums.Transito.Topografia;
import Model.Endereco;

/**
 * Created by Pefoce on 05/06/2017.
 */

public class EnderecoTransito extends SugarRecord<EnderecoTransito> {

    @Expose
    private Endereco endereco;
    @Expose
    private TipoVia tipoVia;
    @Expose
    private Topografia topografia;
    @Expose
    private Logradouro logradouro;
    @Expose
    private Pavimentacao pavimentacao;
    @Expose
    private CondicaoPista condicao;
    @Expose
    private IluminacaoVia iluminacao;
    @Expose
    private SinalizacaoPare sinalizacaoPare;
    @Expose
    private Semaforo semaforo;
    @Expose
    private OrientacaoGeograficaComposta sentidoVia;
    @Expose
    private int Angulo;
    @Expose
    private boolean isMaoDupla;
    @Expose
    private boolean isPreferencial;
    @Expose
    private boolean isCurva;
    @Expose
    private boolean isMolhada;
    @Expose
    private boolean isComposta;
    @Expose
    private int numFaixas;
    @Expose
    private int numPistas;
    @Expose
    String latitude;
    @Expose
    String longitude;

    @Expose
    Long largura;


    public EnderecoTransito(Endereco endereco) {
        this.endereco = endereco;
    }

    public EnderecoTransito() {
        this.endereco = new Endereco();

    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoVia getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(TipoVia tipoVia) {
        this.tipoVia = tipoVia;
    }

    public Topografia getTopografia() {
        return topografia;
    }

    public void setTopografia(Topografia topografia) {
        this.topografia = topografia;
    }

    public Logradouro getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(Logradouro logradouro) {
        this.logradouro = logradouro;
    }

    public Pavimentacao getPavimentacao() {
        return pavimentacao;
    }

    public void setPavimentacao(Pavimentacao pavimentacao) {
        this.pavimentacao = pavimentacao;
    }

    public CondicaoPista getCondicao() {
        return condicao;
    }

    public void setCondicao(CondicaoPista condicao) {
        this.condicao = condicao;
    }

    public IluminacaoVia getIluminacao() {
        return iluminacao;
    }

    public void setIluminacao(IluminacaoVia iluminacao) {
        this.iluminacao = iluminacao;
    }

    public SinalizacaoPare getSinalizacaoPare() {
        return sinalizacaoPare;
    }

    public void setSinalizacaoPare(SinalizacaoPare sinalizacaoPare) {
        this.sinalizacaoPare = sinalizacaoPare;
    }

    public Semaforo getSemaforo() {
        return semaforo;
    }

    public void setSemaforo(Semaforo semaforo) {
        this.semaforo = semaforo;
    }

    public boolean isPreferencial() {
        return isPreferencial;
    }

    public void setPreferencial(boolean preferencial) {
        isPreferencial = preferencial;
    }

    public boolean isCurva() {
        return isCurva;
    }

    public void setCurva(boolean curva) {
        isCurva = curva;
    }

    public boolean isMolhada() {
        return isMolhada;
    }

    public void setMolhada(boolean molhada) {
        isMolhada = molhada;
    }

    public boolean isComposta() {
        return isComposta;
    }

    public void setComposta(boolean composta) {
        isComposta = composta;
    }

    public int getNumFaixas() {
        return numFaixas;
    }

    public void setNumFaixas(int numFaixas) {
        this.numFaixas = numFaixas;
    }

    @Override
    public String toString() {
        if(endereco!= null)
        return this.endereco.getDescricao();
        else
            return "(Sem Endereço)";
    }

    public int getAngulo() {
        return Angulo;
    }

    public void setAngulo(int angulo) {
        Angulo = angulo;
    }

    public boolean isMaoDupla() {
        return isMaoDupla;
    }

    public void setMaoDupla(boolean maoDupla) {
        isMaoDupla = maoDupla;
    }

    public int getNumPistas() {
        return numPistas;
    }

    public void setNumPistas(int numPistas) {
        this.numPistas = numPistas;
    }

    public OrientacaoGeograficaComposta getSentidoVia() {
        return sentidoVia;
    }

    public void setSentidoVia(OrientacaoGeograficaComposta sentidoVia) {
        this.sentidoVia = sentidoVia;
    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String toStringDoc()
    {
        return this.tipoVia.getValor() + " " + endereco.getDescricao();
    }

    public Long getLargura() {
        return largura;
    }

    public void setLargura(Long largura) {
        this.largura = largura;
    }

}
