package Model.Vida;

import com.orm.SugarRecord;

import Enums.Comodo;
import Enums.IluminacaoArtificial;
import Enums.LocalPraia;
import Enums.LocalVeiculo;
import Enums.Meteorologia;
import Enums.TipoLocal;
import Enums.TipoLocalCrime;
import Enums.TipoVegetacao;
import Enums.Transito.LocalObjeto;
import Enums.Transito.Pavimentacao;
import Enums.Transito.TipoCNH;
import Enums.Transito.TipoVia;
import Enums.Vida.TipoAberturaLocal;
import Model.Endereco;
import Model.Gravacao;

/**
 * Created by Pefoce on 15/11/2017.
 */

public class EnderecoVida extends SugarRecord<EnderecoVida>
{
   private Endereco endereco;

   private TipoLocalCrime tipoLocalCrime;

   private String latitude;

   private String longitude;


   private IluminacaoArtificial tipoIluminacao;

   private TipoVegetacao tipoVegetacao;

   private TipoAberturaLocal localAberto;

    private boolean veiculoEnvolvido;

    private String modeloVeiculo;

    private String marcaVeiculo;

    private String placaVeiculo;

    private LocalVeiculo localVeiculo;

    private String nomeProprietario;

    private Meteorologia condicoesClimaticas;

    private String numeroDocumentoProprietario;

    private TipoCNH categoriaProprietario;

    private Pavimentacao pavimentacao;

    private LocalObjeto posicaoVia;

    private Long ocorrenciaId;

    private TipoVia tipoVia;

    private String observacao;

    private LocalPraia localPraia;

    private Comodo comodo;

    private TipoLocal localResidencia;

   private Gravacao gravacaoEndereco;

   public TipoLocal getLocalResidencia()
   {
      return localResidencia;
   }

   public LocalPraia getLocalPraia()
   {
      return localPraia;
   }

   public EnderecoVida()
   {
      this.endereco = new Endereco();

   }

   public void setLocalPraia(LocalPraia localPraia)
   {
      this.localPraia = localPraia;
   }

   public void setLocalResidencia(TipoLocal localResidencia)
   {
      this.localResidencia = localResidencia;
   }
   public TipoVia getTipoVia()
   {
      return tipoVia;
   }

   public void setTipoVia(TipoVia tipoVia)
   {
      this.tipoVia = tipoVia;
   }

   public boolean getVeiculoEnvolvido()
   {
      return veiculoEnvolvido;
   }

   public void setVeiculoEnvolvido(boolean veiculoEnvolvido)
   {
      this.veiculoEnvolvido = veiculoEnvolvido;
   }

   public String getModeloVeiculo()
   {
      return modeloVeiculo;
   }

   public void setModeloVeiculo(String modeloVeiculo)
   {
      this.modeloVeiculo = modeloVeiculo;
   }

   public String getPlacaVeiculo()
   {
      return placaVeiculo;
   }

   public void setPlacaVeiculo(String placaVeiculo)
   {
      this.placaVeiculo = placaVeiculo;
   }

   public LocalVeiculo getLocalVeiculo()
   {
      return localVeiculo;
   }

   public void setLocalVeiculo(LocalVeiculo localVeiculo)
   {
      this.localVeiculo = localVeiculo;
   }

   public String getNomeProprietario()
   {
      return nomeProprietario;
   }

   public void setNomeProprietario(String nomeProprietario)
   {
      this.nomeProprietario = nomeProprietario;
   }

   public Gravacao getGravacaoEndereco()
   {
      return gravacaoEndereco;
   }

   public void setGravacaoEndereco(Gravacao gravacaoEndereco)
   {
      this.gravacaoEndereco = gravacaoEndereco;
   }

   public String getNumeroDocumentoProprietario()
   {
      return numeroDocumentoProprietario;
   }

   public void setNumeroDocumentoProprietario(String numeroDocumentoProprietario)
   {
      this.numeroDocumentoProprietario = numeroDocumentoProprietario;
   }

   public TipoCNH getCategoriaProprietario()
   {
      return categoriaProprietario;
   }

   public void setCategoriaProprietario(TipoCNH categoriaProprietario)
   {
      this.categoriaProprietario = categoriaProprietario;
   }

   public Pavimentacao getPavimentacao()
   {
      return pavimentacao;
   }

   public Comodo getComodo()
   {
      return comodo;
   }

   public void setComodo(Comodo comodo)
   {
      this.comodo = comodo;
   }

   public void setPavimentacao(Pavimentacao pavimentacao)
   {
      this.pavimentacao = pavimentacao;
   }

   public LocalObjeto getPosicaoVia()
   {
      return posicaoVia;
   }

   public void setPosicaoVia(LocalObjeto posicaoVia)
   {
      this.posicaoVia = posicaoVia;
   }

   public Long getOcorrenciaId()
   {
      return ocorrenciaId;
   }

   public void setOcorrenciaId(Long ocorrenciaId)
   {
      this.ocorrenciaId = ocorrenciaId;
   }


   public Endereco getEndereco()
   {
      return endereco;
   }

   public void setEndereco(Endereco endereco)
   {
      this.endereco = endereco;
   }

   public TipoLocalCrime getTipoLocalCrime()
   {
      return tipoLocalCrime;
   }

   public void setTipoLocalCrime(TipoLocalCrime tipoLocalCrime)
   {
      this.tipoLocalCrime = tipoLocalCrime;
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

    public Meteorologia getCondicoesClimaticas()
    {
        return condicoesClimaticas;
    }

    public void setCondicoesClimaticas(Meteorologia condicoesClimaticas)
    {
        this.condicoesClimaticas = condicoesClimaticas;
    }


   public String getObservacao()
   {
      return observacao;
   }

   public void setObservacao(String observacao)
   {
      this.observacao = observacao;
   }

   public IluminacaoArtificial getTipoIluminacao()
   {
      return tipoIluminacao;
   }

   public void setTipoIluminacao(IluminacaoArtificial tipoIluminacao)
   {
      this.tipoIluminacao = tipoIluminacao;
   }

    public String getMarcaVeiculo()
    {
        return marcaVeiculo;
    }

    public void setMarcaVeiculo(String marcaVeiculo)
    {
        this.marcaVeiculo = marcaVeiculo;
    }

    public TipoVegetacao getTipoVegetacao()
   {
      return tipoVegetacao;
   }

   public void setTipoVegetacao(TipoVegetacao tipoVegetacao)
   {
      this.tipoVegetacao = tipoVegetacao;
   }

   public TipoAberturaLocal getLocalAberto()
   {
      return localAberto;
   }

   public void setLocalAberto(TipoAberturaLocal localAberto)
   {
      this.localAberto = localAberto;
   }

   @Override
   public String toString()
   {
      if(endereco!=null)
      return tipoVia.getValor()+" "+ endereco.getDescricao()+" "+ endereco.getBairro()+ " " + endereco.getCidade();
      else
         return "Sem Endereço.";
   }

   public void AnularCampos()
   {
      this.localAberto = null;


      this.latitude = "";
      this.longitude = "";

      this.tipoIluminacao = null;
      this.observacao = "";

      if(this.endereco!=null)
      {
         this.endereco.setBairro("");
         this.endereco.setCidade("");
         this.endereco.setEndereco("");
      }

      this.localResidencia = null;
      this.ocorrenciaId = null;
      this.posicaoVia = null;
      this.localPraia = null;
//      this.modeloVeiculo = null;
//      this.veiculoEnvolvido = false;
//      this.placaVeiculo = "";
//      this.nomeProprietario = "";
//      this.numeroDocumentoProprietario = "";
//      this.categoriaProprietario = null;
      this.localVeiculo = null;
      this.tipoLocalCrime = null;
      this.tipoIluminacao = null;
      this.condicoesClimaticas = null;
      this.tipoVia = null;

      this.pavimentacao = null;
      this.tipoVegetacao = null;
   }
}
