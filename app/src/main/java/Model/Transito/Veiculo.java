package Model.Transito;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import Enums.Cor;
import Enums.Transito.TipoCNH;
import Enums.Transito.TipoVeiculo;
import Enums.UF;
import Util.StringUtil;

public class Veiculo extends SugarRecord

{
    @Expose
    TipoVeiculo tipoVeiculo;
    @Expose
    String placa;
    @Expose
    String modelo;
    @Expose
    String marca;
    @Expose
    Cor cor;
    @Expose
    int anoModelo;
    @Expose
    int anoFabricacao;
    @Expose
    String municipioPlaca;
    @Expose
    UF ufPlaca;
    @Expose
    String nomeProprietario;
    @Expose
    String numeroDocumentoProprietario;
    @Expose
    TipoCNH categoriaProprietario;
    @Expose
    String nomeCondutor;
    @Expose
    String numeroDocumentoCondutor;
    @Expose
    TipoCNH categoriaCondutor;


    public Veiculo(String placa)
    {
        this.placa = placa;
    }

    public Veiculo()
    {
        this.modelo = "";
        this.anoModelo = 0;
        this.anoFabricacao = 0;
        this.placa = "0";
        this.cor = Cor.CINZA;
        this.nomeCondutor = "";
        this.nomeProprietario = "";
        this.numeroDocumentoCondutor = "";
        this.numeroDocumentoProprietario = "";
        this.categoriaCondutor = TipoCNH.NP;
        this.categoriaProprietario = TipoCNH.NP;
    }


    public String getNomeCondutor()
    {
        return nomeCondutor;
    }

    public void setNomeCondutor(String nomeCondutor)
    {
        this.nomeCondutor = nomeCondutor;
    }

    public String getNumeroDocumentoCondutor()
    {
        return numeroDocumentoCondutor;
    }

    public void setNumeroDocumentoCondutor(String numeroDocumentoCondutor)
    {
        this.numeroDocumentoCondutor = numeroDocumentoCondutor;
    }

    public TipoCNH getCategoriaCondutor()
    {
        return categoriaCondutor;
    }

    public void setCategoriaCondutor(TipoCNH categoriaCondutor)
    {
        this.categoriaCondutor = categoriaCondutor;
    }


    public TipoCNH getCategoriaProprietario()
    {
        return categoriaProprietario;
    }

    public void setCategoriaProprietario(TipoCNH categoriaProprietario)
    {
        this.categoriaProprietario = categoriaProprietario;
    }


    public String getNomeProprietario()
    {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario)
    {
        this.nomeProprietario = nomeProprietario;
    }

    public String getNumeroDocumentoProprietario()
    {
        return numeroDocumentoProprietario;
    }

    public void setNumeroDocumentoProprietario(String numeroDocumentoProprietario)
    {
        this.numeroDocumentoProprietario = numeroDocumentoProprietario;
    }

    public Cor getCor()
    {
        return cor;
    }

    public void setCor(Cor cor)
    {
        this.cor = cor;
    }

    public int getAnoModelo()
    {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo)
    {
        this.anoModelo = anoModelo;
    }

    public TipoVeiculo getTipoVeiculo()
    {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(TipoVeiculo tipoVeiculo)
    {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getPlaca()
    {
        return placa;
    }

    public void setPlaca(String placa)
    {
        this.placa = placa;
    }

    public String getMarca()
    {
        return marca;
    }

    public void setMarca(String marca)
    {
        this.marca = marca;
    }

    @Override
    public String toString()
    {
        return StringUtil.checkValue(this.modelo, -1, "(Sem modelo)") + " " + StringUtil.checkValue(this.placa, -1, "(Sem placa)");
    }

    public String getModelo()
    {
        return modelo;
    }

    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }

    public int getAnoFabricacao()
    {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao)
    {
        this.anoFabricacao = anoFabricacao;
    }

    public String getMunicipioPlaca()
    {
        return municipioPlaca;
    }

    public void setMunicipioPlaca(String municipioPlaca)
    {
        this.municipioPlaca = municipioPlaca;
    }

    public UF getUfPlaca()
    {
        return ufPlaca;
    }

    public void setUfPlaca(UF ufPlaca)
    {
        this.ufPlaca = ufPlaca;
    }
}
