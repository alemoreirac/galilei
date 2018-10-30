package Util;


import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Control.FotoBusiness;
import Control.Transito.ColisaoBusiness;
import Control.Transito.DanoBusiness;
import Control.Transito.EnderecoTransitoBusiness;
import Control.Transito.EnvolvidoTransitoBusiness;
import Control.Transito.VeiculoBusiness;
import Control.Transito.VestigioTransitoBusiness;
import Control.Vida.EnderecoVidaBusiness;
import Control.Vida.EnvolvidoVidaBusiness;
import Control.Vida.LesaoBusiness;
import Control.Vida.VestigioVidaBusiness;
import Model.Bairro;
import Model.Delegacia;
import Model.Foto;
import Model.Municipio;
import Model.Ocorrencia;
import Model.Transito.ColisaoTransito;
import Model.Transito.EnderecoTransito;
import Model.Transito.EnvolvidoTransito;
import Model.Transito.Veiculo;
import Model.Usuario;
import Model.Vida.EnderecoVida;
import Model.Vida.EnvolvidoVida;
import Model.Vida.VestigioVida;

/**
 * Created by Pefoce on 02/08/2018.
 */

public class UpdateUtil
{
    private static List<Usuario> usuariosLocais;
    private static Usuario[] usuariosGalileu;

    private static List<Municipio> municipiosLocais;
    private static Municipio[] municipiosGalileu;

    private static List<Delegacia> delegaciasLocais;
    private static Delegacia[] delegaciasGalileu;

    private static List<Bairro> bairrosLocais;
    private static Bairro[] bairrosGalileu;

    public static void updateUsers(String jsonUsuariosGalileu)
    {
        boolean possuiUsuario = false;

        Gson gson = new Gson();

        usuariosGalileu = (gson.fromJson(jsonUsuariosGalileu,Usuario[].class));

        usuariosLocais = Usuario.listAll(Usuario.class);

        if(usuariosLocais==null)
            usuariosLocais = new ArrayList<>();

        if(usuariosGalileu != null)
        {
            for(Usuario uGalileu : usuariosGalileu)
            {
                for(Usuario uLocal : usuariosLocais)
                {
                    if(uLocal!=null && uLocal.getIdGalileu()!=null && uGalileu!=null && uGalileu.getId()!=null &&
                            uLocal.getIdGalileu().equals(uGalileu.getId()))
                    {
                        possuiUsuario = true;
                        break;
                    }
                    else
                        possuiUsuario = false;
                }
                if(!possuiUsuario)
                {
                    Usuario novoUsuario = new Usuario(uGalileu.getEmail(),uGalileu.getNome(),uGalileu.getPassword(), uGalileu.getId());

                    novoUsuario.save();

                    usuariosLocais.add(novoUsuario);
                }
            }
        }
    }

    public static void updateMunicipios(String jsonMunicipiosGalileu)
    {
        boolean possuiMunicipio = false;

        Gson gson = new Gson();

        municipiosGalileu = (gson.fromJson(jsonMunicipiosGalileu,Municipio[].class));

        municipiosLocais = Municipio.listAll(Municipio.class);

        if(municipiosLocais==null)
            municipiosLocais = new ArrayList<>();

        if(usuariosGalileu != null)
        {
            for(Municipio mGalileu : municipiosGalileu)
            {
                for(Municipio mLocal : municipiosLocais)
                {
                    if(mLocal!=null && mLocal.getIdGalileu()!=null && mGalileu!=null && mGalileu.getId()!=null &&
                            mLocal.getIdGalileu().equals(mGalileu.getId()))
                    {
                        possuiMunicipio = true;
                        break;
                    }
                    else
                        possuiMunicipio = false;
                }
                if(!possuiMunicipio)
                {
//                    Usuario novoUsuario = new Usuario(mGalileu.getEmail(),mGalileu.getNome(),mGalileu.getPassword(), mGalileu.getId());

                    Municipio novoMunicipio = new Municipio(mGalileu.getDescricao(),mGalileu.getId());

                    novoMunicipio.save();

                    municipiosLocais.add(novoMunicipio);
                }
            }
        }
    }

    public static void updateDelegacias(String jsonDelegaciasGalileu)
    {
        boolean possuiDelegacia = false;

        Gson gson = new Gson();

        delegaciasGalileu = (gson.fromJson(jsonDelegaciasGalileu, Delegacia[].class));

        delegaciasLocais = Delegacia.listAll(Delegacia.class);

        if(delegaciasLocais==null)
            delegaciasLocais= new ArrayList<>();

        if(delegaciasGalileu != null)
        {
            for(Delegacia dGalileu : delegaciasGalileu)
            {
                for(Delegacia dLocal : delegaciasLocais)
                {
                    if(dLocal!=null && dLocal.getIdGalileu()!=null && dGalileu!=null && dGalileu.getId()!=null &&
                            dLocal.getIdGalileu().equals(dGalileu.getId()))
                    {
                        possuiDelegacia = true;
                        break;
                    }
                    else
                        possuiDelegacia = false;
                }
                if(!possuiDelegacia)
                {
                    Delegacia novaDelegacia = new Delegacia(dGalileu.getDescricao(),dGalileu.getId());

                    novaDelegacia.save();

                    delegaciasLocais.add(novaDelegacia);
                }
            }
        }
    }


    public static void updateBairros(String jsonBairrosGalileu)
    {
        boolean possuiBairro = false;

        Gson gson = new Gson();

        bairrosGalileu = (gson.fromJson(jsonBairrosGalileu, Bairro[].class));

        bairrosLocais = Bairro.listAll(Bairro.class);

        if(bairrosLocais==null)
            bairrosLocais= new ArrayList<>();

        if(bairrosGalileu != null)
        {
            for(Bairro bGalileu : bairrosGalileu)
            {
                for(Bairro bLocal : bairrosLocais)
                {
                    if(bLocal!=null && bLocal.getIdGalileu()!=null && bGalileu!=null && bGalileu.getId()!=null &&
                            bLocal.getIdGalileu().equals(bGalileu.getId()))
                    {
                        possuiBairro = true;
                        break;
                    }
                    else
                        possuiBairro = false;
                }
                if(!possuiBairro)
                {
                    Bairro novoBairro = new Bairro(bGalileu.getDescricao(),bGalileu.getId());

                    novoBairro.save();

                    bairrosLocais.add(novoBairro);
                }
            }
        }
    }

    public static JsonObject construirPacoteTransito(Ocorrencia ocorrencia)
    {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

        List<EnderecoTransito> enderecos = EnderecoTransitoBusiness.findEnderecoByOcorrenciaId(ocorrencia.getId());
        List<Veiculo> veiculos = VeiculoBusiness.findVeiculosByOcorrenciaId(ocorrencia.getId());
        List<ColisaoTransito> colisoes = ColisaoBusiness.findColisoesByOcorrenciaId(ocorrencia.getId());
        List<EnvolvidoTransito> envolvidos = EnvolvidoTransitoBusiness.findEnvolvidoByOcorrenciaId(ocorrencia.getId());
        List<Foto> fotos = FotoBusiness.findFotosByOcorrenciaTransitoId(ocorrencia.getId());

//        File f  =new File(fotos.get(0).getArquivo());
//
//        Object o = FileUtil.toByteArray(f);

//        Base64.encode(FileUtil.toByteArray(f),Base64.DEFAULT);

        JsonArray arrayEnderecos = gson.toJsonTree(enderecos).getAsJsonArray();
        JsonArray arrayVeiculos = gson.toJsonTree(veiculos).getAsJsonArray();
        JsonArray arrayEnvolvidos = gson.toJsonTree(envolvidos).getAsJsonArray();
        JsonArray arrayColisoes = gson.toJsonTree(colisoes).getAsJsonArray();
        JsonArray arrayFotos = gson.toJsonTree(fotos).getAsJsonArray();

        for(int i = 0 ; i < veiculos.size();i++)
            arrayVeiculos.get(i).getAsJsonObject().add("danos",gson.toJsonTree(DanoBusiness.findDanosByVeiculo(veiculos.get(i).getId())));

        for(int i = 0 ; i < colisoes.size();i++)
            arrayColisoes.get(i).getAsJsonObject().add("vestigios",gson.toJsonTree(VestigioTransitoBusiness.findVestigiosByColisao(colisoes.get(i).getId())));

        for(int i = 0 ; i < colisoes.size();i++)
            arrayColisoes.get(i).getAsJsonObject().add("pedestres",gson.toJsonTree(EnvolvidoTransitoBusiness.findEnvolvidosByColisao(colisoes.get(i).getId())));


//        arrayVeiculos.;
//        arrayVeiculos.add
//        JsonObject jsonOcorrencia = new JsonObject();
//        jsonOcorrencia.add("ocorrencia", ocorrencia);

        JsonObject jsonResultado = new JsonObject();

        jsonResultado.add("enderecos", arrayEnderecos);
        jsonResultado.add("veiculos", arrayVeiculos);
        jsonResultado.add("envolvidos", arrayEnvolvidos);
        jsonResultado.add("colisoes", arrayColisoes);
        jsonResultado.add("fotos", arrayFotos);
        jsonResultado.add("ocorrencia",gson.toJsonTree(ocorrencia));
//        jsonResultado.add("arquivo",gson.toJsonTree(o));

        return   jsonResultado.getAsJsonObject();
    }

    public static String construirPacoteVida(Ocorrencia ocorrencia)
    {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

        ocorrencia.getOcorrenciaVida().setOcorrenciaID(null);

        List<EnderecoVida> enderecos = EnderecoVidaBusiness.findEnderecosByOcorrenciaId(ocorrencia.getOcorrenciaVida().getId());
        List<VestigioVida> vestigios = VestigioVidaBusiness.findVestigioByOcorrenciaId(ocorrencia.getOcorrenciaVida().getId());
        List<EnvolvidoVida> envolvidos = EnvolvidoVidaBusiness.findEnvolvidosByOcorrenciaId(ocorrencia.getOcorrenciaVida().getId());
        List<Foto> fotos = FotoBusiness.findFotosByOcorrenciaVidaId(ocorrencia.getOcorrenciaVida().getId());


        JsonArray arrayEnderecos = gson.toJsonTree(enderecos).getAsJsonArray();
        JsonArray arrayVestigios = gson.toJsonTree(vestigios).getAsJsonArray();
        JsonArray arrayEnvolvidos = gson.toJsonTree(envolvidos).getAsJsonArray();
        JsonArray arrayFotos = gson.toJsonTree(fotos).getAsJsonArray();

        for(int i = 0 ; i < envolvidos.size();i++)
            arrayEnvolvidos.get(i).getAsJsonObject().add("lesoes",gson.toJsonTree(LesaoBusiness.findLesaoByEnvolvido(envolvidos.get(i).getId())));


//        try
//        {
//            for(int i = 0 ; i < envolvidos.size();i++)
//            {
//                File file = new File(fotos.get(i).getArquivo());
//                arrayFotos.get(i).getAsJsonObject().addProperty("base64", gson.toJson(Base64.encode(IOUtils.toByteArray(file), Base64.DEFAULT)));
//            }
//
//        } catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }

        JsonObject jsonResultado = new JsonObject();

        jsonResultado.add("enderecos", arrayEnderecos);
        jsonResultado.add("vestigios", arrayVestigios);
        jsonResultado.add("envolvidos", arrayEnvolvidos);
        jsonResultado.add("fotos", arrayFotos);
        jsonResultado.add("ocorrencia",gson.toJsonTree(ocorrencia));

        return   jsonResultado.toString();
    }

    public static void testee() throws JSONException
    {

        String s = "{\"enderecos\":[{\"bairro\":{\"descricao\":\"Meireles\",\"id\":112},\"comodo\":\"AREA_SERVICO\",\"complemento\":\"gjfjf\",\"condicoesClimaticas\":\"TEMPO_ABERTO\",\"dataInclusao\":\"2018-08-22\",\"descricaoEndereco\":\"Vsngndgngd\",\"latitude\":\"3º43'7\",\"localAberto\":\"ABERTO\",\"localResidencia\":\"EXTERNO\",\"longitude\":\"38º32'8\",\"municipio\":{\"descricao\":\"Fortaleza\",\"id\":59},\"observacao\":\"fjfj\",\"tipoIluminacao\":\"ILUMINACAO_NATURAL_BOA\",\"tipoLocalCrime\":\"RESIDENCIAL\",\"tipoVia\":\"RUA\",\"veiculoEnvolvido\":false,\"id\":1},{\"complemento\":\"bfbge\",\"condicoesClimaticas\":\"TEMPO_ABERTO\",\"dataInclusao\":\"2018-08-22\",\"descricaoEndereco\":\"Popilhtgd\",\"latitude\":\"3º00'00S\",\"localPraia\":\"FALESIAS\",\"longitude\":\"38º00'00''W\",\"observacao\":\"\",\"tipoIluminacao\":\"ILUMINACAO_NATURAL_BOA\",\"tipoLocalCrime\":\"PRAIA\",\"tipoVia\":\"RUA\",\"veiculoEnvolvido\":false,\"id\":2}]}";

        JSONObject json =  new JSONObject(s);

        JSONArray oobj =  new JSONArray(json.get("enderecos").toString());


        JSONObject objEndereco =  new JSONObject(oobj.get(0).toString());
        ((JSONObject)objEndereco.get("bairro")).get("idGalileu");
//        String s = "{\"enderecos\":[{\"comodo\":\"AREA_SERVICO\",\"complemento\":\"\",\"condicoesClimaticas\":\"TEMPO_ABERTO\",\"dataInclusao\":\"Aug 22, 2018 11:31:32\",\"descricaoEndereco\":\"Vsngndgngd\",\"latitude\":\"3º43'7\",\"localAberto\":\"ABERTO\",\"localResidencia\":\"EXTERNO\",\"longitude\":\"38º32'6\",\"observacao\":\"\",\"tipoIluminacao\":\"ILUMINACAO_NATURAL_BOA\",\"tipoLocalCrime\":\"RESIDENCIAL\",\"tipoVia\":\"RUA\",\"veiculoEnvolvido\":false,\"id\":1},{\"complemento\":\"bfbge\",\"condicoesClimaticas\":\"TEMPO_ABERTO\",\"dataInclusao\":\"Aug 22, 2018 11:52:49\",\"descricaoEndereco\":\"Popilhtgd\",\"latitude\":\"3º00'00\\\"S\",\"longitude\":\"38º00'00\\\"W\",\"observacao\":\"\",\"pavimentacao\":\"ASFALTO\",\"posicaoVia\":\"PISTA\",\"tipoIluminacao\":\"ILUMINACAO_NATURAL_BOA\",\"tipoLocalCrime\":\"VIA_PUBLICA\",\"tipoVia\":\"RUA\",\"veiculoEnvolvido\":false,\"id\":2}],\"vestigios\":[{\"condicaoMunicao\":false,\"dataInclusao\":\"Aug 22, 2018 11:52:35\",\"observacao\":\"\",\"quantidadeMunicao\":0,\"tipoRecolhimentoAmostraBiologica\":\"SWAB\",\"tipoVestigio\":\"BIOLOGICO\",\"tiposVestigioBiologico\":\"SANGUE\",\"id\":1},{\"condicaoMunicao\":false,\"dataInclusao\":\"Aug 22, 2018 11:52:38\",\"observacao\":\"\",\"quantidadeMunicao\":0,\"tipoRecolhimentoAmostraBiologica\":\"SWAB\",\"tipoVestigio\":\"BIOLOGICO\",\"tiposVestigioBiologico\":\"SANGUE\",\"id\":2}],\"envolvidos\":[{\"Observacoes\":\"\",\"dataInclusao\":\"Aug 22, 2018 11:52:11\",\"documentoTipo\":\"NP\",\"documentoValor\":\"\",\"endereco\":{\"comodo\":\"AREA_SERVICO\",\"complemento\":\"\",\"condicoesClimaticas\":\"TEMPO_ABERTO\",\"dataInclusao\":\"Aug 22, 2018 11:31:32\",\"descricaoEndereco\":\"Vsngndgngd\",\"latitude\":\"3º43'7\",\"localAberto\":\"ABERTO\",\"localResidencia\":\"EXTERNO\",\"longitude\":\"38º32'6\",\"observacao\":\"\",\"tipoIluminacao\":\"ILUMINACAO_NATURAL_BOA\",\"tipoLocalCrime\":\"RESIDENCIAL\",\"tipoVia\":\"RUA\",\"veiculoEnvolvido\":false,\"id\":1},\"genero\":\"MASCULINO\",\"indiciosTempoMorte\":\"FLACIDO_QUENTE_SEM_LIVORES\",\"nome\":\"\",\"posicaoBracoDireito\":\"ESTENDIDO\",\"posicaoBracoEsquerdo\":\"ESTENDIDO\",\"posicaoCabeca\":\"APOIADA_SOLO\",\"posicaoCorpo\":\"DECUBITO_DORSAL\",\"posicaoPernaDireita\":\"ESTENDIDO\",\"posicaoPernaEsquerda\":\"ESTENDIDO\",\"tipoMorte\":\"HOMICIDIO\",\"vestes\":\"\",\"id\":1,\"lesoes\":[{\"compatibilidade\":true,\"dataInclusao\":\"Aug 22, 2018 18:49:41\",\"localizacaoLesao\":\"FRONTAL\",\"natureza\":\"BALISTICA\",\"parteCorpo\":\"TORAX\",\"secaoLesao\":\"PEITORAL_ESQUERDO\",\"id\":1}]},{\"Observacoes\":\"\",\"dataInclusao\":\"Aug 22, 2018 11:52:40\",\"documentoTipo\":\"NP\",\"documentoValor\":\"\",\"endereco\":{\"comodo\":\"AREA_SERVICO\",\"complemento\":\"\",\"condicoesClimaticas\":\"TEMPO_ABERTO\",\"dataInclusao\":\"Aug 22, 2018 11:31:32\",\"descricaoEndereco\":\"Vsngndgngd\",\"latitude\":\"3º43'7\",\"localAberto\":\"ABERTO\",\"localResidencia\":\"EXTERNO\",\"longitude\":\"38º32'6\",\"observacao\":\"\",\"tipoIluminacao\":\"ILUMINACAO_NATURAL_BOA\",\"tipoLocalCrime\":\"RESIDENCIAL\",\"tipoVia\":\"RUA\",\"veiculoEnvolvido\":false,\"id\":1},\"genero\":\"NAO_IDENTIFICADO\",\"indiciosTempoMorte\":\"FLACIDO_QUENTE_SEM_LIVORES\",\"nome\":\"Desconhecido(a)\",\"posicaoBracoDireito\":\"ESTENDIDO\",\"posicaoBracoEsquerdo\":\"ESTENDIDO\",\"posicaoCabeca\":\"APOIADA_SOLO\",\"posicaoCorpo\":\"DECUBITO_DORSAL\",\"posicaoPernaDireita\":\"ESTENDIDO\",\"posicaoPernaEsquerda\":\"ESTENDIDO\",\"tipoMorte\":\"HOMICIDIO\",\"vestes\":\"\",\"id\":2,\"lesoes\":[]}],\"fotos\":[{\"arquivo\":\"/storage/emulated/0/Galilei/8_Convidado(a)/Vida/2018/08/22/I20180005558/Fotos_Envolvidos/1_.jpeg\",\"categoriaFoto\":\"DESENHO\",\"dataInclusao\":\"Aug 22, 2018 18:50:37\",\"descricao\":\"Mapa das lesões\",\"id\":1},{\"arquivo\":\"/storage/emulated/0/Galilei/8_Convidado(a)/Vida/2018/08/22/I20180005558/Fotos_Local_Ocorrencia/foto_local_ocorrencia2018_08_23 09-27-45.jpeg\",\"dataInclusao\":\"Aug 23, 2018 09:27:45\",\"descricao\":\"\",\"id\":2},{\"arquivo\":\"/storage/emulated/0/Galilei/8_Convidado(a)/Vida/2018/08/22/I20180005558foto2018_08_23 10-34-55.jpeg\",\"categoriaFoto\":\"DESENHO\",\"dataInclusao\":\"Aug 23, 2018 10:34:55\",\"descricao\":\"\",\"id\":3}],\"ocorrencia\":{\"dataChamado\":\"Aug 14, 2018 13:06:26\",\"dataInclusao\":\"Aug 14, 2018 13:06:26\",\"ocorrenciaTransito\":{\"Comandante\":\"\",\"Viatura\":\"\",\"ais\":\"AIS_4\",\"dataAtendimento\":\"Aug 14, 2018 13:06:26\",\"dataChamado\":\"Aug 14, 2018 13:06:26\",\"documentoOcorrencia\":{\"tipoDocumento\":\"NL\",\"valor\":\"\",\"id\":1},\"numIncidencia\":\"I20180123545\",\"ocorrenciaID\":1,\"orgaoDestino\":\"1º Distrito Policial\",\"orgaoOrigem\":\"CIOPS - Coordenadoria Integrada de Operações de Segurança\",\"orgaoPresente\":\"AM\",\"preservacaoLocal\":\"MARCACOES_AMC\",\"ultimaForma\":false,\"id\":1},\"perito\":{\"email\":\"123\",\"idGalileu\":0,\"nome\":\"Convidado(a)\",\"password\":\"123\",\"id\":8},\"tipoOcorrencia\":\"TRANSITO\",\"id\":1}}";
//
//        JSONObject json = null;
//        try
//        {
//            json = new JSONObject(s);
////            Object o = json.get("envolvidos");
//
//            JSONArray oobj = new JSONArray(json.get("envolvidos"));
//            oobj.get(0).toString();
//
//        } catch (JSONException e)
//        {
//            e.printStackTrace();
//        }
    }
}
