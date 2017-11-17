package Enums.Vida;

import com.orm.SugarRecord;

import java.util.Date;

import Enums.DocumentoPessoa;
import Enums.Genero;

/**
 * Created by Pefoce on 16/11/2017.
 */

public class EnvolvidoVida extends SugarRecord<EnvolvidoVida>
{
    private String nome;
    private Date nascimento;
    private String numDocumento;
    private DocumentoPessoa tipoDocumento;
    private float temperaturaCorporal;
    private int periodoMorte;
    private UnidadeTempo unidadeTempo;
    private Genero genero;

    private PosicaoTorax posicaoTorax;
    private PosicaoBraco posicaoBracoEsquerdo;
    private PosicaoBraco posicaoBracoDireito;
    private PosicaoPerna posicaoPernaEsquerda;
    private PosicaoPerna posicaoPernaDireita;
    private PosicaoCabeca posicaoCabeca;

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Date getNascimento()
    {
        return nascimento;
    }

    public void setNascimento(Date nascimento)
    {
        this.nascimento = nascimento;
    }

    public String getNumDocumento()
    {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento)
    {
        this.numDocumento = numDocumento;
    }

    public DocumentoPessoa getTipoDocumento()
    {
        return tipoDocumento;
    }

    public void setTipoDocumento(DocumentoPessoa tipoDocumento)
    {
        this.tipoDocumento = tipoDocumento;
    }

    public float getTemperaturaCorporal()
    {
        return temperaturaCorporal;
    }

    public void setTemperaturaCorporal(float temperaturaCorporal)
    {
        this.temperaturaCorporal = temperaturaCorporal;
    }

    public int getPeriodoMorte()
    {
        return periodoMorte;
    }

    public void setPeriodoMorte(int periodoMorte)
    {
        this.periodoMorte = periodoMorte;
    }

    public UnidadeTempo getUnidadeTempo()
    {
        return unidadeTempo;
    }

    public void setUnidadeTempo(UnidadeTempo unidadeTempo)
    {
        this.unidadeTempo = unidadeTempo;
    }

    public Genero getGenero()
    {
        return genero;
    }

    public void setGenero(Genero genero)
    {
        this.genero = genero;
    }

    public PosicaoTorax getPosicaoTorax()
    {
        return posicaoTorax;
    }

    public void setPosicaoTorax(PosicaoTorax posicaoTorax)
    {
        this.posicaoTorax = posicaoTorax;
    }

    public PosicaoBraco getPosicaoBracoEsquerdo()
    {
        return posicaoBracoEsquerdo;
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

    public PosicaoCabeca getPosicaoCabeca()
    {
        return posicaoCabeca;
    }

    public void setPosicaoCabeca(PosicaoCabeca posicaoCabeca)
    {
        this.posicaoCabeca = posicaoCabeca;
    }
}
