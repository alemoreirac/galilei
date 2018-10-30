package Model.Vida;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Enums.DocumentoPessoa;
import Enums.Genero;
import Enums.SecaoImagem;
import Enums.Transito.TipoEnvolvidoTransito;
import Enums.Vida.IndiciosTempoMorte;
import Enums.Vida.ParteCorpo;
import Enums.Vida.PosicaoBraco;
import Enums.Vida.PosicaoCabeca;
import Enums.Vida.PosicaoPerna;
import Enums.Vida.PosicaoTorax;
import Enums.UnidadeTempo;
import Enums.Vida.TipoMorte;
import Model.Gravacao;
import Util.BuscadorEnum;
import Util.TempoUtil;

/**
 * Created by Pefoce on 20/11/2017.
 */

public class EnvolvidoVida extends SugarRecord
{
    @Expose
    Date dataInclusao;

    private String nome;

    private DocumentoPessoa documentoTipo;

    private String documentoValor;

    private Date nascimento;

    private Genero genero;

    private String vestes;

    private Gravacao gravacaoEnvolvido;

    private String Observacoes;

    private IndiciosTempoMorte indiciosTempoMorte;

    private TipoMorte tipoMorte;

    private EnderecoVida endereco;

    private PosicaoBraco posicaoBracoEsquerdo;

    private PosicaoBraco posicaoBracoDireito;

    private PosicaoPerna posicaoPernaEsquerda;

    private PosicaoPerna posicaoPernaDireita;

    private PosicaoTorax posicaoCorpo;

    private PosicaoCabeca posicaoCabeca;

//    public boolean isMorteViolenta()
//    {
//        return morteViolenta;
//    }
//
//    public void setMorteViolenta(boolean morteViolenta)
//    {
//        this.morteViolenta = morteViolenta;
//    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public DocumentoPessoa getDocumentoTipo()
    {
        return documentoTipo;
    }

    public void setDocumentoTipo(DocumentoPessoa documentoTipo)
    {
        this.documentoTipo = documentoTipo;
    }

    public EnvolvidoVida()
    {
        dataInclusao = Calendar.getInstance().getTime();
        this.nascimento = null;
        this.setNome("");
        this.setObservacoes("");
        this.setDocumentoValor("");
        this.setGenero(Genero.NAO_IDENTIFICADO);
        this.posicaoCorpo = PosicaoTorax.DECUBITO_DORSAL;
        this.posicaoBracoDireito = PosicaoBraco.ESTENDIDO;
        this.posicaoBracoEsquerdo = PosicaoBraco.ESTENDIDO;
        this.posicaoCabeca = PosicaoCabeca.APOIADA_SOLO;
        this.posicaoPernaDireita = PosicaoPerna.ESTENDIDO;
        this.posicaoPernaEsquerda = PosicaoPerna.ESTENDIDO;
    }

    public Date getDataInclusao()
    {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao)
    {
        this.dataInclusao = dataInclusao;
    }

    public EnderecoVida getEndereco()
    {
        return endereco;
    }

    public void setEndereco(EnderecoVida endereco)
    {
        this.endereco = endereco;
    }

    public String getDocumentoValor()
    {
        return documentoValor;
    }

    public void setDocumentoValor(String documentoValor)
    {
        this.documentoValor = documentoValor;
    }

    public Date getNascimento()
    {
        return nascimento;
    }

    public void setNascimento(Date nascimento)
    {
        this.nascimento = nascimento;
    }

    public Genero getGenero()
    {
        return genero;
    }

    public void setGenero(Genero genero)
    {
        this.genero = genero;
    }

    public String getVestes()
    {
        return vestes;
    }

    public void setVestes(String vestes)
    {
        this.vestes = vestes;
    }

    public String getObservacoes()
    {
        return Observacoes;
    }

    public void setObservacoes(String observacoes)
    {
        Observacoes = observacoes;
    }

    public IndiciosTempoMorte getIndiciosTempoMorte()
    {
        return indiciosTempoMorte;
    }

    public void setIndiciosTempoMorte(IndiciosTempoMorte indiciosTempoMorte)
    {
        this.indiciosTempoMorte = indiciosTempoMorte;
    }

    public PosicaoBraco getPosicaoBracoEsquerdo()
    {
        return posicaoBracoEsquerdo;
    }

    public Gravacao getGravacaoEnvolvido()
    {
        return gravacaoEnvolvido;
    }

    public void setGravacaoEnvolvido(Gravacao gravacaoEnvolvido)
    {
        this.gravacaoEnvolvido = gravacaoEnvolvido;
    }

    public void setPosicaoBracoEsquerdo(PosicaoBraco posicaoBracoEsquerdo)
    {
        this.posicaoBracoEsquerdo = posicaoBracoEsquerdo;
    }

    public PosicaoBraco getPosicaoBracoDireito()
    {
        return posicaoBracoDireito;
    }

    public void setPosicaoBracoDireito(PosicaoBraco posicaoBracoDireito)
    {
        this.posicaoBracoDireito = posicaoBracoDireito;
    }

    public PosicaoPerna getPosicaoPernaEsquerda()
    {
        return posicaoPernaEsquerda;
    }

    public void setPosicaoPernaEsquerda(PosicaoPerna posicaoPernaEsquerda)
    {
        this.posicaoPernaEsquerda = posicaoPernaEsquerda;
    }

    public PosicaoPerna getPosicaoPernaDireita()
    {
        return posicaoPernaDireita;
    }

    public void setPosicaoPernaDireita(PosicaoPerna posicaoPernaDireita)
    {
        this.posicaoPernaDireita = posicaoPernaDireita;
    }

    public PosicaoTorax getPosicaoCorpo()
    {
        return posicaoCorpo;
    }

    public void setPosicaoCorpo(PosicaoTorax posicaoCorpo)
    {
        this.posicaoCorpo = posicaoCorpo;
    }

    public PosicaoCabeca getPosicaoCabeca()
    {
        return posicaoCabeca;
    }

    public void setPosicaoCabeca(PosicaoCabeca posicaoCabeca)
    {
        this.posicaoCabeca = posicaoCabeca;
    }

    public void setDataNascimentoString(String s)
    {
        final Calendar c = Calendar.getInstance();
      try
      {
          c.setTime(TempoUtil.stringToDate(s));
      }catch (Exception e )
      {
          this.nascimento = null;
          return;
      }
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

//        if(year>)

        final Calendar cSave = Calendar.getInstance();

        cSave.set(Calendar.YEAR, year);
        cSave.set(Calendar.MONTH, month);
        cSave.set(Calendar.DAY_OF_MONTH, day);

        this.nascimento = cSave.getTime();
    }

    public List<SecaoImagem> getSecoesImagem()
    {
        ArrayList<SecaoImagem> secoesList = new ArrayList<>();
        List<LesaoEnvolvido> lesaoEnvolvidos = LesaoEnvolvido.find(LesaoEnvolvido.class, "envolvido_vida = ?", getId().toString());

        for (LesaoEnvolvido le : lesaoEnvolvidos)
        {
            if (le.getLesao() != null)
            {
                if (le.getLesao().getSecaoLesao() != null)
                {
                    SecaoImagem si = BuscadorEnum.EncontrarSecaoImagem(le.getLesao().getSecaoLesao(), genero);

                    if (!secoesList.contains(si))
                        secoesList.add(si);
                }
            }
        }
        return secoesList;
    }

    public TipoMorte getTipoMorte()
    {
        return tipoMorte;
    }

    public void setTipoMorte(TipoMorte tipoMorte)
    {
        this.tipoMorte = tipoMorte;
    }

    public String getDataNascimentoString()
    {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar c = Calendar.getInstance();

        if (this.getNascimento() != null)
            c.setTime(this.getNascimento());
        else
            return "--/--/----";

        return format.format(c.getTime());
    }
}