package Util;

import android.widget.Spinner;

import java.util.List;

import Enums.AreaIntegradaSeguranca;
import Enums.Calibre;
import Enums.Comodo;
import Enums.IluminacaoArtificial;
import Enums.LocalPraia;
import Enums.LocalVeiculo;
import Enums.SecaoImagem;
import Enums.TipoAcesso;
import Enums.TipoArma;
import Enums.TipoLocal;
import Enums.TipoLocalCrime;
import Enums.Meteorologia;
import Enums.TipoRecolhimentoAmostra_Papiloscopia;
import Enums.TipoVegetacao;
import Enums.TiposMunicao;
import Enums.TiposVestigioBiologico;
import Enums.Transito.AtoresColisao;
import Enums.CategoriaFoto;
import Enums.ConclusaoTransito;
import Enums.Transito.CondicaoPista;
import Enums.Cor;
import Enums.DocumentoPessoa;
import Enums.DocumentoSolicitacao;
import Enums.Transito.EstadoSitioColisao;
import Enums.Genero;
import Enums.IluminacaoVia;
import Enums.Transito.Lesao;
import Enums.Transito.LocalObjeto;
import Enums.Transito.LocalPedestre;
import Enums.Orgao;
import Enums.OrientacaoGeografica;
import Enums.OrientacaoGeograficaComposta;
import Enums.Transito.Pavimentacao;
import Enums.PreservacaoLocal;
import Enums.Transito.Semaforo;
import Enums.Transito.SetorDano;
import Enums.Transito.SinalizacaoPare;
import Enums.Transito.TercoDano;
import Enums.Transito.TipoCNH;
import Enums.Transito.TipoDano;
import Enums.TipoInteracao;
import Enums.Transito.TipoJustificativa_Inconclusao;
import Enums.TipoOcorrencia;
import Enums.Transito.TipoVeiculo;
import Enums.Transito.TipoVestigioTransito;
import Enums.Transito.TipoVia;
import Enums.Transito.TipoEnvolvidoTransito;
import Enums.Transito.Topografia;
import Enums.UnidadeTempo;
import Enums.Vida.LocalizacaoLesao;
import Enums.Vida.NaturezaLesao;
import Enums.Vida.ParteCorpo;
import Enums.Vida.PosicaoBraco;
import Enums.Vida.PosicaoCabeca;
import Enums.Vida.PosicaoPerna;
import Enums.Vida.PosicaoTorax;
import Enums.Vida.Secao;
import Enums.Vida.TipoAberturaLocal;
import Enums.Vida.TipoOcorrenciaVida;
import Enums.Vida.TipoRecolhimentoAmostra_Biologica;
import Enums.Vida.TipoVestigioVida;
import Model.Transito.ColisaoTransito;
import Model.Transito.EnderecoTransito;
import Model.Transito.EnvolvidoTransito;
import Model.Foto;
import Model.Transito.Veiculo;
import Model.Vida.EnvolvidoVida;
import Model.Vida.VestigioVida;

/**
 * Created by Pefoce on 22/06/2017.
 */

public class BuscadorEnum
{
    //
//
    public static TipoOcorrencia BuscarTipoOcorrencia(String valor)
    {
        for (TipoOcorrencia item : TipoOcorrencia.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static SecaoImagem BuscarSecaoImagem(String valor)
    {
        for (SecaoImagem item : SecaoImagem.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static TipoVestigioTransito BuscarTipoVestigio(String valor)
    {
        for (TipoVestigioTransito item : TipoVestigioTransito.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static Meteorologia BuscarMeteorologia(String valor)
    {
        for (Meteorologia item : Meteorologia.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static ConclusaoTransito BuscarConclusao(String valor)
    {
        for (ConclusaoTransito item : ConclusaoTransito.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static TipoEnvolvidoTransito BuscarTipoEnvolvido(String valor)
    {
        for (TipoEnvolvidoTransito item : TipoEnvolvidoTransito.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static AreaIntegradaSeguranca BuscarAIS(String valor)
    {
        for (AreaIntegradaSeguranca item : AreaIntegradaSeguranca.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static LocalPedestre BuscarLocalPedestre(String valor)
    {
        for (LocalPedestre item : LocalPedestre.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static IluminacaoVia BuscarIluminacao(String valor)
    {
        for (IluminacaoVia item : IluminacaoVia.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static LocalObjeto BuscarLocalObjeto(String valor)
    {
        for (LocalObjeto item : LocalObjeto.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static TipoJustificativa_Inconclusao BuscarJustificativa(String valor)
    {
        for (TipoJustificativa_Inconclusao item : TipoJustificativa_Inconclusao.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static TipoVia BuscarTipoVia(String valor)
    {
        for (TipoVia item : TipoVia.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static DocumentoSolicitacao BuscarDocSolicitacao(String valor)
    {
        for (DocumentoSolicitacao item : DocumentoSolicitacao.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static Genero BuscarGenero(String valor)
    {
        for (Genero item : Genero.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static AtoresColisao BuscarAtoresColisao(String valor)
    {

        for (AtoresColisao item : AtoresColisao.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    //
//
    public static SetorDano BuscarSetorDano(String valor)
    {

        for (SetorDano item : SetorDano.values())
        {
            if (item.getValor().equals(valor))
                //if(SetorDano.valueOf(SetorDano.class,item.toString()).toString().equals(valor))
                return item;
        }
        return null;
    }

    public static NaturezaLesao BuscarNaturezaLesao(String valor)
    {
        for (NaturezaLesao item : NaturezaLesao.values())
        {
            //if (NaturezaLesao.valueOf(NaturezaLesao.class, item.toString()).toString().equals(valor))
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static LocalizacaoLesao BuscarLocalizacaoLesao(String valor)
    {
        for (LocalizacaoLesao item : LocalizacaoLesao.values())
        {
            //if (LocalizacaoLesao.valueOf(LocalizacaoLesao.class, item.toString()).toString().equals(valor))
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static Calibre BuscarCalibre(String valor)
    {
        for (Calibre item : Calibre.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TipoArma BuscarTipoArma(String valor)
    {
        for (TipoArma item : TipoArma.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TiposMunicao BuscarTipoMunicao(String valor)
    {
        for (TiposMunicao item : TiposMunicao.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static SetorDano BuscarSetorDanoSigla(String valor)
    {

        for (SetorDano item : SetorDano.values())
        {
            if (SetorDano.valueOf(SetorDano.class, item.toString()).toString().equals(valor))
                //if (item.getValor() == valor)
                return item;
        }
        return null;
    }
//

    public static TipoCNH BuscarTipoCNH(String valor)
    {
        for (TipoCNH item : TipoCNH.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static EstadoSitioColisao BuscarEstadoColisao(String valor)
    {
        for (EstadoSitioColisao item : EstadoSitioColisao.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static PreservacaoLocal BuscarPreservacaoLocal(String valor)
    {
        for (PreservacaoLocal item : PreservacaoLocal.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static CondicaoPista BuscarCondicaoPista(String valor)
    {
        for (CondicaoPista item : CondicaoPista.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static Pavimentacao BuscarPavimentacao(String valor)
    {
        for (Pavimentacao item : Pavimentacao.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static Semaforo BuscarSemaforizacao(String valor)
    {
        for (Semaforo item : Semaforo.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static SinalizacaoPare BuscarSinalizacaoPare(String valor)
    {
        for (SinalizacaoPare item : SinalizacaoPare.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static Lesao BuscarLesao(String valor)
    {
        for (Lesao item : Lesao.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static Topografia BuscarTopografia(String valor)
    {
        for (Topografia item : Topografia.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static IluminacaoArtificial BuscarIluminacaoArtificial(String valor)
    {
        for (IluminacaoArtificial item : IluminacaoArtificial.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static Orgao BuscarOrgao(String valor)
    {
        for (Orgao item : Orgao.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static OrientacaoGeografica BuscarOrientacao(String valor)
    {
        for (OrientacaoGeografica item : OrientacaoGeografica.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static OrientacaoGeograficaComposta BuscarOrientacaoComposta(String valor)
    {
        for (OrientacaoGeograficaComposta item : OrientacaoGeograficaComposta.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TipoInteracao BuscarTipoInteracao(String valor)
    {
        for (TipoInteracao item : TipoInteracao.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static DocumentoPessoa BuscarDocumentoPessoa(String valor)
    {
        for (DocumentoPessoa item : DocumentoPessoa.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TipoVeiculo BuscarTipoVeiculo(String valor)
    {
        for (TipoVeiculo item : TipoVeiculo.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TercoDano BuscarTercoDano(String valor)
    {
        for (TercoDano item : TercoDano.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static CategoriaFoto BuscarCategoriaFoto(String valor)
    {
        for (CategoriaFoto item : CategoriaFoto.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TipoDano BuscarTipoDano(String valor)
    {
        for (TipoDano item : TipoDano.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static Cor BuscarCor(String valor)
    {
        for (Cor item : Cor.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TipoOcorrenciaVida BuscarTipoOcorrenciaVida(String valor)
    {
        for (TipoOcorrenciaVida item : TipoOcorrenciaVida.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static ParteCorpo BuscarParteCorpo(String valor)
    {
        for (ParteCorpo item : ParteCorpo.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TipoAcesso BuscarTipoAcesso(String valor)
    {
        for (TipoAcesso item : TipoAcesso.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TipoLocal BuscarTipoLocal(String valor)
    {
        for (TipoLocal item : TipoLocal.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TipoLocalCrime BuscarTipoLocalCrime(String valor)
    {
        for (TipoLocalCrime item : TipoLocalCrime.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TipoAberturaLocal BuscarLocalAberto(String valor)
    {
        for (TipoAberturaLocal item : TipoAberturaLocal.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TipoVegetacao BuscarVegetacao(String valor)
    {
        for (TipoVegetacao item : TipoVegetacao.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static UnidadeTempo BuscarUnidadeTempo(String valor)
    {
        for (UnidadeTempo item : UnidadeTempo.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TiposVestigioBiologico BuscarVestigioBiologico(String valor)
    {
        for (TiposVestigioBiologico item : TiposVestigioBiologico.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static TipoRecolhimentoAmostra_Biologica BuscarRecolhimentoAmostraBiologica(String valor)
    {
        for (TipoRecolhimentoAmostra_Biologica item : TipoRecolhimentoAmostra_Biologica.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static TipoRecolhimentoAmostra_Papiloscopia BuscarRecolhimentoAmostraPapiloscopia(String valor)
    {
        for (TipoRecolhimentoAmostra_Papiloscopia item : TipoRecolhimentoAmostra_Papiloscopia.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static LocalVeiculo BuscarLocalVeiculo(String valor)
    {
        for (LocalVeiculo item : LocalVeiculo.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static Comodo BuscarComodo(String valor)
    {
        for (Comodo item : Comodo.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static LocalPraia BuscarLocaisPraia(String valor)
    {
        for (LocalPraia item : LocalPraia.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }


    public static Secao BuscarSecao(String valor)
    {
        for (Secao item : Secao.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static TipoVestigioVida BuscarVestigioVida(String valor)
    {
        for (TipoVestigioVida item : TipoVestigioVida.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static DocumentoPessoa BuscarTipoDocumento(String valor)
    {
        for (DocumentoPessoa item : DocumentoPessoa.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static PosicaoBraco BuscarPosicaoBraco(String valor)
    {
        for (PosicaoBraco item : PosicaoBraco.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static PosicaoCabeca BuscarPosicaoCabeca(String valor)
    {
        for (PosicaoCabeca item : PosicaoCabeca.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static PosicaoTorax BuscarPosicaoTorax(String valor)
    {
        for (PosicaoTorax item : PosicaoTorax.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    public static PosicaoPerna BuscarPosicaoPerna(String valor)
    {

        for (PosicaoPerna item : PosicaoPerna.values())
        {
            if (item.getValor() == valor)
                return item;
        }
        return null;
    }

    //
    public static Integer getIndex(Spinner spinner, String myString)
    {
        for (int i = 0; i < spinner.getCount(); i++)
        {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString))
            {
                return i;
            }
        }
        return 0;
    }

    public static Integer getVeiculoIndexById(Spinner spinner, Long id)
    {
        for (int i = 0; i < spinner.getCount(); i++)
        {
            if (((Veiculo) spinner.getItemAtPosition(i)).getId() != null)
            {
                if (((Veiculo) spinner.getItemAtPosition(i)).getId().equals(id))
                    return i;
            }
        }
        return null;
    }

    public static Integer getEnvolvidoIndexById(Spinner spinner, Long id)
    {
        for (int i = 0; i < spinner.getCount(); i++)
        {
            if (((EnvolvidoTransito) spinner.getItemAtPosition(i)).getId() == id)
            {
                return i;
            }
        }
        return null;
    }

    public static Integer getEnderecoIndexById(Spinner spinner, Long id)
    {
        for (int i = 0; i < spinner.getCount(); i++)
        {
            if (((EnderecoTransito) spinner.getItemAtPosition(i)).getId() != null)
            {
                if (((EnderecoTransito) spinner.getItemAtPosition(i)).getId().equals(id))
                    return i;
            }
        }
        return null;
    }

    public static int PegarPosicaoVeiculo(List<Veiculo> veiculos, Veiculo v)
    {
        for (int i = 0; i < veiculos.size(); i++)
        {
            if (veiculos.get(i).getId() != null)
            {
                if (veiculos.get(i).getId().equals(v.getId()))
                    return i;
            }
        }
        return 0;
    }

    public static int PegarPosicaoEndereco(List<EnderecoTransito> enderecos, EnderecoTransito et)
    {
        for (int i = 0; i < enderecos.size(); i++)
        {
            if (enderecos.get(i).getId().equals(et.getId()))
            {
                return i;
            }
        }
        return -1;
    }

    public static int PegarPosicaoEnvolvidoVida(List<EnvolvidoVida> envolvidos, EnvolvidoVida ev)
    {
        for (int i = 0; i < envolvidos.size(); i++)
        {
            if (envolvidos.get(i).getId().equals(ev.getId()))
            {
                return i;
            }
        }
        return -1;
    }


    public static int PegarPosicaoVestigioVida(List<VestigioVida> vestigios, VestigioVida vv)
    {
        for (int i = 0; i < vestigios.size(); i++)
        {
            if (vestigios.get(i).getId().equals(vv.getId()))
            {
                return i;
            }
        }
        return -1;
    }

    public static int PegarPosicaoEnvolvidoTransito(List<EnvolvidoTransito> envolvidos, EnvolvidoTransito et)
    {
        for (int i = 0; i < envolvidos.size(); i++)
        {
            if (envolvidos.get(i).getId().equals(et.getId()))
            {
                return i;
            }
        }
        return -1;
    }

    public static int PegarPosicaoFoto(List<Foto> fotos, Foto foto)
    {
        for (int i = 0; i < fotos.size(); i++)
        {
            if (fotos.get(i).getId().equals(foto.getId()))
            {
                return i;
            }
        }
        return -1;
    }

    public static int PegarPosicaoColisao(List<ColisaoTransito> colisoes, ColisaoTransito ct)
    {
        for (int i = 0; i < colisoes.size(); i++)
        {
            if (colisoes.get(i).getId().equals(ct.getId()))
            {
                return i;
            }
        }
        return -1;
    }


    public static ParteCorpo EncontrarParteCorpo(Secao secao)
    {
        switch (secao)
        {
            case SETOR_SUPERIOR_BRACO_ESQUERDO:
            case SETOR_INFERIOR_BRACO_ESQUERDO:
            case COTOVELO_ESQUERDO:
            case SETOR_SUPERIOR_ANTEBRACO_ESQUERDO:
            case SETOR_INFERIOR_ANTEBRACO_ESQUERDO:
            case MAO_ESQUERDA:
            case SETOR_SUPERIOR_BRACO_DIREITO:
            case SETOR_INFERIOR_BRACO_DIREITO:
            case COTOVELO_DIREITO:
            case SETOR_SUPERIOR_ANTEBRACO_DIREITO:
            case SETOR_INFERIOR_ANTEBRACO_DIREITO:
            case MAO_DIREITA:
                return ParteCorpo.BRACOS;

            case SETOR_SUPERIOR_COXA_DIREITA:
            case SETOR_INFERIOR_COXA_DIREITA:
            case JOELHO_DIREITO:
            case SETOR_SUPERIOR_PERNA_DIREITA:
            case SETOR_INFERIOR_PERNA_DIREITA:
            case PE_DIREITO:

            case SETOR_SUPERIOR_COXA_ESQUERDA:
            case SETOR_INFERIOR_COXA_ESQUERDA:
            case JOELHO_ESQUERDO:
            case SETOR_SUPERIOR_PERNA_ESQUERDA:
            case SETOR_INFERIOR_PERNA_ESQUERDA:
            case PE_ESQUERDO:
                return ParteCorpo.PERNAS;

            case CLAVICULAR_ANTERIOR_DIREITO:
            case PEITORAL_DIREITO:
            case HIPOCONDRIO_DIREITO:
            case FLANCO_DIREITO:
            case ILIACO_ANTERIOR_DIREITO:
            case CLAVICULAR_ANTERIOR_ESQUERDO:
            case PEITORAL_ESQUERDO:
            case HIPOCONDRIO_ESQUERDO:
            case FLANCO_ESQUERDO:
            case ILIACO_ANTERIOR_ESQUERDO:
            case CLAVICULAR_POSTERIOR_DIREITO:
            case ESCAPULAR_DIREITO:
            case LOMBAR_DIREITO:
            case ILIACO_POSTERIOR_DIREITO:
            case GLUTEO_DIREITO:
            case CLAVICULAR_POSTERIOR_ESQUERDO:
            case ESCAPULAR_ESQUERDO:
            case LOMBAR_ESQUERDO:
            case ILIACO_POSTERIOR_ESQUERDO:
            case GLUTEO_ESQUERDO:
            case GENITAL:
            case ANUS:
                return ParteCorpo.TORAX;

            case PARIETAL_ESQUERDA:
            case AURICULAR_ESQUERDA:
            case FRONTAL_ESQUERDA:
            case OCULAR_ESQUERDA:
            case MALAR_ESQUERDA:
            case NASAL_ESQUERDA:
            case BUCAL_ESQUERDA:
            case MENTONIANA_ESQUERDA:
                //    case MANDIBULAR_ESQUERDA:
            case CAROTIDIANA_ESQUERDA:
            case CERVICAL_ESQUERDA:
            case OCCIPITAL_ESQUERDA:
            case PARIETAL_DIREITA:
            case AURICULAR_DIREITA:
            case FRONTAL_DIREITA:
            case OCULAR_DIREITA:
            case MALAR_DIREITA:
            case NASAL_DIREITA:
            case BUCAL_DIREITA:
            case MENTONIANA_DIREITA:
                //   case MANDIBULAR_DIREITA:
            case CAROTIDIANA_DIREITA:
            case CERVICAL_DIREITA:
            case OCCIPITAL_DIREITA:

                return ParteCorpo.CABECA;


            default:
                return null;
        }
    }

    public static SecaoImagem EncontrarSecaoImagem(Secao secao, Genero genero)
    {
        switch (secao)
        {
            case SETOR_SUPERIOR_BRACO_ESQUERDO:
            case SETOR_INFERIOR_BRACO_ESQUERDO:
            case COTOVELO_ESQUERDO:
            case SETOR_SUPERIOR_ANTEBRACO_ESQUERDO:
            case SETOR_INFERIOR_ANTEBRACO_ESQUERDO:
            case MAO_ESQUERDA:

            case SETOR_SUPERIOR_BRACO_DIREITO:
            case SETOR_INFERIOR_BRACO_DIREITO:
            case COTOVELO_DIREITO:
            case SETOR_SUPERIOR_ANTEBRACO_DIREITO:
            case SETOR_INFERIOR_ANTEBRACO_DIREITO:
            case MAO_DIREITA:


            case SETOR_SUPERIOR_COXA_DIREITA:
            case SETOR_INFERIOR_COXA_DIREITA:
            case JOELHO_DIREITO:
            case SETOR_SUPERIOR_PERNA_DIREITA:
            case SETOR_INFERIOR_PERNA_DIREITA:
            case PE_DIREITO:


            case SETOR_SUPERIOR_COXA_ESQUERDA:
            case SETOR_INFERIOR_COXA_ESQUERDA:
            case JOELHO_ESQUERDO:
            case SETOR_SUPERIOR_PERNA_ESQUERDA:
            case SETOR_INFERIOR_PERNA_ESQUERDA:
            case PE_ESQUERDO:


            case CLAVICULAR_ANTERIOR_DIREITO:
            case PEITORAL_DIREITO:
            case HIPOCONDRIO_DIREITO:
            case FLANCO_DIREITO:
            case ILIACO_ANTERIOR_DIREITO:
            case CLAVICULAR_ANTERIOR_ESQUERDO:
            case PEITORAL_ESQUERDO:
            case HIPOCONDRIO_ESQUERDO:
            case FLANCO_ESQUERDO:
            case ILIACO_ANTERIOR_ESQUERDO:

                if (genero.equals(Genero.FEMININO))
                    return SecaoImagem.ANTERIOR_FEMININO;
                else
                    return SecaoImagem.ANTERIOR_MASCULINO;

            case CLAVICULAR_POSTERIOR_DIREITO:
            case ESCAPULAR_DIREITO:
            case LOMBAR_DIREITO:
            case ILIACO_POSTERIOR_DIREITO:
            case GLUTEO_DIREITO:
            case CLAVICULAR_POSTERIOR_ESQUERDO:
            case ESCAPULAR_ESQUERDO:
            case LOMBAR_ESQUERDO:
            case ILIACO_POSTERIOR_ESQUERDO:
            case GLUTEO_ESQUERDO:
            case GENITAL:
            case ANUS:
                if (genero.equals(Genero.FEMININO))
                    return SecaoImagem.POSTERIOR_FEMININO;
                else
                    return SecaoImagem.POSTERIOR_MASCULINO;



            case PARIETAL_ESQUERDA:
            case AURICULAR_ESQUERDA:
            case FRONTAL_ESQUERDA:
            case OCULAR_ESQUERDA:
            case MALAR_ESQUERDA:
            case NASAL_ESQUERDA:
            case BUCAL_ESQUERDA:
            case MENTONIANA_ESQUERDA:
                //    case MANDIBULAR_ESQUERDA:
            case CAROTIDIANA_ESQUERDA:
            case CERVICAL_ESQUERDA:
            case OCCIPITAL_ESQUERDA:

                if (genero.equals(Genero.FEMININO))
                    return SecaoImagem.CABECA_FEMININO_ESQUERDA;
                else
                    return SecaoImagem.CABECA_MASCULINO_ESQUERDA;




            case PARIETAL_DIREITA:
            case AURICULAR_DIREITA:
            case FRONTAL_DIREITA:
            case OCULAR_DIREITA:
            case MALAR_DIREITA:
            case NASAL_DIREITA:
            case BUCAL_DIREITA:
            case MENTONIANA_DIREITA:
                //   case MANDIBULAR_DIREITA:
            case CAROTIDIANA_DIREITA:
            case CERVICAL_DIREITA:
            case OCCIPITAL_DIREITA:

                if (genero.equals(Genero.FEMININO))
                    return SecaoImagem.CABECA_FEMININO_DIREITA;
                else
                    return SecaoImagem.CABECA_MASCULINO_DIREITA;




            default:
                return null;
        }
    }
}
