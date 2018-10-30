package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;

import Enums.Transito.CondicaoPista;
import Enums.IluminacaoVia;
import Enums.OrientacaoGeograficaComposta;
import Enums.Transito.Pavimentacao;
import Enums.Transito.Semaforo;
import Enums.Transito.SinalizacaoPare;
import Enums.Transito.TipoVia;
import Enums.Transito.Topografia;
import Model.Bairro;
import Model.Municipio;
import Util.StringUtil;

/**
 * Created by Pefoce on 05/06/2017.
 */

public class EnderecoTransito extends SugarRecord
{
    @Expose
    Date dataInclusao;
    @Expose
    private Municipio municipio;
    @Expose
    private Bairro bairro;
    @Expose
    private String complemento;
    @Expose
    private String descricaoEndereco;
    @Expose
    private TipoVia tipoVia;
    @Expose
    private Topografia topografia;
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
    private int anguloInclinacao;
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
    private boolean meioFio;
    @Expose
    Float largura;


    public boolean getMeioFio()
    {
        return meioFio;
    }

    public void setMeioFio(boolean meioFio)
    {
        this.meioFio = meioFio;
    }

    public TipoVia getTipoVia()
    {
        return tipoVia;
    }

    public void setTipoVia(TipoVia tipoVia)
    {
        this.tipoVia = tipoVia;
    }

    public Topografia getTopografia()
    {
        return topografia;
    }

    public void setTopografia(Topografia topografia)
    {
        this.topografia = topografia;
    }


    public Pavimentacao getPavimentacao()
    {
        return pavimentacao;
    }

    public void setPavimentacao(Pavimentacao pavimentacao)
    {
        this.pavimentacao = pavimentacao;
    }

    public CondicaoPista getCondicao()
    {
        return condicao;
    }

    public void setCondicao(CondicaoPista condicao)
    {
        this.condicao = condicao;
    }

    public IluminacaoVia getIluminacao()
    {
        return iluminacao;
    }

    public void setIluminacao(IluminacaoVia iluminacao)
    {
        this.iluminacao = iluminacao;
    }

    public SinalizacaoPare getSinalizacaoPare()
    {
        return sinalizacaoPare;
    }

    public void setSinalizacaoPare(SinalizacaoPare sinalizacaoPare)
    {
        this.sinalizacaoPare = sinalizacaoPare;
    }

    public Semaforo getSemaforo()
    {
        return semaforo;
    }

    public void setSemaforo(Semaforo semaforo)
    {
        this.semaforo = semaforo;
    }

    public boolean isPreferencial()
    {
        return isPreferencial;
    }

    public Municipio getMunicipio()
    {
        return municipio;
    }

    public void setMunicipio(Municipio municipio)
    {
        this.municipio = municipio;
    }

    public Bairro getBairro()
    {
        return bairro;
    }

    public void setBairro(Bairro bairro)
    {
        this.bairro = bairro;
    }

    public String getDescricaoEndereco()
    {
        return descricaoEndereco;
    }

    public void setDescricaoEndereco(String descricaoEndereco)
    {
        this.descricaoEndereco = descricaoEndereco;
    }

    public void setPreferencial(boolean preferencial)
    {
        isPreferencial = preferencial;
    }

    public boolean isCurva()
    {
        return isCurva;
    }

    public void setCurva(boolean curva)
    {
        isCurva = curva;
    }

    public boolean isMolhada()
    {
        return isMolhada;
    }

    public void setMolhada(boolean molhada)
    {
        isMolhada = molhada;
    }

    public boolean isComposta()
    {
        return isComposta;
    }

    public void setComposta(boolean composta)
    {
        isComposta = composta;
    }

    public int getNumFaixas()
    {
        return numFaixas;
    }

    public void setNumFaixas(int numFaixas)
    {
        this.numFaixas = numFaixas;
    }

    @Override
    public String toString()
    {
        return StringUtil.checkValue(descricaoEndereco, 20, "(Sem Endereço)");
    }

    public int getAnguloInclinacao()
    {
        return anguloInclinacao;
    }

    public void setAnguloInclinacao(int anguloInclinacao)
    {
        this.anguloInclinacao = anguloInclinacao;
    }

    public boolean isMaoDupla()
    {
        return isMaoDupla;
    }

    public void setMaoDupla(boolean maoDupla)
    {
        isMaoDupla = maoDupla;
    }

    public int getNumPistas()
    {
        return numPistas;
    }

    public void setNumPistas(int numPistas)
    {
        this.numPistas = numPistas;
    }

    public OrientacaoGeograficaComposta getSentidoVia()
    {
        return sentidoVia;
    }

    public void setSentidoVia(OrientacaoGeograficaComposta sentidoVia)
    {
        this.sentidoVia = sentidoVia;
    }

    public String getComplemento()
    {
        return complemento;
    }

    public void setComplemento(String complemento)
    {
        this.complemento = complemento;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String toStringDoc()
    {
        String endereco = "";

        if(tipoVia.getValor()!=null)
            endereco += tipoVia.getValor();

        if (descricaoEndereco != null)
            endereco += " " + descricaoEndereco;

        if(endereco.isEmpty())
            return "(sem endereço)";

        return endereco;
    }

    public Float getLargura()
    {
        return largura;
    }

    public void setLargura(Float largura)
    {
        this.largura = largura;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EnderecoTransito that = (EnderecoTransito) o;

        if (anguloInclinacao != that.anguloInclinacao) return false;
        if (isMaoDupla != that.isMaoDupla) return false;
        if (isPreferencial != that.isPreferencial) return false;
        if (isCurva != that.isCurva) return false;
        if (isMolhada != that.isMolhada) return false;
        if (isComposta != that.isComposta) return false;
        if (numFaixas != that.numFaixas) return false;
        if (numPistas != that.numPistas) return false;
        if (municipio != null ? !municipio.equals(that.municipio) : that.municipio != null)
            return false;
        if (bairro != null ? !bairro.equals(that.bairro) : that.bairro != null) return false;
        if (descricaoEndereco != null ? !descricaoEndereco.equals(that.descricaoEndereco) : that.descricaoEndereco != null)
            return false;
        if (tipoVia != that.tipoVia) return false;
        if (topografia != that.topografia) return false;
        if (pavimentacao != that.pavimentacao) return false;
        if (condicao != that.condicao) return false;
        if (iluminacao != that.iluminacao) return false;
        if (sinalizacaoPare != that.sinalizacaoPare) return false;
        if (semaforo != that.semaforo) return false;
        if (sentidoVia != that.sentidoVia) return false;
        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null)
            return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null)
            return false;
        return largura != null ? largura.equals(that.largura) : that.largura == null;
    }

    @Override
    public int hashCode()
    {
        int result = municipio != null ? municipio.hashCode() : 0;
        result = 31 * result + (bairro != null ? bairro.hashCode() : 0);
        result = 31 * result + (descricaoEndereco != null ? descricaoEndereco.hashCode() : 0);
        result = 31 * result + (tipoVia != null ? tipoVia.hashCode() : 0);
        result = 31 * result + (topografia != null ? topografia.hashCode() : 0);
        result = 31 * result + (pavimentacao != null ? pavimentacao.hashCode() : 0);
        result = 31 * result + (condicao != null ? condicao.hashCode() : 0);
        result = 31 * result + (iluminacao != null ? iluminacao.hashCode() : 0);
        result = 31 * result + (sinalizacaoPare != null ? sinalizacaoPare.hashCode() : 0);
        result = 31 * result + (semaforo != null ? semaforo.hashCode() : 0);
        result = 31 * result + (sentidoVia != null ? sentidoVia.hashCode() : 0);
        result = 31 * result + anguloInclinacao;
        result = 31 * result + (isMaoDupla ? 1 : 0);
        result = 31 * result + (isPreferencial ? 1 : 0);
        result = 31 * result + (isCurva ? 1 : 0);
        result = 31 * result + (isMolhada ? 1 : 0);
        result = 31 * result + (isComposta ? 1 : 0);
        result = 31 * result + numFaixas;
        result = 31 * result + numPistas;
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (largura != null ? largura.hashCode() : 0);
        return result;
    }

    public EnderecoTransito()
    {
        dataInclusao = Calendar.getInstance().getTime();
    }
}
