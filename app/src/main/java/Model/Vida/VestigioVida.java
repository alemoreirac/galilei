package Model.Vida;

import com.orm.SugarRecord;

import Enums.Calibre;
import Enums.DocumentoPessoa;
import Enums.TipoArma;
import Enums.TipoRecolhimentoAmostra_Papiloscopia;
import Enums.TiposMunicao;
import Enums.TiposVestigioBiologico;
import Enums.Vida.TipoRecolhimentoAmostra_Biologica;
import Enums.Vida.TipoVestigioVida;

/**
 * Created by Pefoce on 02/12/2017.
 */

public class VestigioVida extends SugarRecord<VestigioVida>
{
    TipoVestigioVida tipoVestigio;
    String observacao;

    //Características de Documento
    String numDocumento;
    DocumentoPessoa tipoDocumento;

    //Características de Impressões Papiloscópicas
    TipoRecolhimentoAmostra_Papiloscopia tipoRecolhimentoAmostraPapiloscopia;
    String objetoRecolhidoPapiloscopia;

    //Características de Biológicos
    TiposVestigioBiologico tiposVestigioBiologico;
    TipoRecolhimentoAmostra_Biologica tipoRecolhimentoAmostraBiologica;


    //Características armas de Fogo
    TipoArma tipoArma;
    String numeracaoArma;
    Calibre calibreArma;

    //Características Munição
    int quantidadeMunicao;
    Calibre calibreMunicao;
    boolean condicaoMunicao;
    TiposMunicao tiposMunicao;


    //Características MISCELANIA
    String objetoRecolhido;

    public VestigioVida()
    {
    }

    public TipoRecolhimentoAmostra_Papiloscopia getTipoRecolhimentoAmostraPapiloscopia()
    {
        return tipoRecolhimentoAmostraPapiloscopia;
    }

    public void setTipoRecolhimentoAmostraPapiloscopia(TipoRecolhimentoAmostra_Papiloscopia tipoRecolhimentoAmostraPapiloscopia)
    {
        this.tipoRecolhimentoAmostraPapiloscopia = tipoRecolhimentoAmostraPapiloscopia;
    }

    public TipoArma getTipoArma()
    {
        return tipoArma;
    }

    public void setTipoArma(TipoArma tipoArma)
    {
        this.tipoArma = tipoArma;
    }

    public TipoVestigioVida getTipoVestigio()
    {
        return tipoVestigio;
    }

    public void setTipoVestigio(TipoVestigioVida tipoVestigio)
    {
        this.tipoVestigio = tipoVestigio;
    }

    public String getObservacao()
    {
        return observacao;
    }

    public void setObservacao(String observacao)
    {
        this.observacao = observacao;
    }

    public String getNumDocumento()
    {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento)
    {
        this.numDocumento = numDocumento;
    }

    public Calibre getCalibreArma()
    {
        return calibreArma;
    }

    public void setCalibreArma(Calibre calibreArma)
    {
        this.calibreArma = calibreArma;
    }

    public Calibre getCalibreMunicao()
    {
        return calibreMunicao;
    }

    public void setCalibreMunicao(Calibre calibreMunicao)
    {
        this.calibreMunicao = calibreMunicao;
    }

    public DocumentoPessoa getTipoDocumento()
    {
        return tipoDocumento;
    }

    public void setTipoDocumento(DocumentoPessoa tipoDocumento)
    {
        this.tipoDocumento = tipoDocumento;
    }

    public String getObjetoRecolhidoPapiloscopia()
    {
        return objetoRecolhidoPapiloscopia;
    }

    public void setObjetoRecolhidoPapiloscopia(String objetoRecolhidoPapiloscopia)
    {
        this.objetoRecolhidoPapiloscopia = objetoRecolhidoPapiloscopia;
    }

    public TiposVestigioBiologico getTiposVestigioBiologico()
    {
        return tiposVestigioBiologico;
    }

    public void setTiposVestigioBiologico(TiposVestigioBiologico tiposVestigioBiologico)
    {
        this.tiposVestigioBiologico = tiposVestigioBiologico;
    }

    public String getNumeracaoArma()
    {
        return numeracaoArma;
    }

    public void setNumeracaoArma(String numeracaoArma)
    {
        this.numeracaoArma = numeracaoArma;
    }

    public int getQuantidadeMunicao()
    {
        return quantidadeMunicao;
    }

    public void setQuantidadeMunicao(int quantidadeMunicao)
    {
        this.quantidadeMunicao = quantidadeMunicao;
    }

    public TipoRecolhimentoAmostra_Biologica getTipoRecolhimentoAmostraBiologica()
    {
        return tipoRecolhimentoAmostraBiologica;
    }

    public void setTipoRecolhimentoAmostraBiologica(TipoRecolhimentoAmostra_Biologica tipoRecolhimentoAmostraBiologica)
    {
        this.tipoRecolhimentoAmostraBiologica = tipoRecolhimentoAmostraBiologica;
    }

    public String getObjetoRecolhido()
    {
        return objetoRecolhido;
    }

    public void setObjetoRecolhido(String objetoRecolhido)
    {
        this.objetoRecolhido = objetoRecolhido;
    }

    public boolean getCondicaoMunicao()
    {
        return condicaoMunicao;
    }

    public void setCondicaoMunicao(boolean condicaoMunicao)
    {
        this.condicaoMunicao = condicaoMunicao;
    }

    public boolean isCondicaoMunicao()
    {
        return condicaoMunicao;
    }

    public TiposMunicao getTiposMunicao()
    {
        return tiposMunicao;
    }

    public void setTipoMunicao(TiposMunicao tiposMunicao)
    {
        this.tiposMunicao = tiposMunicao;
    }

    public void LimparCampos()
    {
        tipoVestigio = null;
        observacao = null;
        numDocumento = null;
        tipoDocumento = null;
        tipoRecolhimentoAmostraPapiloscopia = null;
        objetoRecolhidoPapiloscopia = null;
        tiposVestigioBiologico = null;
        tipoRecolhimentoAmostraBiologica = null;
        tipoArma = null;
        numeracaoArma = null;
        calibreArma = null;
        quantidadeMunicao = 0;
        calibreMunicao = null;
        condicaoMunicao = false;
        objetoRecolhido = null;
    }

    @Override
    public String toString()
    {
        switch (getTipoVestigio())
        {
            case ARMA_DE_FOGO:
                if(getTipoArma() != null && getCalibreArma() != null && getNumeracaoArma()!= null)
                return "Arma de fogo do tipo: " + getTipoArma().getValor().toLowerCase() + " de calibre " + getCalibreArma().getValor() + " de numeração " + getNumeracaoArma();

            case BIOLOGICO:
                if(getTiposVestigioBiologico() != null && getTipoRecolhimentoAmostraBiologica()!= null)
                return "Vestígio biológico do tipo: " + getTiposVestigioBiologico().getValor().toLowerCase() + " coletado com: " + getTipoRecolhimentoAmostraBiologica();

            case DOCUMENTO:
                if(getTipoDocumento() != null && getNumDocumento()!= null)
                return "Documento do tipo: " + getTipoDocumento().getValor().toLowerCase() + " de número: " + getNumDocumento();

            case MUNICAO:
                if(getTiposMunicao()!= null && getCalibreMunicao()!= null)
                {
                    if (isCondicaoMunicao())
                        return getQuantidadeMunicao() + " munição(ões) do tipo: " + getTiposMunicao().getValor().toLowerCase() + " de calibre " + getCalibreMunicao().getValor().toLowerCase() + " em bom estado.";
                    else
                        return getQuantidadeMunicao() + " munição(ões) do tipo: " + getTiposMunicao().getValor().toLowerCase() + " de calibre " + getCalibreMunicao().getValor().toLowerCase() + " danificada.";
                }
            case VESTIGIO_PAPILOSCOPICO:
                if(getObjetoRecolhidoPapiloscopia()!= null && getTipoRecolhimentoAmostraPapiloscopia() != null)
                return "Vestígio recolhido para análise papiloscópica: " + getObjetoRecolhidoPapiloscopia() + " colhido com " + getTipoRecolhimentoAmostraPapiloscopia();

            case OUTRO:
                if(getObjetoRecolhido()!=null)
                return "Vestígio recolhido: " + getObjetoRecolhido();
        }
        return "Vestígio nulo";
    }
}
