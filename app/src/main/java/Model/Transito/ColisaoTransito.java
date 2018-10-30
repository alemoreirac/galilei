package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import java.util.Calendar;
import java.util.Date;

import Enums.Transito.AtoresColisao;
import Enums.Transito.ConclusaoTransito;
import Enums.Transito.LocalObjeto;
import Enums.Transito.LocalPedestre;
import Enums.OrientacaoGeograficaComposta;
import Enums.TipoInteracao;
import Enums.Transito.TipoJustificativa_Inconclusao;
import Model.Gravacao;

/**
 * Created by Pefoce on 29/08/2017.
 */

public class ColisaoTransito extends SugarRecord
{
    @Expose
    Date dataInclusao;
    @Expose
    Veiculo veiculo1;
    @Expose
    Veiculo veiculo2;
    @Expose
    TipoInteracao tipoInteracao;
    @Expose
    boolean isVeiculo1Causador;
    @Expose
    boolean isVeiculo2Causador;
    @Expose
    ConclusaoTransito conclusaoVeiculo1;
    @Expose
    ConclusaoTransito conclusaoVeiculo2;
    @Expose
    boolean isVeiculo1ContraMao;
    @Expose
    boolean isVeiculo2ContraMao;
    @Expose
    int ordemAcontecimento;
    @Expose
    int veiculo1_Faixa;
    @Expose
    int veiculo2_Faixa;
    @Expose
    EnderecoTransito endereco_veiculo1;
    @Expose
    EnderecoTransito endereco_veiculo2;
    @Expose
    OrientacaoGeograficaComposta sentido_veiculo1;
    @Expose
    OrientacaoGeograficaComposta sentido_veiculo2;
    @Expose
    String objetoDescricao;
    @Expose
    String objetoObservacao;
    @Expose
    LocalObjeto objetoPosicao;
//    @Expose
//    EnvolvidoTransito pedestre;
    @Expose
    LocalPedestre posicaoPedestre;
    @Expose
    boolean culpaPedestre;
    @Expose
    int distancia;
    @Expose
    String observacoesColisao;
    @Expose
    Gravacao gravacaoObservacoes;
    @Expose
    AtoresColisao atoresColisao;
    @Expose
    String animalDescricao;
    @Expose
    String animalObservacao;
    @Expose
    LocalObjeto animalPosicao;
    @Expose
    boolean Inconclusivo;
    @Expose
    Veiculo veiculoEvadido;
    @Expose
    EnvolvidoTransito envolvidoEvadido;
    @Expose
    TipoJustificativa_Inconclusao justificativaInconclusao;

    public TipoJustificativa_Inconclusao getJustificativaInconclusao()
    {
        return justificativaInconclusao;
    }

    public void setJustificativaInconclusao(TipoJustificativa_Inconclusao justificativaInconclusao)
    {
        this.justificativaInconclusao = justificativaInconclusao;
    }


    public Veiculo getVeiculoEvadido()
    {
        return veiculoEvadido;
    }

    public void setVeiculoEvadido(Veiculo veiculoEvadido)
    {
        this.veiculoEvadido = veiculoEvadido;
    }

    public EnvolvidoTransito getEnvolvidoEvadido()
    {
        return envolvidoEvadido;
    }

    public void setEnvolvidoEvadido(EnvolvidoTransito envolvidoEvadido)
    {
        this.envolvidoEvadido = envolvidoEvadido;
    }


    public boolean getInconclusivo()
    {
        return Inconclusivo;
    }

    public void setInconclusivo(boolean inconclusivo)
    {
        Inconclusivo = inconclusivo;
    }


    public void AnularCampos()
    {
        //this.atoresColisao = null;
        this.veiculo1 = null;
        this.sentido_veiculo1 = null;
        this.isVeiculo1ContraMao = false;
        this.isVeiculo1Causador = false;
        this.veiculo1_Faixa = 0;
        this.endereco_veiculo1 = null;
        this.conclusaoVeiculo1 = null;

        this.veiculo2 = null;
        this.sentido_veiculo2 = null;
        this.isVeiculo2ContraMao = false;
        this.isVeiculo2Causador = false;
        this.veiculo1_Faixa = 0;
        this.endereco_veiculo2 = null;
        this.conclusaoVeiculo2 = null;

//        this.pedestre = null;
        this.posicaoPedestre = null;
        this.distancia = 0;

        this.objetoDescricao = "";
        this.objetoObservacao = "";
        this.objetoPosicao = null;

        this.animalDescricao = "";
        this.animalObservacao = "";
        this.animalPosicao = null;
        this.Inconclusivo = false;

        this.ordemAcontecimento = 0;

    }

    public ColisaoTransito()
    {
        dataInclusao = Calendar.getInstance().getTime();

        setTipoInteracao(TipoInteracao.ADERNAMENTO);
    }

    public int getOrdemAcontecimento()
    {
        return ordemAcontecimento;
    }

    public void setOrdemAcontecimento(int ordemAcontecimento)
    {
        this.ordemAcontecimento = ordemAcontecimento;
    }

    public Veiculo getVeiculo1()
    {
        return veiculo1;
    }

    public void setVeiculo1(Veiculo veiculo1)
    {
        this.veiculo1 = veiculo1;
    }

    public Veiculo getVeiculo2()
    {
        return veiculo2;
    }

    public void setVeiculo2(Veiculo veiculo2)
    {
        this.veiculo2 = veiculo2;
    }

    public TipoInteracao getTipoInteracao()
    {
        return tipoInteracao;
    }

    public void setTipoInteracao(TipoInteracao tipoInteracao)
    {
        this.tipoInteracao = tipoInteracao;
    }

    public boolean isVeiculo1Causador()
    {
        return isVeiculo1Causador;
    }

    public void setVeiculo1Causador(boolean veiculo1Causador)
    {
        isVeiculo1Causador = veiculo1Causador;
    }

    public boolean isVeiculo2Causador()
    {
        return isVeiculo2Causador;
    }

    public void setVeiculo2Causador(boolean veiculo2Causador)
    {
        isVeiculo2Causador = veiculo2Causador;
    }

    public boolean isVeiculo1ContraMao()
    {
        return isVeiculo1ContraMao;
    }

    public void setVeiculo1ContraMao(boolean veiculo1ContraMao)
    {
        isVeiculo1ContraMao = veiculo1ContraMao;
    }

    public boolean isVeiculo2ContraMao()
    {
        return isVeiculo2ContraMao;
    }

    public void setVeiculo2ContraMao(boolean veiculo2ContraMao)
    {
        isVeiculo2ContraMao = veiculo2ContraMao;
    }

    public int getVeiculo1_Faixa()
    {
        return veiculo1_Faixa;
    }

    public void setVeiculo1_Faixa(int veiculo1_Faixa)
    {
        this.veiculo1_Faixa = veiculo1_Faixa;
    }

    public int getVeiculo2_Faixa()
    {
        return veiculo2_Faixa;
    }

    public void setVeiculo2_Faixa(int veiculo2_Faixa)
    {
        this.veiculo2_Faixa = veiculo2_Faixa;
    }

    public EnderecoTransito getEndereco_veiculo1()
    {
        return endereco_veiculo1;
    }

    public void setEndereco_veiculo1(EnderecoTransito endereco_veiculo1)
    {
        this.endereco_veiculo1 = endereco_veiculo1;
    }

    public EnderecoTransito getEndereco_veiculo2()
    {
        return endereco_veiculo2;
    }

    public void setEndereco_veiculo2(EnderecoTransito endereco_veiculo2)
    {
        this.endereco_veiculo2 = endereco_veiculo2;
    }

    public ConclusaoTransito getConclusaoVeiculo1()
    {
        return conclusaoVeiculo1;
    }

    public void setConclusaoVeiculo1(ConclusaoTransito conclusaoVeiculo1)
    {
        this.conclusaoVeiculo1 = conclusaoVeiculo1;
    }

    public OrientacaoGeograficaComposta getSentido_veiculo1()
    {
        return sentido_veiculo1;
    }

    public void setSentido_veiculo1(OrientacaoGeograficaComposta sentido_veiculo1)
    {
        this.sentido_veiculo1 = sentido_veiculo1;
    }

    public OrientacaoGeograficaComposta getSentido_veiculo2()
    {
        return sentido_veiculo2;
    }

    public void setSentido_veiculo2(OrientacaoGeograficaComposta sentido_veiculo2)
    {
        this.sentido_veiculo2 = sentido_veiculo2;
    }

    public String getObjetoDescricao()
    {
        return objetoDescricao;
    }

    public void setObjetoDescricao(String objetoDescricao)
    {
        this.objetoDescricao = objetoDescricao;
    }

    public String getObjetoObservacao()
    {
        return objetoObservacao;
    }

    public void setObjetoObservacao(String objetoObservacao)
    {
        this.objetoObservacao = objetoObservacao;
    }

    public LocalObjeto getObjetoPosicao()
    {
        return objetoPosicao;
    }

    public void setObjetoPosicao(LocalObjeto objetoPosicao)
    {
        this.objetoPosicao = objetoPosicao;
    }

//    public EnvolvidoTransito getPedestre()
//    {
//        return pedestre;
//    }
//
//    public void setPedestre(EnvolvidoTransito pedestre)
//    {
//        this.pedestre = pedestre;
//    }

    public LocalPedestre getPosicaoPedestre()
    {
        return posicaoPedestre;
    }

    public void setPosicaoPedestre(LocalPedestre posicaoPedestre)
    {
        this.posicaoPedestre = posicaoPedestre;
    }

    public boolean getCulpaPedestre()
    {
        return culpaPedestre;
    }

    public void setCulpaPedestre(boolean culpaPedestre)
    {
        this.culpaPedestre = culpaPedestre;
    }

    public int getDistancia()
    {
        return distancia;
    }

    public void setDistancia(int distancia)
    {
        this.distancia = distancia;
    }

    public String getObservacoesColisao()
    {
        return observacoesColisao;
    }

    public void setObservacoesColisao(String observacoesColisao)
    {
        this.observacoesColisao = observacoesColisao;
    }

    public String getAnimalDescricao()
    {
        return animalDescricao;
    }

    public void setAnimalDescricao(String animalDescricao)
    {
        this.animalDescricao = animalDescricao;
    }

    public Gravacao getGravacaoObservacoes()
    {
        return gravacaoObservacoes;
    }

    public void setGravacaoObservacoes(Gravacao gravacaoObservacoes)
    {
        this.gravacaoObservacoes = gravacaoObservacoes;
    }

    public String getAnimalObservacao()
    {
        return animalObservacao;
    }

    public void setAnimalObservacao(String animalObservacao)
    {
        this.animalObservacao = animalObservacao;
    }

    public ConclusaoTransito getConclusaoVeiculo2()
    {
        return conclusaoVeiculo2;
    }

    public void setConclusaoVeiculo2(ConclusaoTransito conclusaoVeiculo2)
    {
        this.conclusaoVeiculo2 = conclusaoVeiculo2;
    }

    public LocalObjeto getAnimalPosicao()
    {
        return animalPosicao;
    }

    public void setAnimalPosicao(LocalObjeto animalPosicao)
    {
        this.animalPosicao = animalPosicao;
    }

    public AtoresColisao getAtoresColisao()
    {
        return atoresColisao;
    }

    public void setAtoresColisao(AtoresColisao atoresColisao)
    {
        this.atoresColisao = atoresColisao;
    }

}
