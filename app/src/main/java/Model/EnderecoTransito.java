package Model;

import com.orm.SugarRecord;

import Enums.CondicaoPista;
import Enums.Iluminacao;
import Enums.Logradouro;
import Enums.OrientacaoGeografica;
import Enums.Pavimentacao;
import Enums.Semaforo;
import Enums.SinalizacaoPare;
import Enums.TipoVia;
import Enums.Topografia;

/**
 * Created by Pefoce on 05/06/2017.
 */

public class EnderecoTransito extends SugarRecord<EnderecoTransito> {

    private Endereco endereco;
    private TipoVia tipoVia;
    private Topografia topografia;
    private Logradouro logradouro;
    private Pavimentacao pavimentacao;
    private CondicaoPista condicao;
    private Iluminacao iluminacao;
    private SinalizacaoPare sinalizacaoPare;
    private Semaforo semaforo;
    private boolean isPreferencial;
    private boolean isCurva;
    private boolean isMolhada;

    private boolean isComposta;
    int numFaixas;




    public EnderecoTransito(Endereco endereco) {
        this.endereco = endereco;
    }

    public EnderecoTransito() {
        this.endereco = new Endereco(); }

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

    public Iluminacao getIluminacao() {
        return iluminacao;
    }

    public void setIluminacao(Iluminacao iluminacao) {
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


}
