package Util;

import java.util.ArrayList;
import java.util.List;

import Control.BairroBusiness;
import Control.DelegaciaBusiness;
import Enums.AreaIntegradaSeguranca;
import Model.Bairro;
import Model.Municipio;
import Model.DadosTerritoriais;
import Model.Delegacia;
import Model.Usuario;

/**
 * Created by Pefoce on 21/06/2017.
 */

public class Initializer
{
    public static void InicializarDelegacias()
    {

        ArrayList<Delegacia> delegas = new ArrayList<Delegacia>();

        delegas.add(new Delegacia("1º Distrito Policial"));
        delegas.add(new Delegacia("2º Distrito Policial"));
        delegas.add(new Delegacia("3º Distrito Policial"));
        delegas.add(new Delegacia("4º Distrito Policial"));
        delegas.add(new Delegacia("5º Distrito Policial"));
        delegas.add(new Delegacia("6º Distrito Policial"));
        delegas.add(new Delegacia("7º Distrito Policial"));
        delegas.add(new Delegacia("8º Distrito Policial"));
        delegas.add(new Delegacia("9º Distrito Policial"));
        delegas.add(new Delegacia("10º Distrito Policial"));
        delegas.add(new Delegacia("11º Distrito Policial"));
        delegas.add(new Delegacia("12º Distrito Policial"));
        delegas.add(new Delegacia("13º Distrito Policial"));
        delegas.add(new Delegacia("14º Distrito Policial"));
        delegas.add(new Delegacia("15º Distrito Policial"));
        delegas.add(new Delegacia("16º Distrito Policial"));
        delegas.add(new Delegacia("17º Distrito Policial"));
        delegas.add(new Delegacia("18º Distrito Policial"));
        delegas.add(new Delegacia("19º Distrito Policial"));
        delegas.add(new Delegacia("20º Distrito Policial"));
        delegas.add(new Delegacia("21º Distrito Policial"));
        delegas.add(new Delegacia("22º Distrito Policial"));
        delegas.add(new Delegacia("23º Distrito Policial"));
        delegas.add(new Delegacia("24º Distrito Policial"));
        delegas.add(new Delegacia("25º Distrito Policial"));
        delegas.add(new Delegacia("26º Distrito Policial"));
        delegas.add(new Delegacia("27º Distrito Policial"));
        delegas.add(new Delegacia("28º Distrito Policial"));
        delegas.add(new Delegacia("29º Distrito Policial"));
        delegas.add(new Delegacia("30º Distrito Policial"));
        delegas.add(new Delegacia("31º Distrito Policial"));
        delegas.add(new Delegacia("32º Distrito Policial"));
        delegas.add(new Delegacia("33º Distrito Policial"));
        delegas.add(new Delegacia("34º Distrito Policial"));
        delegas.add(new Delegacia("35º Distrito Policial"));
        delegas.add(new Delegacia("CIOPS - Coordenadoria Integrada de Operações de Segurança"));
        delegas.add(new Delegacia("Delegacia Metropolitana de Caucaia"));
        delegas.add(new Delegacia("Delegacia Metropolitana de Pacatuba"));
        delegas.add(new Delegacia("Delegacia Metropolitana de Guaiuba"));
        delegas.add(new Delegacia("Delegacia Metropolitana de Maranguape"));
        delegas.add(new Delegacia("Delegacia Metropolitana de Eusebio"));
        delegas.add(new Delegacia("Delegacia Metropolitana de Itaitinga"));
        delegas.add(new Delegacia("Delegacia Municipal de Abaiara"));
        delegas.add(new Delegacia("Delegacia Municipal de Acarape"));
        delegas.add(new Delegacia("Delegacia Regional de Acarau"));
        delegas.add(new Delegacia("Delegacia Municipal de Acopiara"));
        delegas.add(new Delegacia("Delegacia Municipal de Aiuaba"));
        delegas.add(new Delegacia("Delegacia Municipal de Alcantara"));
        delegas.add(new Delegacia("Delegacia Municipal de Altaneira"));
        delegas.add(new Delegacia("Delegacia Municipal de Alto Santo"));
        delegas.add(new Delegacia("Delegacia Municipal de Amontada"));
        delegas.add(new Delegacia("Delegacia Municipal de Antonina do Norte"));
        delegas.add(new Delegacia("Delegacia Municipal de Apuiares"));
        delegas.add(new Delegacia("Delegacia Regional de Aracati"));
        delegas.add(new Delegacia("Delegacia Municipal de Aracoiaba"));
        delegas.add(new Delegacia("Delegacia Municipal de Araripe"));
        delegas.add(new Delegacia("Delegacia Municipal de Aratuba"));
        delegas.add(new Delegacia("Delegacia Municipal de Assare"));
        delegas.add(new Delegacia("Delegacia Municipal de Aurora"));
        delegas.add(new Delegacia("Delegacia Municipal de Barbalha"));
        delegas.add(new Delegacia("Delegacia Municipal de Barreira"));
        delegas.add(new Delegacia("Delegacia Regional de Baturite"));
        delegas.add(new Delegacia("Delegacia Municipal de Beberibe"));
        delegas.add(new Delegacia("Delegacia Municipal de Boa Viagem"));
        delegas.add(new Delegacia("Delegacia Regional de Brejo Santo"));
        delegas.add(new Delegacia("Delegacia Regional de Camocim"));
        delegas.add(new Delegacia("Delegacia Regional de Caninde"));
        delegas.add(new Delegacia("Delegacia Municipal de Capistrano"));
        delegas.add(new Delegacia("Delegacia Municipal de Caridade"));
        delegas.add(new Delegacia("Delegacia Municipal de Caririacu"));
        delegas.add(new Delegacia("Delegacia Municipal de Carius"));
        delegas.add(new Delegacia("Delegacia Municipal de Cascavel"));
        delegas.add(new Delegacia("Delegacia Municipal de Catarina"));
        delegas.add(new Delegacia("Delegacia Municipal de Cedro"));
        delegas.add(new Delegacia("Delegacia Municipal de Chaval"));
        delegas.add(new Delegacia("Delegacia Municipal de Chorozinho"));
        delegas.add(new Delegacia("Delegacia Municipal de Coreau"));
        delegas.add(new Delegacia("Delegacia Regional de Crateus"));
        delegas.add(new Delegacia("Delegacia Regional de Crato"));
        delegas.add(new Delegacia("Delegacia Municipal de Barroquinha"));
        delegas.add(new Delegacia("Delegacia Municipal de Cruz"));
        delegas.add(new Delegacia("Delegacia Municipal de Erere"));
        delegas.add(new Delegacia("Delegacia Municipal de Farias Brito"));
        delegas.add(new Delegacia("Delegacia Municipal de Frecheirinha"));
        delegas.add(new Delegacia("Delegacia Municipal de General Sampaio"));
        delegas.add(new Delegacia("Delegacia Municipal de Graca"));
        delegas.add(new Delegacia("Delegacia Municipal de Granja"));
        delegas.add(new Delegacia("Delegacia Municipal de Granjeiro"));
        delegas.add(new Delegacia("Delegacia Municipal de Groairas"));
        delegas.add(new Delegacia("Delegacia Municipal de Guaraciaba do Norte"));
        delegas.add(new Delegacia("Delegacia Municipal de Guaramiranga"));
        delegas.add(new Delegacia("Delegacia Municipal de Hidrolandia"));
        delegas.add(new Delegacia("Delegacia Municipal de Iraucuba"));
        delegas.add(new Delegacia("Delegacia Municipal de Itaicaba"));
        delegas.add(new Delegacia("Delegacia Municipal de Itapaje"));
        delegas.add(new Delegacia("Delegacia Municipal de Itapiuna"));
        delegas.add(new Delegacia("Delegacia Municipal de Itarema"));
        delegas.add(new Delegacia("Delegacia Municipal de Jaguaretama"));
        delegas.add(new Delegacia("Delegacia Municipal de Jaguaribara"));
        delegas.add(new Delegacia("Delegacia Regional de Jaguaribe"));
        delegas.add(new Delegacia("Delegacia Municipal de Jardim"));
        delegas.add(new Delegacia("Delegacia Municipal de Ibiapina"));
        delegas.add(new Delegacia("Delegacia Regional de Ico"));
        delegas.add(new Delegacia("Delegacia Regional de Iguatu"));
        delegas.add(new Delegacia("Delegacia Municipal de Independencia"));
        delegas.add(new Delegacia("Delegacia Municipal de Choro Limao"));
        delegas.add(new Delegacia("Delegacia Municipal de Ipaumirim"));
        delegas.add(new Delegacia("Delegacia Municipal de Ipu"));
        delegas.add(new Delegacia("Delegacia Municipal de Ipueiras"));
        delegas.add(new Delegacia("Delegacia Municipal de Iracema"));
        delegas.add(new Delegacia("Delegacia Municipal de Jati"));
        delegas.add(new Delegacia("Delegacia Regional de Juazeiro do Norte"));
        delegas.add(new Delegacia("Delegacia Municipal de Jucas"));
        delegas.add(new Delegacia("Delegacia Municipal de Lavras da Mangabeira"));
        delegas.add(new Delegacia("Delegacia Municipal de Limoeiro do Norte"));
        delegas.add(new Delegacia("Delegacia Municipal de Marco"));
        delegas.add(new Delegacia("Delegacia Municipal de Madalena"));
        delegas.add(new Delegacia("Delegacia Municipal de Martinopole"));
        delegas.add(new Delegacia("Delegacia Municipal de Massape"));
        delegas.add(new Delegacia("Delegacia Municipal de Mauriti"));
        delegas.add(new Delegacia("Delegacia Municipal de Meruoca"));
        delegas.add(new Delegacia("Delegacia Municipal de Milha"));
        delegas.add(new Delegacia("Delegacia Municipal de Croata"));
        delegas.add(new Delegacia("Delegacia Municipal de Mombaca"));
        delegas.add(new Delegacia("Delegacia Municipal de Monsenhor Tabosa"));
        delegas.add(new Delegacia("Delegacia Municipal de Morada Nova"));
        delegas.add(new Delegacia("Delegacia Municipal de Morrinhos"));
        delegas.add(new Delegacia("Delegacia Municipal de Mucambo"));
        delegas.add(new Delegacia("Delegacia Municipal de Mulungu"));
        delegas.add(new Delegacia("Delegacia Municipal de Nova Russas"));
        delegas.add(new Delegacia("Delegacia Municipal de Novo Oriente"));
        delegas.add(new Delegacia("Delegacia Municipal de Oros"));
        delegas.add(new Delegacia("Delegacia Municipal de Pacajus"));
        delegas.add(new Delegacia("Delegacia Municipal de Pacoti"));
        delegas.add(new Delegacia("Delegacia Municipal de Palhano"));
        delegas.add(new Delegacia("Delegacia Municipal de Palmacia"));
        delegas.add(new Delegacia("Delegacia Municipal de Paracuru"));
        delegas.add(new Delegacia("Delegacia Municipal de Paraipaba"));
        delegas.add(new Delegacia("Delegacia Municipal de Parambu"));
        delegas.add(new Delegacia("Delegacia Municipal de Paramoti"));
        delegas.add(new Delegacia("Delegacia Municipal de Pedra Branca"));
        delegas.add(new Delegacia("Delegacia Municipal de Penaforte"));
        delegas.add(new Delegacia("Delegacia Municipal de Pentecoste"));
        delegas.add(new Delegacia("Delegacia Municipal de Pereiro"));
        delegas.add(new Delegacia("Delegacia Municipal de Ibaretama"));
        delegas.add(new Delegacia("Delegacia Municipal de Piquet Carneiro"));
        delegas.add(new Delegacia("Delegacia Municipal de Pires Ferreira"));
        delegas.add(new Delegacia("Delegacia Municipal de Poranga"));
        delegas.add(new Delegacia("Delegacia Municipal de Porteiras"));
        delegas.add(new Delegacia("Delegacia Municipal de Potengi"));
        delegas.add(new Delegacia("Delegacia Municipal de Quiterianopolis"));
        delegas.add(new Delegacia("Delegacia Municipal de Redencao"));
        delegas.add(new Delegacia("Delegacia Regional de Russas"));
        delegas.add(new Delegacia("Delegacia Municipal de Saboeiro"));
        delegas.add(new Delegacia("Delegacia Municipal de Ibicuitinga"));
        delegas.add(new Delegacia("Delegacia Municipal de Santana do Cariri"));
        delegas.add(new Delegacia("Delegacia Municipal de Santa Quiteria"));
        delegas.add(new Delegacia("Delegacia Municipal de Sao Benedito"));
        delegas.add(new Delegacia("Delegacia Municipal de Sao Joao do Jaguaribe"));
        delegas.add(new Delegacia("Delegacia Municipal de Sao Luis do Curu"));
        delegas.add(new Delegacia("Delegacia Regional de Senador Pompeu"));
        delegas.add(new Delegacia("Delegacia Regional de Sobral"));
        delegas.add(new Delegacia("Delegacia Municipal de Solonopole"));
        delegas.add(new Delegacia("Delegacia Municipal de Tabuleiro do Norte"));
        delegas.add(new Delegacia("Delegacia Municipal de Tamboril"));
        delegas.add(new Delegacia("Delegacia Municipal de Ipaporanga"));
        delegas.add(new Delegacia("Delegacia Regional de Taua"));
        delegas.add(new Delegacia("Delegacia Municipal de Irapuan Pinheiro"));
        delegas.add(new Delegacia("Delegacia Regional de Tiangua"));
        delegas.add(new Delegacia("Delegacia Municipal de Trairi"));
        delegas.add(new Delegacia("Delegacia Municipal de Ubajara"));
        delegas.add(new Delegacia("Delegacia Municipal de Umari"));
        delegas.add(new Delegacia("Delegacia Municipal de Uruburetama"));
        delegas.add(new Delegacia("Delegacia Municipal de Uruoca"));
        delegas.add(new Delegacia("Delegacia Municipal de Varzea Alegre"));
        delegas.add(new Delegacia("Delegacia Municipal de Vicosa do Ceara"));
        delegas.add(new Delegacia("Delegacia Municipal de Miraima"));
        delegas.add(new Delegacia("Delegacia Municipal de Pindoretama"));
        delegas.add(new Delegacia("Delegacia Municipal de Salitre"));
        delegas.add(new Delegacia("Delegacia Municipal de Tarrafas"));
        delegas.add(new Delegacia("Delegacia Municipal de Tejucuoca"));
        delegas.add(new Delegacia("Delegacia Municipal de Ararenda"));
        delegas.add(new Delegacia("Delegacia Municipal de Jijoca"));
        delegas.add(new Delegacia("Delegacia Municipal de Senador Catunda"));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Fortaleza"));
        delegas.add(new Delegacia("Delegacia de Roubos e Furtos de Veiculos e Cargas - Drfvc"));
        delegas.add(new Delegacia("Delegacia de Roubos e Furtos"));
        delegas.add(new Delegacia("Delegacia de Defraudacoes e Falsificacoes"));
        delegas.add(new Delegacia("Delegacia de Capturas e Polinter"));
        delegas.add(new Delegacia("Delegacia de Acidentes e Delitos de Transito"));
        delegas.add(new Delegacia("Delegacia de Policia Maritima Aerea e de Fronteiras"));
        delegas.add(new Delegacia("Delegacia Regional do Trabalho"));
        delegas.add(new Delegacia("Delegacia Municipal de Parnaiba/pi"));
        delegas.add(new Delegacia("Delegacia Municipal de Itapipoca"));
        delegas.add(new Delegacia("Delegacia de Caio Prado"));
        delegas.add(new Delegacia("Delegacia Municipal de Mossoro"));
        delegas.add(new Delegacia("Delegacia de Crim. Contra Administracao e Financas Publicas"));
        delegas.add(new Delegacia("Delegacia Municipal de Quiterianopolis"));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Iguatu"));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Juazeiro"));
        delegas.add(new Delegacia("Delegacia de Policia Federal Em Juazeiro do Norte"));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Crato"));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Sobral"));
        delegas.add(new Delegacia("Delegacia Municipal de Catunda"));
        delegas.add(new Delegacia("Delegacia de Policia Civil de Rafael Godeiro-rn"));
        delegas.add(new Delegacia("Delegacia da Crianca e do Adolescente"));
        delegas.add(new Delegacia("Delegacia Municipal de Rafael Cordeiro/rn"));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Maracanau"));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Caucaia"));
        delegas.add(new Delegacia("10a Delegacia Regional de Policia Civil"));
        delegas.add(new Delegacia("Delegacia de Combate Aos Crimes Contra a Ordem Tributaria"));
        delegas.add(new Delegacia("Delegacia de Crimes Contra Adminastracao e Financas Publicas"));
        delegas.add(new Delegacia("Delegacia de Protecao Ao Turista"));
        delegas.add(new Delegacia("Delegacia Central de Flagrantes"));
        delegas.add(new Delegacia("Delegacia de Repressao a Entorpecentes"));
        delegas.add(new Delegacia("Delegacia Metropolitana de Aquiraz"));
        delegas.add(new Delegacia("Delegacia Municipal de Arneiroz"));
        delegas.add(new Delegacia("Delegacia Municipal de Banabuiu"));
        delegas.add(new Delegacia("Delegacia Municipal de Barro"));
        delegas.add(new Delegacia("Delegacia Municipal de Bela Cruz"));
        delegas.add(new Delegacia("Delegacia Municipal de Campos Sales"));
        delegas.add(new Delegacia("Delegacia Municipal de Carire"));
        delegas.add(new Delegacia("Delegacia Municipal de Carnaubal"));
        delegas.add(new Delegacia("Delegacia Municipal de Forquilha"));
        delegas.add(new Delegacia("Delegacia Municipal de Horizonte"));
        delegas.add(new Delegacia("Delegacia Regional de Itapipoca"));
        delegas.add(new Delegacia("Delegacia Municipal de Itatira"));
        delegas.add(new Delegacia("Delegacia Municipal de Jaguaruana"));
        delegas.add(new Delegacia("Delegacia Municipal de Icapui"));
        delegas.add(new Delegacia("Delegacia Municipal de Milagres"));
        delegas.add(new Delegacia("Delegacia Municipal de Missao Velha"));
        delegas.add(new Delegacia("Delegacia Municipal de Moraujo"));
        delegas.add(new Delegacia("Delegacia Municipal de Nova Olinda"));
        delegas.add(new Delegacia("Delegacia Municipal de Fortim"));
        delegas.add(new Delegacia("Delegacia Municipal de Pacuja"));
        delegas.add(new Delegacia("Delegacia Municipal de Potiretama"));
        delegas.add(new Delegacia("Delegacia Municipal de Reriutaba"));
        delegas.add(new Delegacia("Delegacia Municipal de Santana do Acarau"));
        delegas.add(new Delegacia("Delegacia Municipal de Sao G. do Amarante"));
        delegas.add(new Delegacia("Delegacia Municipal de Senador Sa"));
        delegas.add(new Delegacia("Delegacia Municipal de Tururu"));
        delegas.add(new Delegacia("Delegacia Municipal de Umirim"));
        delegas.add(new Delegacia("Delegacia Municipal de Varjota"));
        delegas.add(new Delegacia("Delegacia Municipal de Ocara"));
        delegas.add(new Delegacia("Assessor Tec. da Delegacia Geral da Pol. Civil"));
        delegas.add(new Delegacia("Delegacia de Assuntos Internos"));
        delegas.add(new Delegacia("Gab. Delegado Geral da Policia Civil"));
        delegas.add(new Delegacia("Gabinete do Delegado Superintendente"));
        delegas.add(new Delegacia("Delegacia Municipal de Sobral"));
        delegas.add(new Delegacia("Delegacia Geral"));
        delegas.add(new Delegacia("Delegacia Municipal de Meruoca"));
        delegas.add(new Delegacia("Delegacia Municipal de Sao Goncalo do Amarante"));
        delegas.add(new Delegacia("Delegacia Municipal de Sobral"));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Pacatuba"));
        delegas.add(new Delegacia("Delegacia do Estado do Rio Grande do Norte"));
        delegas.add(new Delegacia("Divisao de Homicidios - 3a Delegacia"));
        delegas.add(new Delegacia("Divisao de Homicidios - 2a Delegacia"));
        delegas.add(new Delegacia("Delegacia Especial de Atendimento a Mulher do Distrito Federal"));
        delegas.add(new Delegacia("Divisão de Homicidios - 1ª Delegacia"));
        delegas.add(new Delegacia("Delegacia de Pedras de Fogo/pb"));
        delegas.add(new Delegacia("Divisao de Homicidios - 4a Delegacia"));
        delegas.add(new Delegacia("Delegacia de Repressao as Acoes Criminosas Organizadas - Draco"));
        delegas.add(new Delegacia("06a Delegacia Regional de Policia de Nova Cruz - Rn"));
        delegas.add(new Delegacia("Delegacia de Policia Federal Em Mossoro"));
        delegas.add(new Delegacia("Delegacia de Narcoticos"));

        for (Delegacia de : delegas)
            de.save();

    }

    public static void InicializarMunicipios()
    {


        ArrayList<Municipio> municipios = new ArrayList<>();
        municipios.add(new Municipio("Abaiara"));
        municipios.add(new Municipio("Acarape"));
        municipios.add(new Municipio("Acaraú"));
        municipios.add(new Municipio("Acopiara"));
        municipios.add(new Municipio("Aiuaba"));
        municipios.add(new Municipio("Alcântaras"));
        municipios.add(new Municipio("Altaneira"));
        municipios.add(new Municipio("Alto Santo"));
        municipios.add(new Municipio("Amontada"));
        municipios.add(new Municipio("Antonina do Norte"));
        municipios.add(new Municipio("Apuiarés"));
        municipios.add(new Municipio("Aquiraz"));
        municipios.add(new Municipio("Aracati"));
        municipios.add(new Municipio("Aracoiaba"));
        municipios.add(new Municipio("Ararendá"));
        municipios.add(new Municipio("Araripe"));
        municipios.add(new Municipio("Aratuba"));
        municipios.add(new Municipio("Arneiroz"));
        municipios.add(new Municipio("Assaré"));
        municipios.add(new Municipio("Aurora"));
        municipios.add(new Municipio("Baixio"));
        municipios.add(new Municipio("Banabuiú"));
        municipios.add(new Municipio("Barbalha"));
        municipios.add(new Municipio("Barreira"));
        municipios.add(new Municipio("Barro"));
        municipios.add(new Municipio("Barroquinha"));
        municipios.add(new Municipio("Baturité"));
        municipios.add(new Municipio("Beberibe"));
        municipios.add(new Municipio("Bela Cruz"));
        municipios.add(new Municipio("Boa Viagem"));
        municipios.add(new Municipio("Brejo Santo"));
        municipios.add(new Municipio("Camocim"));
        municipios.add(new Municipio("Campos Sales"));
        municipios.add(new Municipio("Canindé"));
        municipios.add(new Municipio("Capistrano"));
        municipios.add(new Municipio("Caridade"));
        municipios.add(new Municipio("Cariré"));
        municipios.add(new Municipio("Caririaçu"));
        municipios.add(new Municipio("Cariús"));
        municipios.add(new Municipio("Carnaubal"));
        municipios.add(new Municipio("Cascavel"));
        municipios.add(new Municipio("Catarina"));
        municipios.add(new Municipio("Catunda"));
        municipios.add(new Municipio("Caucaia"));
        municipios.add(new Municipio("Cedro"));
        municipios.add(new Municipio("Chaval"));
        municipios.add(new Municipio("Choró"));
        municipios.add(new Municipio("Chorozinho"));
        municipios.add(new Municipio("Coreaú"));
        municipios.add(new Municipio("Crateús"));
        municipios.add(new Municipio("Crato"));
        municipios.add(new Municipio("Croatá"));
        municipios.add(new Municipio("Cruz"));
        municipios.add(new Municipio("Deputado Irapuan Pinheiro"));
        municipios.add(new Municipio("Ererê"));
        municipios.add(new Municipio("Eusébio"));
        municipios.add(new Municipio("Farias Brito"));
        municipios.add(new Municipio("Forquilha"));
        municipios.add(new Municipio("Fortaleza"));
        municipios.add(new Municipio("Fortim"));
        municipios.add(new Municipio("Frecheirinha"));
        municipios.add(new Municipio("General Sampaio"));
        municipios.add(new Municipio("Graça"));
        municipios.add(new Municipio("Granja"));
        municipios.add(new Municipio("Granjeiro"));
        municipios.add(new Municipio("Groaíras"));
        municipios.add(new Municipio("Guaiúba"));
        municipios.add(new Municipio("Guaraciaba do Norte"));
        municipios.add(new Municipio("Guaramiranga"));
        municipios.add(new Municipio("Hidrolândia"));
        municipios.add(new Municipio("Horizonte"));
        municipios.add(new Municipio("Ibaretama"));
        municipios.add(new Municipio("Ibiapina"));
        municipios.add(new Municipio("Ibicuitinga"));
        municipios.add(new Municipio("Icapuí"));
        municipios.add(new Municipio("Icó"));
        municipios.add(new Municipio("Iguatu"));
        municipios.add(new Municipio("Independência"));
        municipios.add(new Municipio("Ipaporanga"));
        municipios.add(new Municipio("Ipaumirim"));
        municipios.add(new Municipio("Ipu"));
        municipios.add(new Municipio("Ipueiras"));
        municipios.add(new Municipio("Iracema"));
        municipios.add(new Municipio("Irauçuba"));
        municipios.add(new Municipio("Itaiçaba"));
        municipios.add(new Municipio("Itaitinga"));
        municipios.add(new Municipio("Itapajé"));
        municipios.add(new Municipio("Itapipoca"));
        municipios.add(new Municipio("Itapiúna"));
        municipios.add(new Municipio("Itarema"));
        municipios.add(new Municipio("Itatira"));
        municipios.add(new Municipio("Jaguaretama"));
        municipios.add(new Municipio("Jaguaribara"));
        municipios.add(new Municipio("Jaguaribe"));
        municipios.add(new Municipio("Jaguaruana"));
        municipios.add(new Municipio("Jardim"));
        municipios.add(new Municipio("Jati"));
        municipios.add(new Municipio("Jijoca de Jericoacoara"));
        municipios.add(new Municipio("Juazeiro do Norte"));
        municipios.add(new Municipio("Jucás"));
        municipios.add(new Municipio("Lavras da Mangabeira"));
        municipios.add(new Municipio("Limoeiro do Norte"));
        municipios.add(new Municipio("Madalena"));
        municipios.add(new Municipio("Maracanaú"));
        municipios.add(new Municipio("Maranguape"));
        municipios.add(new Municipio("Marco"));
        municipios.add(new Municipio("Martinópole"));
        municipios.add(new Municipio("Massapê"));
        municipios.add(new Municipio("Mauriti"));
        municipios.add(new Municipio("Meruoca"));
        municipios.add(new Municipio("Milagres"));
        municipios.add(new Municipio("Milhã"));
        municipios.add(new Municipio("Miraíma"));
        municipios.add(new Municipio("Missão Velha"));
        municipios.add(new Municipio("Mombaça"));
        municipios.add(new Municipio("Monsenhor Tabosa"));
        municipios.add(new Municipio("Morada Nova"));
        municipios.add(new Municipio("Moraújo"));
        municipios.add(new Municipio("Morrinhos"));
        municipios.add(new Municipio("Mucambo"));
        municipios.add(new Municipio("Mulungu"));
        municipios.add(new Municipio("Nova Olinda"));
        municipios.add(new Municipio("Nova Russas"));
        municipios.add(new Municipio("Novo Oriente"));
        municipios.add(new Municipio("Ocara"));
        municipios.add(new Municipio("Orós"));
        municipios.add(new Municipio("Pacajus"));
        municipios.add(new Municipio("Pacatuba"));
        municipios.add(new Municipio("Pacoti"));
        municipios.add(new Municipio("Pacujá"));
        municipios.add(new Municipio("Palhano"));
        municipios.add(new Municipio("Palmácia"));
        municipios.add(new Municipio("Paracuru"));
        municipios.add(new Municipio("Paraipaba"));
        municipios.add(new Municipio("Parambu"));
        municipios.add(new Municipio("Paramoti"));
        municipios.add(new Municipio("Pedra Branca"));
        municipios.add(new Municipio("Penaforte"));
        municipios.add(new Municipio("Pentecoste"));
        municipios.add(new Municipio("Pereiro"));
        municipios.add(new Municipio("Pindoretama"));
        municipios.add(new Municipio("Piquet Carneiro"));
        municipios.add(new Municipio("Pires Ferreira"));
        municipios.add(new Municipio("Poranga"));
        municipios.add(new Municipio("Porteiras"));
        municipios.add(new Municipio("Potengi"));
        municipios.add(new Municipio("Potiretama"));
        municipios.add(new Municipio("Quiterianópolis"));
        municipios.add(new Municipio("Quixadá"));
        municipios.add(new Municipio("Quixelô"));
        municipios.add(new Municipio("Quixeramobim"));
        municipios.add(new Municipio("Quixeré"));
        municipios.add(new Municipio("Redenção"));
        municipios.add(new Municipio("Reriutaba"));
        municipios.add(new Municipio("Russas"));
        municipios.add(new Municipio("Saboeiro"));
        municipios.add(new Municipio("Salitre"));
        municipios.add(new Municipio("Santa Quitéria"));
        municipios.add(new Municipio("Santana do Acaraú"));
        municipios.add(new Municipio("Santana do Cariri"));
        municipios.add(new Municipio("São Benedito"));
        municipios.add(new Municipio("São Gonçalo do Amarante"));
        municipios.add(new Municipio("São João do Jaguaribe"));
        municipios.add(new Municipio("São Luís do Curu"));
        municipios.add(new Municipio("Senador Pompeu"));
        municipios.add(new Municipio("Senador Sá"));
        municipios.add(new Municipio("Sobral"));
        municipios.add(new Municipio("Solonópole"));
        municipios.add(new Municipio("Tabuleiro do Norte"));
        municipios.add(new Municipio("Tamboril"));
        municipios.add(new Municipio("Tarrafas"));
        municipios.add(new Municipio("Tauá"));
        municipios.add(new Municipio("Tejuçuoca"));
        municipios.add(new Municipio("Tianguá"));
        municipios.add(new Municipio("Trairi"));
        municipios.add(new Municipio("Tururu"));
        municipios.add(new Municipio("Ubajara"));
        municipios.add(new Municipio("Umari"));
        municipios.add(new Municipio("Umirim"));
        municipios.add(new Municipio("Uruburetama"));
        municipios.add(new Municipio("Uruoca"));
        municipios.add(new Municipio("Varjota"));
        municipios.add(new Municipio("Várzea Alegre"));
        municipios.add(new Municipio("Viçosa do Ceará"));


        for (Municipio c : municipios)
            c.save();
    }

    public static void InicializarDadosTerritoriais()
    {

        ArrayList<DadosTerritoriais> dadosTerritoriais = new ArrayList<>();

        //AIS 1
        // Cais do Porto, Vicente Pinzón, Mucuripe, Aldeota, Varjota, Praia de Iracema e Meireles.

        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("9º Distrito Policial"), BairroBusiness.findByDescricao("Vicente Pinzón"), AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("9º Distrito Policial"), BairroBusiness.findByDescricao("Cais do Porto"), AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("9º Distrito Policial"), BairroBusiness.findByDescricao("Mucuripe"), AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("26º Distrito Policial"), BairroBusiness.findByDescricao( "Praia de Iracema"), AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("2º Distrito Policial"), BairroBusiness.findByDescricao("Meireles"), AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("2º Distrito Policial"), BairroBusiness.findByDescricao("Aldeota"), AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("2º Distrito Policial"), BairroBusiness.findByDescricao("Varjota"), AreaIntegradaSeguranca.AIS_1));


        // AIS 2
        // Conjunto Ceará I e II, Genibaú, Granja Portugal, Bom Jardim, GranBairroBusiness.findByDescricao(ja Lisboa e Siqueira


        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("12º Distrito Policial"), BairroBusiness.findByDescricao( "Conjunto Ceará I"), AreaIntegradaSeguranca.AIS_2));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("12º Distrito Policial"), BairroBusiness.findByDescricao( "Conjunto Ceará II"), AreaIntegradaSeguranca.AIS_2));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("12º Distrito Policial"), BairroBusiness.findByDescricao( "Parque Genibaú"), AreaIntegradaSeguranca.AIS_2));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("32º Distrito Policial"), BairroBusiness.findByDescricao( "Bom Jardim"), AreaIntegradaSeguranca.AIS_2));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("32º Distrito Policial"), BairroBusiness.findByDescricao( "Granja Lisboa"), AreaIntegradaSeguranca.AIS_2));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("32º Distrito Policial"), BairroBusiness.findByDescricao( "Siqueira"), AreaIntegradaSeguranca.AIS_2));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("12º Distrito Policial"), BairroBusiness.findByDescricao( "Granja Portugal"), AreaIntegradaSeguranca.AIS_2));

        //AIS 3
        // Messejana,Ancuri, Pedras, Barroso, Jangurussu, Conjunto PalmeirasBairroBusiness.findByDescricao(,  Curió, Lagoa Redonda, Guajeru,  SãoBento, Palpina, Parque Santa Maria e Coaçu

        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("6º Distrito Policial"), BairroBusiness.findByDescricao("Messejana"), AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("6º Distrito Policial"), BairroBusiness.findByDescricao("Barroso"), AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("6º Distrito Policial"), BairroBusiness.findByDescricao("Pedras"), AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("6º Distrito Policial"), BairroBusiness.findByDescricao("Ancuri"), AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("6º Distrito Policial"), BairroBusiness.findByDescricao("Parque Santa Maria"), AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("30º Distrito Policial"), BairroBusiness.findByDescricao( "Jangurussu"), AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("30º Distrito Policial"), BairroBusiness.findByDescricao( "Conjunto Palmeiras"), AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("35º Distrito Policial"), BairroBusiness.findByDescricao( "Curió"), AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("35º Distrito Policial"), BairroBusiness.findByDescricao( "Lagoa Redonda"), AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("35º Distrito Policial"), BairroBusiness.findByDescricao( "Guajeru"), AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("35º Distrito Policial"), BairroBusiness.findByDescricao( "São Bento"), AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("35º Distrito Policial"), BairroBusiness.findByDescricao( "Paupina"), AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("35º Distrito Policial"), BairroBusiness.findByDescricao( "Coaçu"), AreaIntegradaSeguranca.AIS_3));

        //AIS 4
        //Centro,Moura  Brasil, Carlito Pamplona,  Álvaro Weyne, Vila ElleryBairroBusiness.findByDescricao(, Monte Castelo, Farias Brito, São Gerardo, Jacarecanga.


        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("34º Distrito Policial"), BairroBusiness.findByDescricao( "Centro"), AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("34º Distrito Policial"), BairroBusiness.findByDescricao( "Moura Brasil"), AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("1º Distrito Policial"), BairroBusiness.findByDescricao("Carlito Pamplona"), AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("1º Distrito Policial"), BairroBusiness.findByDescricao("Àlvaro Weyne"), AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("1º Distrito Policial"), BairroBusiness.findByDescricao("Vila Ellery"), AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("1º Distrito Policial"), BairroBusiness.findByDescricao("Monte Castelo"), AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("3º Distrito Policial"), BairroBusiness.findByDescricao("Farias Brito"), AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("3º Distrito Policial"), BairroBusiness.findByDescricao("São Gerardo"), AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("3º Distrito Policial"), BairroBusiness.findByDescricao("Jacarecanga"), AreaIntegradaSeguranca.AIS_4));

        //AIS 5
        //Parangaba, Vila Pery, Itaoca,Itaperi, Dendê, Pan Americano, JardimBairroBusiness.findByDescricao( América,
        // Benfica, Demócrito Rocha, Couto Fernandes,Montese,Damas,Bom FuturBairroBusiness.findByDescricao(o,Vila União, José Bonifácio, Parreão, Fátima, Serrinha, Aeroporto.


        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("5º Distrito Policial"), BairroBusiness.findByDescricao("Parangaba"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("5º Distrito Policial"), BairroBusiness.findByDescricao("Itaoca"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("5º Distrito Policial"), BairroBusiness.findByDescricao("Itaperi"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("5º Distrito Policial"), BairroBusiness.findByDescricao("Dendê"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("11º Distrito Policial"), BairroBusiness.findByDescricao( "Panamericano"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("11º Distrito Policial"), BairroBusiness.findByDescricao( "Benfica"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("11º Distrito Policial"), BairroBusiness.findByDescricao( "Demócrito Rocha"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("11º Distrito Policial"), BairroBusiness.findByDescricao( "Jardim América"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("11º Distrito Policial"), BairroBusiness.findByDescricao( "Couto Fernandes"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("11º Distrito Policial"), BairroBusiness.findByDescricao( "Montese"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("11º Distrito Policial"), BairroBusiness.findByDescricao( "Damas"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("11º Distrito Policial"), BairroBusiness.findByDescricao( "Bom Futuro"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("25º Distrito Policial"), BairroBusiness.findByDescricao( "Vila União"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("25º Distrito Policial"), BairroBusiness.findByDescricao( "José Bonifácio"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("25º Distrito Policial"), BairroBusiness.findByDescricao( "Parreão"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("25º Distrito Policial"), BairroBusiness.findByDescricao( "Serrinha"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("25º Distrito Policial"), BairroBusiness.findByDescricao( "Bairro de Fátima"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("25º Distrito Policial"), BairroBusiness.findByDescricao( "Aeroporto"), AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("5º Distrito Policial"), BairroBusiness.findByDescricao("Vila Pery"), AreaIntegradaSeguranca.AIS_5));


        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("10º Distrito Policial"), BairroBusiness.findByDescricao( "Antônio Bezerra"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("10º Distrito Policial"), BairroBusiness.findByDescricao( "Quintino Cunha"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("10º Distrito Policial"), BairroBusiness.findByDescricao( "Olavo Oliveira"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("10º Distrito Policial"), BairroBusiness.findByDescricao( "Padre Andrade"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("10º Distrito Policial"), BairroBusiness.findByDescricao( "Bela Vista"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("10º Distrito Policial"), BairroBusiness.findByDescricao( "Presidente Kennedy"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("10º Distrito Policial"), BairroBusiness.findByDescricao( "Parquelândia"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("10º Distrito Policial"), BairroBusiness.findByDescricao( "Amadeu Furtado"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("10º Distrito Policial"), BairroBusiness.findByDescricao( "Parque Araxá"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("10º Distrito Policial"), BairroBusiness.findByDescricao( "Rodolfo Teófilo"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("27º Distrito Policial"), BairroBusiness.findByDescricao( "Bom Sucesso"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("27º Distrito Policial"), BairroBusiness.findByDescricao( "João XXIII"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("27º Distrito Policial"), BairroBusiness.findByDescricao( "Jóquei Clube"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("27º Distrito Policial"), BairroBusiness.findByDescricao( "Henrique Jorge"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("27º Distrito Policial"), BairroBusiness.findByDescricao( "Autran Nunes"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("27º Distrito Policial"), BairroBusiness.findByDescricao( "Pici"), AreaIntegradaSeguranca.AIS_6));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("27º Distrito Policial"), BairroBusiness.findByDescricao( "Dom Lustosa"), AreaIntegradaSeguranca.AIS_6));


        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("26º Distrito Policial"), BairroBusiness.findByDescricao( "Edson Queiroz"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("26º Distrito Policial"), BairroBusiness.findByDescricao( "Sabiaguaba"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("26º Distrito Policial"), BairroBusiness.findByDescricao( "Cambeba"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("26º Distrito Policial"), BairroBusiness.findByDescricao( "José de Alencar"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("26º Distrito Policial"), BairroBusiness.findByDescricao( "Parque Iracema"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("26º Distrito Policial"), BairroBusiness.findByDescricao( "Parque Manibura"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("26º Distrito Policial"), BairroBusiness.findByDescricao( "Sapiranga"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("13º Distrito Policial"), BairroBusiness.findByDescricao( "Municipio dos Funcionários"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("13º Distrito Policial"), BairroBusiness.findByDescricao( "Alto da Balança"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("13º Distrito Policial"), BairroBusiness.findByDescricao( "Cajazeiras"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("16º Distrito Policial"), BairroBusiness.findByDescricao( "Parque Dois Irmãos"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("16º Distrito Policial"), BairroBusiness.findByDescricao( "Passaré"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("13º Distrito Policial"), BairroBusiness.findByDescricao( "Jardim das Oliveiras"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("13º Distrito Policial"), BairroBusiness.findByDescricao( "Aerolândia"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("16º Distrito Policial"), BairroBusiness.findByDescricao( "Boa Vista"), AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("16º Distrito Policial"), BairroBusiness.findByDescricao( "Dias Macêdo"), AreaIntegradaSeguranca.AIS_7));



        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("33º Distrito Policial"), BairroBusiness.findByDescricao( "Barra do Ceará"), AreaIntegradaSeguranca.AIS_8));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("17º Distrito Policial"), BairroBusiness.findByDescricao( "Vila Velha"), AreaIntegradaSeguranca.AIS_8));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("17º Distrito Policial"), BairroBusiness.findByDescricao( "Jardim Guanabara"), AreaIntegradaSeguranca.AIS_8));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("7º Distrito Policial"), BairroBusiness.findByDescricao("Cristo Redentor"), AreaIntegradaSeguranca.AIS_8));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("7º Distrito Policial"), BairroBusiness.findByDescricao("Floresta"), AreaIntegradaSeguranca.AIS_8));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("7º Distrito Policial"), BairroBusiness.findByDescricao("Jardim Iracema"), AreaIntegradaSeguranca.AIS_8));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("7º Distrito Policial"), BairroBusiness.findByDescricao("Pirambu"), AreaIntegradaSeguranca.AIS_8));



        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("19º Distrito Policial"), BairroBusiness.findByDescricao( "Conjunto Esperança"), AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("19º Distrito Policial"), BairroBusiness.findByDescricao( "Canindezinho"), AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("19º Distrito Policial"), BairroBusiness.findByDescricao( "Vila Manoel Sátiro"), AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("19º Distrito Policial"), BairroBusiness.findByDescricao( "Presidente Vargas"), AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("19º Distrito Policial"), BairroBusiness.findByDescricao( "Parque São José"), AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("19º Distrito Policial"), BairroBusiness.findByDescricao( "Maraponga"), AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("19º Distrito Policial"), BairroBusiness.findByDescricao( "Jardim Cearense"), AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("19º Distrito Policial"), BairroBusiness.findByDescricao( "Parque Santa Rosa"), AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("8º Distrito Policial"), BairroBusiness.findByDescricao("Mondubim"), AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("8º Distrito Policial"), BairroBusiness.findByDescricao("Planalto Ayrton Senna"), AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("8º Distrito Policial"), BairroBusiness.findByDescricao("Conjunto José Walter"), AreaIntegradaSeguranca.AIS_9));




        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("15º Distrito Policial"), BairroBusiness.findByDescricao( "Cocó"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("15º Distrito Policial"), BairroBusiness.findByDescricao( "Papicu"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("15º Distrito Policial"), BairroBusiness.findByDescricao( "Lourdes"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("15º Distrito Policial"), BairroBusiness.findByDescricao( "Municipio 2000"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("15º Distrito Policial"), BairroBusiness.findByDescricao( "Praia do Futuro I"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("15º Distrito Policial"), BairroBusiness.findByDescricao( "Praia do Futuro II"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("15º Distrito Policial"), BairroBusiness.findByDescricao( "Manoel Dias Branco"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("4º Distrito Policial"), BairroBusiness.findByDescricao("Engenheiro Luciano Cavalcante"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("4º Distrito Policial"), BairroBusiness.findByDescricao("São João do Tauape"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("4º Distrito Policial"), BairroBusiness.findByDescricao("Salinas"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("4º Distrito Policial"), BairroBusiness.findByDescricao("Joaquim Távora"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("4º Distrito Policial"), BairroBusiness.findByDescricao("Dionísio Torres"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("4º Distrito Policial"), BairroBusiness.findByDescricao("Guararapes"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("14º Distrito Policial"), BairroBusiness.findByDescricao( "Conjunto Industrial"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("14º Distrito Policial"), BairroBusiness.findByDescricao( "Esplanada Mondubim"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("14º Distrito Policial"), BairroBusiness.findByDescricao( "Aracapé"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("20º Distrito Policial"), BairroBusiness.findByDescricao( "Parque Tijuca"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("20º Distrito Policial"), BairroBusiness.findByDescricao( "Conjunto Acaracuzinho"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("20º Distrito Policial"), BairroBusiness.findByDescricao( "Santo Sátiro"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("20º Distrito Policial"), BairroBusiness.findByDescricao( "Santa Marta"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("20º Distrito Policial"), BairroBusiness.findByDescricao( "Alto Alegre I"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("20º Distrito Policial"), BairroBusiness.findByDescricao( "Alto Alegre II"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("20º Distrito Policial"), BairroBusiness.findByDescricao( "Jaçanaú"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("20º Distrito Policial"), BairroBusiness.findByDescricao( "Siqueira - Maracanaú"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("21º Distrito Policial"), BairroBusiness.findByDescricao( "Conjunto Timbó"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("21º Distrito Policial"), BairroBusiness.findByDescricao( "Distrito Industrial I"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("28º Distrito Policial"), BairroBusiness.findByDescricao( "Mucunã"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("28º Distrito Policial"), BairroBusiness.findByDescricao( "Parque Luzardo Viana"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("28º Distrito Policial"), BairroBusiness.findByDescricao( "Residencial Maracanaú"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("28º Distrito Policial"), BairroBusiness.findByDescricao( "Jenipapeiro"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("28º Distrito Policial"), BairroBusiness.findByDescricao( "Coqueiral"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("28º Distrito Policial"), BairroBusiness.findByDescricao( "Cágado"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("28º Distrito Policial"), BairroBusiness.findByDescricao( "Maracananzinho"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("28º Distrito Policial"), BairroBusiness.findByDescricao( "Novo Maracanaú"), AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais(DelegaciaBusiness.findByDescricao("28º Distrito Policial"), BairroBusiness.findByDescricao( "Pau Serrado"), AreaIntegradaSeguranca.AIS_10));


//        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", BairroBusiness.findByDescricao("Mutirão Vida Nova"), AreaIntegradaSeguranca.AIS_1));
//        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", BairroBusiness.findByDescricao("Colônia Antônio Justa"), AreaIntegradaSeguranca.AIS_1));
//        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", BairroBusiness.findByDescricao("Alto da Mangueira"), AreaIntegradaSeguranca.AIS_1));
//        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", BairroBusiness.findByDescricao("Olho D'Água"), AreaIntegradaSeguranca.AIS_1));
//        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", BairroBusiness.findByDescricao("Horto Florestal"), AreaIntegradaSeguranca.AIS_1));
//        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", BairroBusiness.findByDescricao("Jereissate I"), AreaIntegradaSeguranca.AIS_1));
//        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", BairroBusiness.findByDescricao("Jereissate II"), AreaIntegradaSeguranca.AIS_1));



        for (DadosTerritoriais dt : dadosTerritoriais)
            dt.save();

    }

    public static void InicializarBairros()
        {

            ArrayList<Bairro> bairros = new ArrayList<Bairro>();

        bairros.add(new Bairro("Acaracuzinho"));
        bairros.add(new Bairro("Aerolândia"));
        bairros.add(new Bairro("Aeroporto"));
        bairros.add(new Bairro("Água Fria"));
        bairros.add(new Bairro("Alagadiço"));
        bairros.add(new Bairro("Aldeota"));
        bairros.add(new Bairro("Alto Alegre"));
        bairros.add(new Bairro("Alto da Balança"));
        bairros.add(new Bairro("Álvaro Weyne"));
        bairros.add(new Bairro("Amadeu Furtado"));
        bairros.add(new Bairro("Ancuri"));
        bairros.add(new Bairro("Antônio Bezerra"));
        bairros.add(new Bairro("Antônio Diogo"));
        bairros.add(new Bairro("Araturi"));
        bairros.add(new Bairro("Arenópolis"));
        bairros.add(new Bairro("Autran Nunes"));
        bairros.add(new Bairro("Bairro de Lourdes"));
        bairros.add(new Bairro("Barra do Ceará"));
        bairros.add(new Bairro("Barroso"));
        bairros.add(new Bairro("Bela Vista"));
        bairros.add(new Bairro("Benfica"));
        bairros.add(new Bairro("Boa Vista"));
        bairros.add(new Bairro("Boa Vista Castelão"));
        bairros.add(new Bairro("Bom Futuro"));
        bairros.add(new Bairro("Bom Jardim"));
        bairros.add(new Bairro("Bonsucesso"));
        bairros.add(new Bairro("Cais do Porto"));
        bairros.add(new Bairro("Cajazeiras"));
        bairros.add(new Bairro("Cambeba"));
        bairros.add(new Bairro("Canindezinho"));
        bairros.add(new Bairro("Canto Verde"));
        bairros.add(new Bairro("Carlito Maia"));
        bairros.add(new Bairro("Carlito Pamplona"));
        bairros.add(new Bairro("Casa Porto"));
        bairros.add(new Bairro("Castelo Encantado"));
        bairros.add(new Bairro("Centro"));
        bairros.add(new Bairro("Municipio 2000"));
        bairros.add(new Bairro("Municipio dos Funcionários"));
        bairros.add(new Bairro("Coaçu"));
        bairros.add(new Bairro("Coco"));
        bairros.add(new Bairro("Conjunto Ceará"));
        bairros.add(new Bairro("Conjunto Esperança"));
        bairros.add(new Bairro("Conjunto Habitacional Aeronáutica"));
        bairros.add(new Bairro("Conjunto Nova Assunção"));
        bairros.add(new Bairro("Conjunto Nova Perimetral"));
        bairros.add(new Bairro("Conjunto Novo Oriente"));
        bairros.add(new Bairro("Conjunto Palmeiras"));
        bairros.add(new Bairro("Conjunto Parque Ipiranga"));
        bairros.add(new Bairro("Conjunto Planalto Pici"));
        bairros.add(new Bairro("Conjunto Prefeito José Walter"));
        bairros.add(new Bairro("Conjunto Sol Poente"));
        bairros.add(new Bairro("Conjunto Vila Velha Iv"));
        bairros.add(new Bairro("Couto Fernandes"));
        bairros.add(new Bairro("Cristo Redentor"));
        bairros.add(new Bairro("Curió"));
        bairros.add(new Bairro("Damas"));
        bairros.add(new Bairro("Demócrito Rocha"));
        bairros.add(new Bairro("Dendê"));
        bairros.add(new Bairro("Dias Macedo"));
        bairros.add(new Bairro("Dionísio Torres"));
        bairros.add(new Bairro("Distrito Industrial"));
        bairros.add(new Bairro("Distrito Industrial I"));
        bairros.add(new Bairro("Distrito Industrial III"));
        bairros.add(new Bairro("Dom Lustosa"));
        bairros.add(new Bairro("Dunas"));
        bairros.add(new Bairro("Edson Queiroz"));
        bairros.add(new Bairro("Engenheiro Luciano Cavalcante"));
        bairros.add(new Bairro("Farias Brito"));
        bairros.add(new Bairro("Fátima"));
        bairros.add(new Bairro("Floresta"));
        bairros.add(new Bairro("Granja Lisboa"));
        bairros.add(new Bairro("Granja Portugal"));
        bairros.add(new Bairro("Guajiru"));
        bairros.add(new Bairro("Guaramiranga"));
        bairros.add(new Bairro("Guararapes"));
        bairros.add(new Bairro("Henrique Jorge"));
        bairros.add(new Bairro("Industrial"));
        bairros.add(new Bairro("Itaóca"));
        bairros.add(new Bairro("Itaperi"));
        bairros.add(new Bairro("Jaboti"));
        bairros.add(new Bairro("Jacarecanga"));
        bairros.add(new Bairro("Jangurussu"));
        bairros.add(new Bairro("Jardim América"));
        bairros.add(new Bairro("Jardim Cearense"));
        bairros.add(new Bairro("Jardim das Oliveiras"));
        bairros.add(new Bairro("Jardim Guanabara"));
        bairros.add(new Bairro("Jardim Iracema"));
        bairros.add(new Bairro("Jardim Jatobá"));
        bairros.add(new Bairro("João Xxiii"));
        bairros.add(new Bairro("Joaquim Távora"));
        bairros.add(new Bairro("Jóquei Clube"));
        bairros.add(new Bairro("José Bonifácio"));
        bairros.add(new Bairro("José de Alencar"));
        bairros.add(new Bairro("Lago Verde"));
        bairros.add(new Bairro("Lagoa Redonda"));
        bairros.add(new Bairro("Loteamento Alfha Village"));
        bairros.add(new Bairro("Loteamento Araturi"));
        bairros.add(new Bairro("Loteamento Esplanada Castelão"));
        bairros.add(new Bairro("Loteamento Esplanada Messejana"));
        bairros.add(new Bairro("Loteamento Grande Aldeota"));
        bairros.add(new Bairro("Loteamento Jardim Bandeirantes"));
        bairros.add(new Bairro("Loteamento Jurema Park"));
        bairros.add(new Bairro("Loteamento Parque Dom Pedro"));
        bairros.add(new Bairro("Loteamento Parque Elisabeth"));
        bairros.add(new Bairro("Loteamento Parque Montenegro"));
        bairros.add(new Bairro("Loteamento Planalto João Xxiii"));
        bairros.add(new Bairro("Loteamento Planalto Mondubim"));
        bairros.add(new Bairro("Loteamento Sítio Santa Sofia"));
        bairros.add(new Bairro("Manoel Dias Branco"));
        bairros.add(new Bairro("Manuel Satiro"));
        bairros.add(new Bairro("Maraponga"));
        bairros.add(new Bairro("Meireles"));
        bairros.add(new Bairro("Messejana"));
        bairros.add(new Bairro("Mondubim"));
        bairros.add(new Bairro("Monte Castelo"));
        bairros.add(new Bairro("Montese"));
        bairros.add(new Bairro("Moura Brasil"));
        bairros.add(new Bairro("Mucuripe"));
        bairros.add(new Bairro("Novo Mondubim"));
        bairros.add(new Bairro("Olavo Oliveira"));
        bairros.add(new Bairro("Padre Andrade"));
        bairros.add(new Bairro("Pajuçara"));
        bairros.add(new Bairro("Pan Americano"));
        bairros.add(new Bairro("Papicu"));
        bairros.add(new Bairro("Parangaba"));
        bairros.add(new Bairro("Parque Alto Alegre"));
        bairros.add(new Bairro("Parque Araxá"));
        bairros.add(new Bairro("Parque Dois Irmãos"));
        bairros.add(new Bairro("Parque Genibau"));
        bairros.add(new Bairro("Parque Iracema"));
        bairros.add(new Bairro("Parque Jerusalém"));
        bairros.add(new Bairro("Parque Manibura"));
        bairros.add(new Bairro("Parque Novo Mondubim"));
        bairros.add(new Bairro("Parque Potira"));
        bairros.add(new Bairro("Parque Presidente Vargas"));
        bairros.add(new Bairro("Parque Santa Maria"));
        bairros.add(new Bairro("Parque Santa Rosa"));
        bairros.add(new Bairro("Parque São José"));
        bairros.add(new Bairro("Parque São Vicente"));
        bairros.add(new Bairro("Parquelândia"));
        bairros.add(new Bairro("Parreão"));
        bairros.add(new Bairro("Passaré"));
        bairros.add(new Bairro("Patriolino Ribeiro"));
        bairros.add(new Bairro("Paupina"));
        bairros.add(new Bairro("Pedras"));
        bairros.add(new Bairro("Pici"));
        bairros.add(new Bairro("Pirambu"));
        bairros.add(new Bairro("Planalto Ayrton Senna"));
        bairros.add(new Bairro("Praia de Iracema"));
        bairros.add(new Bairro("Praia Iracema"));
        bairros.add(new Bairro("Prefeito José Walter"));
        bairros.add(new Bairro("Presidente Kennedy"));
        bairros.add(new Bairro("Presidente Tancredo Neves"));
        bairros.add(new Bairro("Quintino Cunha"));
        bairros.add(new Bairro("Rodolfo Teófilo"));
        bairros.add(new Bairro("Sabiaguaba"));
        bairros.add(new Bairro("Salinas"));
        bairros.add(new Bairro("Santa Maria"));
        bairros.add(new Bairro("Santa Rosa"));
        bairros.add(new Bairro("São Bento"));
        bairros.add(new Bairro("São Gerardo"));
        bairros.add(new Bairro("São João do Tauape"));
        bairros.add(new Bairro("Sapiranga"));
        bairros.add(new Bairro("Serrinha"));
        bairros.add(new Bairro("Siqueira"));
        bairros.add(new Bairro("Varjota"));
        bairros.add(new Bairro("Vicente Pinzón"));
        bairros.add(new Bairro("Vila Ellery"));
        bairros.add(new Bairro("Vila Peri"));
        bairros.add(new Bairro("Vila União"));
        bairros.add(new Bairro("Vila Velha"));

        for(Bairro b : bairros)
            b.save();

        }

    public static void InicializarPessoas()
    {
        List<Usuario> usuarios = new ArrayList<>();

        //NUPEX GERAL

      usuarios.add( new Usuario("romulo.costa@pefoce.ce.gov.br", "Rômulo Costa do Nascimento",  "35702753372",0l));
      usuarios.add( new Usuario("charlton.bezerra@pefoce.ce.gov.br", "Charlton Bezerra",  "23198834368",0l));
      usuarios.add( new Usuario("jesus.sales@pefoce.ce.gov.br", "Jesus Ferreira Sales",  "04990838300",0l));
      usuarios.add( new Usuario("ireudo.pereira@pefoce.ce.gov.br", "Ireudo Pereira de Oliveira",  "51061171353",0l));
      usuarios.add( new Usuario("adriano.araujo@pefoce.ce.gov.br", "Adriano Araújo",  "47257679304",0l));
      usuarios.add( new Usuario("carlos.passos@pefoce.ce.gov.br", "Carlos Roberto Picanço Passos Júnior",  "00418759316",0l));
      usuarios.add( new Usuario("daniel.tabosa@pefoce.ce.gov.br", "Daniel Monteiro Tabosa",  "97095559368",0l));
      usuarios.add( new Usuario("marcondes.franca@pefoce.ce.gov.br", "Francisco Marcondes França de Sousa",  "41118235304",0l));
      usuarios.add( new Usuario("julio.rocha@pefoce.ce.gov.br", "Júlio César Rodrigues Rocha",  "01046795309",0l));
      usuarios.add( new Usuario("leda.queiroz@pefoce.ce.gov.br", "Leda Talita Afonso Ferreira de Queiroz",  "01358455481",0l));
      usuarios.add( new Usuario("leonardo.borges@pefoce.ce.gov.br", "Leonardo Borges Braga",  "00460566377",0l));
      usuarios.add( new Usuario("martonio.camelo@pefoce.ce.gov.br", "Martônio Camelo de Santana",  "42965136304",0l));
      usuarios.add( new Usuario("pedro.sergio@pefoce.ce.gov.br", "Pedro Sergio De Lima Amaro",  "21010897349",0l));
      usuarios.add( new Usuario("ronaldo.morais@pefoce.ce.gov.br", "Ronaldo Morais Fernandes",  "72383836320",0l));
      usuarios.add( new Usuario("sonia.silva@pefoce.ce.gov.br", "Sonia Maria da Silva Moreira",  "20946260320",0l));
      usuarios.add( new Usuario("lima.junior@pefoce.ce.gov.br", "Waldir Albertino De Lima Junior",  "42719461334",0l));
      usuarios.add( new Usuario("robson.paiva@pefoce.ce.gov.br", "Robson Fernandes de Paiva",  "54940133320",0l));
      usuarios.add( new Usuario("thiago.lobo@pefoce.ce.gov.br", "Thiago Diniz Lobo",  "02691056384",0l));
      usuarios.add( new Usuario("marcio.cavalcante@pefoce.ce.gov.br", "Márcio Roberto Uchoa Cavalcante",  "92217877372",0l));
      usuarios.add( new Usuario("rogeiro.alexandre@pefoce.ce.gov.br", "Rogério Alexandre Freires",  "77843797300",0l));
      usuarios.add( new Usuario("oliveira.junior@pefoce.ce.gov.br", "Francisco das Chagas Oliveira Júnior",  "61389153304",0l));
      usuarios.add( new Usuario("vinicius.aguiar@pefoce.ce.gov.br", "Vinícius do Nascimento Aguiar",  "00954067355",0l));
      usuarios.add( new Usuario("wellison.tavares@pefoce.ce.gov.br", "Wellison Tavares", "00367374374",0l));
      usuarios.add( new Usuario("julio.rocha@pefoce.ce.gov.br", "Júlio César Rodrigues Rocha", "01046795309",0l));
      usuarios.add( new Usuario("roberto.vieira@pefoce.ce.gov.br",  "Roberto Vieira e Silva Neto", "23251000306",0l));
      usuarios.add( new Usuario("cordeiro.Junior@pefoce.ce.gov.br",  "José Cordeiro de Oliveira Júnior", "35887192372",0l));
      usuarios.add( new Usuario("francisco.mendoca@pefoce.ce.gov.br", "Francisco Antonio Mendonça Barbosa" , "14228114334",0l));
      usuarios.add( new Usuario("ribeiro.abreu@pefoce.ce.gov.br", "Francisco Ribeiro De Abreu",  "16409175353",0l));
      usuarios.add( new Usuario("luiz.teixeira@pefoce.ce.gov.br", "Luiz Rodrigues Teixeira", "27547264387",0l));
      usuarios.add( new Usuario("celio.montezuma@pefoce.ce.gov.br", "Celio Ricardo Cordeiro Montezuma", "40854515372",0l));
      usuarios.add( new Usuario("percilia.rabelo@pefoce.ce.gov.br", "Maria Percilia Rabelo Machado", "41557310378",0l));
      usuarios.add( new Usuario("ricardo.lucena@pefoce.ce.gov.br", "Ricardo Lucena Cabral", "22826980378",0l));
      usuarios.add( new Usuario("paulo.henrique@pefoce.ce..gov.br", "Paulo Henrique Gifoni Maia", "28363817368",0l));
      usuarios.add( new Usuario("marcos.piccolo@pefoce.ce.gov.br", "Marcos Piccolo De Paula", "59491663534",0l));


      // Juazeiro do Norte
        usuarios.add( new Usuario("fabio.pontes@pefoce.ce.gov.br", "Fabio Montenegro Pontes", "04845164426",0l));
        usuarios.add( new Usuario("karleni.rosa@pefoce.ce.gov.br", "Maria Karleni Rodrigues Rosa", "02716235350",0l));
        usuarios.add( new Usuario("henrique.soares@pefoce.ce.gov.br", "Cicero Henrique Grangeiro Soares", "91582296391",0l));
        usuarios.add( new Usuario("pequeno.alves@pefoce.ce.gov.br", "Raimundo Carlos Alves Pereira", "77524454791",0l));


      //Quixeramobim
      usuarios.add( new Usuario("herbert.luis@pefoce.ce.gov.br", "Herbert Luis Costa De Andrade", "04590030462",0l));
      usuarios.add( new Usuario("otaviano.silva@pefoce.ce.gov.br", "Otaviano Do Nascimento Silva", "01203777302",0l));
      usuarios.add( new Usuario("joaquim.urculino@pefoce.ce.gov.br", "Joaquim Urçulino Melo Neto", "25865188372",0l));


        //Sobral
        usuarios.add( new Usuario("josemir.ramos@pefoce.ce.gov.br", "Josemir Emmerson Torres Ramos", "01166958388",0l));
        usuarios.add( new Usuario("joao.sabino@pefoce.ce.gov.br", "Joao Sabino De Oliveira Neto", "98611836391",0l));
        usuarios.add( new Usuario("alvaro.neuton@pefoce.ce.gov.br", "Alvaro Neuton De Araujo Silva", "02314545389",0l));
        usuarios.add( new Usuario("rafael.carneiro@pefoce.ce.gov.br", "Rafael Lopes Carneiro", "03711612318",0l));

      //Iguatu
        usuarios.add( new Usuario("lucas.teixeira@pefoce.ce.gov.br", "Lucas Antonio De Medeiros Teixeira", "87515431415",0l));
        usuarios.add( new Usuario("idelci.costa@pefoce.ce.gov.br", "Idelci Uile Costa",  "12316199372",0l));

      //Canindé
        usuarios.add( new Usuario("jeova.rodrigues@pefoce.ce.gov.br", "Jeová Rodrigues Carvalho Lima", "23229926315",0l));
        usuarios.add( new Usuario("danuzio.alves@pefoce.ce.gov.br", "Danuzio Alves de araujo", "30959608320",0l));
        usuarios.add( new Usuario("renato.oliveira@pefoce.ce.gov.br", "Renato De Oliveira Silva", "37163809387",0l));

        //Tauá
        usuarios.add( new Usuario("marcelo.albuquerque@pefoce.ce.gov.br", "Marcelo Albuquerque De Vasconcelos", "14396912315",0l));
        usuarios.add( new Usuario("francisco.nunes@pefoce.ce.gov.br", "Francisco De Assis Da Silva Nunes", "21955140359",0l));

      //Russas
        usuarios.add( new Usuario("raimundo.alves@pefoce.ce.gov.br", "Raimundo Alves Bezerra", "78625734391",0l));
        usuarios.add( new Usuario("danilo.jorge@pefoce.ce.gov.br", "Danilo Jorge Evangelista Cunha", "01757763341",0l));
        usuarios.add( new Usuario("paulo.cunha@pefoce.ce.gov.br", "Paulo Sergio Barbosa Da Cunha", "38070537353",0l));

      //dhpp
        usuarios.add( new Usuario("evandro.nogueira@pefoce.ce.gov.br", "Evandro Cajazeiras Nogueira",  "61567906320",0l));
        usuarios.add( new Usuario("felinto.sousa@pefoce.ce.gov.br", "Josevaldo Felinto de Sousa Júnior",  "37035363300",0l));
        usuarios.add( new Usuario("rosane.aguiar@pefoce.ce.gov.br", "Rosane Memória Aguiar",  "47081058353",0l));
        usuarios.add( new Usuario("leao.junior@pefoce.ce.gov.br", "Francisco Leão de Souza Júnior",  "50327011300",0l));
        usuarios.add( new Usuario("luciana.canito@pefoce.ce.gov.br", "Luciana Canito Austregésilo de Amorim",  "23082780300",0l));
        usuarios.add( new Usuario("marcos.lucas@pefoce.ce.gov.br", "Marcos Vinícius Soares Lucas",  "75543575300",0l));

      usuarios.add( new Usuario("123", "Convidado(a)",  "123",0l));



      for(Usuario u : usuarios)
          u.save();

//        new Usuario("	antonio.barbosa@pefoce.ce.gov.br", "Francisco Antônio Mendonça Barbosa",  "14228114334",0l);
//        new Usuario("	percilia.rabelo@pefoce.ce.gov.br", "Maria Percilia Rabelo Machado",  "41557310378",0l);
//        new Usuario("	kildary.abreu@pefoce.ce.gov.br", "Kildary de Abreu Silva",  "61744336334",0l);
//        new Usuario("	paulo.henrique@pefoce.ce.gov.br", "Paulo Henrique Gifoni Maia",  "28363817368",0l);
//        new Usuario("	cordeiro.Junior@pefoce.ce.gov.br", "José Cordeiro De Oliveira Junior",  "35887192372",0l);
//        new Usuario("	celio.montezuma@pefoce.ce.gov.br", "Célio Ricardo Cordeiro Montezuma",  "40854515372",0l);
    }

    public static void Popular()
    {
        InicializarPessoas();
        InicializarBairros();
        InicializarMunicipios();
        InicializarDelegacias();
        InicializarDadosTerritoriais();
    }
}
