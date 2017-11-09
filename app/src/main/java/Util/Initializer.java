package Util;

import android.util.Log;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Enums.AreaIntegradaSeguranca;
import Model.Bairro;
import Model.Cidade;
import Model.DadosTerritoriais;
import Model.Delegacia;
import Model.Pessoa;
import Model.Veiculo;

/**
 * Created by Pefoce on 21/06/2017.
 */

public class Initializer
{
    public static void InicializarPessoas()
    {
        String dtStart = "29/01/1994";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            Date date = format.parse(dtStart);

            Pessoa p = new Pessoa("123", "José 123", date, "123");
            p.save();

            Pessoa p2 = new Pessoa("456", "João 456", date, "456");
            p2.save();

            Pessoa p3 = new Pessoa("789", "Pedro 789", date, "789");
            p3.save();
            System.out.println(date);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        ArrayList<DadosTerritoriais> dadosTerritoriais = new ArrayList<>();


        //AIS 1
        // Cais do Porto, Vicente Pinzón, Mucuripe, Aldeota, Varjota, Praia de Iracema e Meireles.

        dadosTerritoriais.add(new DadosTerritoriais("9º Distrito Policial", "Vicente Pinzón", AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais("9º Distrito Policial", "Cais do Porto", AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais("26º Distrito Policial", "Praia de Iracema", AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais("2º Distrito Policial", "Meireles", AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais("2º Distrito Policial", "Aldeota", AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais("2º Distrito Policial", "Varjota", AreaIntegradaSeguranca.AIS_1));


        // AIS 2
        // Conjunto Ceará I e II, Genibaú, Granja Portugal, Bom Jardim, Granja Lisboa e Siqueira


        dadosTerritoriais.add(new DadosTerritoriais("12º Distrito Policial", "Conjunto Ceará I", AreaIntegradaSeguranca.AIS_2));
        dadosTerritoriais.add(new DadosTerritoriais("12º Distrito Policial", "Conjunto Ceará II", AreaIntegradaSeguranca.AIS_2));
        dadosTerritoriais.add(new DadosTerritoriais("12º Distrito Policial", "Parque Genibaú", AreaIntegradaSeguranca.AIS_2));
        dadosTerritoriais.add(new DadosTerritoriais("32º Distrito Policial", "Bom Jardim", AreaIntegradaSeguranca.AIS_2));
        dadosTerritoriais.add(new DadosTerritoriais("32º Distrito Policial", "Granja Lisboa", AreaIntegradaSeguranca.AIS_2));
        dadosTerritoriais.add(new DadosTerritoriais("32º Distrito Policial", "Siqueira", AreaIntegradaSeguranca.AIS_2));
        dadosTerritoriais.add(new DadosTerritoriais("12º Distrito Policial", "Granja Portugal", AreaIntegradaSeguranca.AIS_2));

        //AIS 3
        // Messejana,Ancuri, Pedras, Barroso, Jangurussu, Conjunto Palmeiras,  Curió, Lagoa Redonda, Guajeru,  SãoBento, Palpina, Parque Santa Maria e Coaçu


        dadosTerritoriais.add(new DadosTerritoriais("6º Distrito Policial", "Messejana", AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais("6º Distrito Policial", "Barroso", AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais("6º Distrito Policial", "Pedras", AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais("30º Distrito Policial", "Jangurussu", AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais("30º Distrito Policial", "Conjunto Palmeiras", AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais("35º Distrito Policial", "Curió", AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais("6º Distrito Policial", "Ancuri", AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais("35º Distrito Policial", "Lagoa Redonda", AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais("35º Distrito Policial", "Guajeru", AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais("35º Distrito Policial", "São Bento", AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais("35º Distrito Policial", "Paupina", AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais("6º Distrito Policial", "Parque Santa Maria", AreaIntegradaSeguranca.AIS_3));
        dadosTerritoriais.add(new DadosTerritoriais("35º Distrito Policial", "Coaçu", AreaIntegradaSeguranca.AIS_3));

        //AIS 4
        //Centro,Moura  Brasil, Carlito Pamplona,  Álvaro Weyne, Vila Ellery, Monte Castelo, Farias Brito, São Gerardo, Jacarecanga.


        dadosTerritoriais.add(new DadosTerritoriais("34º Distrito Policial", "Centro", AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais("1º Distrito Policial", "Carlito Pamplona", AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais("1º Distrito Policial", "Àlvaro Weyne", AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais("1º Distrito Policial", "Vila Ellery", AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais("1º Distrito Policial", "Monte Castelo", AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais("3º Distrito Policial", "Farias Brito", AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais("3º Distrito Policial", "São Gerardo", AreaIntegradaSeguranca.AIS_4));
        dadosTerritoriais.add(new DadosTerritoriais("3º Distrito Policial", "Jacarecanga", AreaIntegradaSeguranca.AIS_4));

        //AIS 5
        //Parangaba, Vila Pery, Itaoca,Itaperi, Dendê, Pan Americano, Jardim América,
        // Benfica, Demócrito Rocha, Couto Fernandes,Montese,Damas,Bom Futuro,Vila União, José Bonifácio, Parreão, Fátima, Serrinha, Aeroporto.


        dadosTerritoriais.add(new DadosTerritoriais("5º Distrito Policial", "Parangaba", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("5º Distrito Policial", "Itaoca", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("5º Distrito Policial", "Itaperi", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("5º Distrito Policial", "Dendê", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("11º Distrito Policial", "Panamericano", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("11º Distrito Policial", "Benfica", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("11º Distrito Policial", "Demócrito Rocha", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("11º Distrito Policial", "Jardim América", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("11º Distrito Policial", "Couto Fernandes", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("11º Distrito Policial", "Montese", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("11º Distrito Policial", "Damas", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("11º Distrito Policial", "Bom Futuro", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("25º Distrito Policial", "Vila União", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("25º Distrito Policial", "José Bonifácio", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("25º Distrito Policial", "Parreão", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("25º Distrito Policial", "Bairro de Fátima", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("5º Distrito Policial", "Vila Pery", AreaIntegradaSeguranca.AIS_5));


        //AIS 6


        dadosTerritoriais.add(new DadosTerritoriais("10º Distrito Policial", "Antônio Bezerra", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("10º Distrito Policial", "Quintino Cunha", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("10º Distrito Policial", "Olavo Oliveira", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("10º Distrito Policial", "Padre Andrade", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("10º Distrito Policial", "Bela Vista", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("10º Distrito Policial", "Presidente Kennedy", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("10º Distrito Policial", "Parquelândia", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("10º Distrito Policial", "Amadeu Furtado", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("10º Distrito Policial", "Parque Araxá", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("10º Distrito Policial", "Rodolfo Teófilo", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("27º Distrito Policial", "Bom Sucesso", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("27º Distrito Policial", "João XXIII", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("27º Distrito Policial", "Jóquei Clube", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("27º Distrito Policial", "Henrique Jorge", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("27º Distrito Policial", "Autran Nunes", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("27º Distrito Policial", "Pici", AreaIntegradaSeguranca.AIS_5));
        dadosTerritoriais.add(new DadosTerritoriais("27º Distrito Policial", "Dom Lustosa", AreaIntegradaSeguranca.AIS_5));


        //AIS 7

        dadosTerritoriais.add(new DadosTerritoriais("26º Distrito Policial", "Edson Queiroz", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("26º Distrito Policial", "Sabiaguaba", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("26º Distrito Policial", "Cambeba", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("26º Distrito Policial", "José de Alencar", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("26º Distrito Policial", "Parque Iracema", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("26º Distrito Policial", "Parque Manibura", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("26º Distrito Policial", "Sapiranga", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("13º Distrito Policial", "Cidade dos Funcionários", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("13º Distrito Policial", "Alto da Balança", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("13º Distrito Policial", "Cajazeiras", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("16º Distrito Policial", "Parque Dois Irmãos", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("16º Distrito Policial", "Passaré", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("13º Distrito Policial", "Aerolândia", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("16º Distrito Policial", "Boa Vista", AreaIntegradaSeguranca.AIS_7));
        dadosTerritoriais.add(new DadosTerritoriais("16º Distrito Policial", "Dias Macêdo", AreaIntegradaSeguranca.AIS_7));


        //AIS 8

        dadosTerritoriais.add(new DadosTerritoriais("33º Distrito Policial", "Barra do Ceará", AreaIntegradaSeguranca.AIS_8));
        dadosTerritoriais.add(new DadosTerritoriais("17º Distrito Policial", "Vila Velha", AreaIntegradaSeguranca.AIS_8));
        dadosTerritoriais.add(new DadosTerritoriais("17º Distrito Policial", "Jardim Guanabara", AreaIntegradaSeguranca.AIS_8));
        dadosTerritoriais.add(new DadosTerritoriais("7º Distrito Policial", "Cristo Redentor", AreaIntegradaSeguranca.AIS_8));
        dadosTerritoriais.add(new DadosTerritoriais("7º Distrito Policial", "Floresta", AreaIntegradaSeguranca.AIS_8));
        dadosTerritoriais.add(new DadosTerritoriais("7", "Jardim Iracema", AreaIntegradaSeguranca.AIS_8));
        dadosTerritoriais.add(new DadosTerritoriais("7º Distrito Policial", "Pirambu", AreaIntegradaSeguranca.AIS_8));

        //AIS 9

        dadosTerritoriais.add(new DadosTerritoriais("19º Distrito Policial", "Conjunto Esperança", AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais("19º Distrito Policial", "Vila Manoel Sátiro", AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais("19º Distrito Policial", "Presidente Vargas", AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais("19º Distrito Policial", "Parque São José", AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais("19º Distrito Policial", "Maraponga", AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais("19º Distrito Policial", "Jardim Cearense", AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais("19º Distrito Policial", "Parque Santa Rosa", AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais("8º Distrito Policial", "Mondubim", AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais("8º Distrito Policial", "Planalto Ayrton Senna", AreaIntegradaSeguranca.AIS_9));
        dadosTerritoriais.add(new DadosTerritoriais("8º Distrito Policial", "Conjunto José Walter", AreaIntegradaSeguranca.AIS_9));


        //AIS 10

        dadosTerritoriais.add(new DadosTerritoriais("15º Distrito Policial", "Papicu", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("15º Distrito Policial", "Lourdes", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("15º Distrito Policial", "Cidade 2000", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("15º Distrito Policial", "Praia do Futuro I", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("15º Distrito Policial", "Praia do Futuro II", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("15º Distrito Policial", "Manoel Dias Branco", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("4º Distrito Policial", "Engenheiro Luciano Cavalcante", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("4º Distrito Policial", "São João do Tauape", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("4º Distrito Policial", "Salinas", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("4º Distrito Policial", "Joaquim Távora", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("4º Distrito Policial", "Dionísio Torres", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("4º Distrito Policial", "Guararapes", AreaIntegradaSeguranca.AIS_10));


        dadosTerritoriais.add(new DadosTerritoriais("14º Distrito Policial", "Conjunto Industrial", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("14º Distrito Policial", "Esplanada Mondubim", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("14º Distrito Policial", "Aracapé", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("20º Distrito Policial", "Parque Tijuca", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("20º Distrito Policial", "Conjunto Acaracuzinho", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("20º Distrito Policial", "Santo Sátiro", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("20º Distrito Policial", "Santa Marta", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("20º Distrito Policial", "Alto Alegre I", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("20º Distrito Policial", "Alto Alegre II", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("20º Distrito Policial", "Jaçanaú", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("20º Distrito Policial", "Siqueira - Maracanaú", AreaIntegradaSeguranca.AIS_10));


        dadosTerritoriais.add(new DadosTerritoriais("21º Distrito Policial", "Conjunto Timbó", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("21º Distrito Policial", "Distrito Industrial I", AreaIntegradaSeguranca.AIS_10));

        dadosTerritoriais.add(new DadosTerritoriais("28º Distrito Policial", "Mucunã", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("28º Distrito Policial", "Parque Luzardo Viana", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("28º Distrito Policial", "Residencial Maracanaú", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("28º Distrito Policial", "Jenipapeiro", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("28º Distrito Policial", "Coqueiral", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("28º Distrito Policial", "Cágado", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("28º Distrito Policial", "Maracananzinho", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("28º Distrito Policial", "Novo Maracanaú", AreaIntegradaSeguranca.AIS_10));
        dadosTerritoriais.add(new DadosTerritoriais("28º Distrito Policial", "Pau Serrado", AreaIntegradaSeguranca.AIS_10));


        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", "Mutirão Vida Nova", AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", "Colônia Antônio Justa", AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", "Alto da Mangueira", AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", "Olho D'Água", AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", "Horto Florestal", AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", "Jereissate I", AreaIntegradaSeguranca.AIS_1));
        dadosTerritoriais.add(new DadosTerritoriais("Delegacia Metropolitana de Maracanaú", "Jereissate II", AreaIntegradaSeguranca.AIS_1));


//
//        bairros.add(new Bairro("Acaracuzinho"));
//        bairros.add(new Bairro("Aerolândia"));
//        bairros.add(new Bairro("Aeroporto"));
//        bairros.add(new Bairro("Água Fria"));
//        bairros.add(new Bairro("Alagadiço"));
//        bairros.add(new Bairro("Aldeota"));
//        bairros.add(new Bairro("Alto Alegre"));
//        bairros.add(new Bairro("Alto da Balança"));
//        bairros.add(new Bairro("Álvaro Weyne"));
//        bairros.add(new Bairro("Amadeu Furtado"));
//        bairros.add(new Bairro("Ancuri"));
//        bairros.add(new Bairro("Antônio Bezerra"));
//        bairros.add(new Bairro("Antônio Diogo"));
//        bairros.add(new Bairro("Araturi"));
//        bairros.add(new Bairro("Arenópolis"));
//        bairros.add(new Bairro("Autran Nunes"));
//        bairros.add(new Bairro("Bairro de Lourdes"));
//        bairros.add(new Bairro("Barra do Ceará"));
//        bairros.add(new Bairro("Barroso"));
//        bairros.add(new Bairro("Bela Vista"));
//        bairros.add(new Bairro("Benfica"));
//        bairros.add(new Bairro("Boa Vista"));
//        bairros.add(new Bairro("Boa Vista Castelão"));
//        bairros.add(new Bairro("Bom Futuro"));
//        bairros.add(new Bairro("Bom Jardim"));
//        bairros.add(new Bairro("Bonsucesso"));
//        bairros.add(new Bairro("Cais do Porto"));
//        bairros.add(new Bairro("Cajazeiras"));
//        bairros.add(new Bairro("Cambeba"));
//        bairros.add(new Bairro("Canindezinho"));
//        bairros.add(new Bairro("Canto Verde"));
//        bairros.add(new Bairro("Carlito Maia"));
//        bairros.add(new Bairro("Carlito Pamplona"));
//        bairros.add(new Bairro("Casa Porto"));
//        bairros.add(new Bairro("Castelo Encantado"));
//        bairros.add(new Bairro("Centro"));
//        bairros.add(new Bairro("Cidade 2000"));
//        bairros.add(new Bairro("Cidade dos Funcionários"));
//        bairros.add(new Bairro("Coaçu"));
//        bairros.add(new Bairro("Coco"));
//        bairros.add(new Bairro("Conjunto Ceará"));
//        bairros.add(new Bairro("Conjunto Esperança"));
//        bairros.add(new Bairro("Conjunto Habitacional Aeronáutica"));
//        bairros.add(new Bairro("Conjunto Nova Assunção"));
//        bairros.add(new Bairro("Conjunto Nova Perimetral"));
//        bairros.add(new Bairro("Conjunto Novo Oriente"));
//        bairros.add(new Bairro("Conjunto Palmeiras"));
//        bairros.add(new Bairro("Conjunto Parque Ipiranga"));
//        bairros.add(new Bairro("Conjunto Planalto Pici"));
//        bairros.add(new Bairro("Conjunto Prefeito José Walter"));
//        bairros.add(new Bairro("Conjunto Sol Poente"));
//        bairros.add(new Bairro("Conjunto Vila Velha Iv"));
//        bairros.add(new Bairro("Couto Fernandes"));
//        bairros.add(new Bairro("Cristo Redentor"));
//        bairros.add(new Bairro("Curió"));
//        bairros.add(new Bairro("Damas"));
//        bairros.add(new Bairro("Demócrito Rocha"));
//        bairros.add(new Bairro("Dendê"));
//        bairros.add(new Bairro("Dias Macedo"));
//        bairros.add(new Bairro("Dionísio Torres"));
//        bairros.add(new Bairro("Distrito Industrial"));
//        bairros.add(new Bairro("Distrito Industrial I"));
//        bairros.add(new Bairro("Distrito Industrial III"));
//        bairros.add(new Bairro("Dom Lustosa"));
//        bairros.add(new Bairro("Dunas"));
//        bairros.add(new Bairro("Edson Queiroz"));
//        bairros.add(new Bairro("Engenheiro Luciano Cavalcante"));
//        bairros.add(new Bairro("Farias Brito"));
//        bairros.add(new Bairro("Fátima"));
//        bairros.add(new Bairro("Floresta"));
//        bairros.add(new Bairro("Granja Lisboa"));
//        bairros.add(new Bairro("Granja Portugal"));
//        bairros.add(new Bairro("Guajiru"));
//        bairros.add(new Bairro("Guaramiranga"));
//        bairros.add(new Bairro("Guararapes"));
//        bairros.add(new Bairro("Henrique Jorge"));
//        bairros.add(new Bairro("Industrial"));
//        bairros.add(new Bairro("Itaóca"));
//        bairros.add(new Bairro("Itaperi"));
//        bairros.add(new Bairro("Jaboti"));
//        bairros.add(new Bairro("Jacarecanga"));
//        bairros.add(new Bairro("Jangurussu"));
//        bairros.add(new Bairro("Jardim América"));
//        bairros.add(new Bairro("Jardim Cearense"));
//        bairros.add(new Bairro("Jardim das Oliveiras"));
//        bairros.add(new Bairro("Jardim Guanabara"));
//        bairros.add(new Bairro("Jardim Iracema"));
//        bairros.add(new Bairro("Jardim Jatobá"));
//        bairros.add(new Bairro("João Xxiii"));
//        bairros.add(new Bairro("Joaquim Távora"));
//        bairros.add(new Bairro("Jóquei Clube"));
//        bairros.add(new Bairro("José Bonifácio"));
//        bairros.add(new Bairro("José de Alencar"));
//        bairros.add(new Bairro("Lago Verde"));
//        bairros.add(new Bairro("Lagoa Redonda"));
//        bairros.add(new Bairro("Loteamento Alfha Village"));
//        bairros.add(new Bairro("Loteamento Araturi"));
//        bairros.add(new Bairro("Loteamento Esplanada Castelão"));
//        bairros.add(new Bairro("Loteamento Esplanada Messejana"));
//        bairros.add(new Bairro("Loteamento Grande Aldeota"));
//        bairros.add(new Bairro("Loteamento Jardim Bandeirantes"));
//        bairros.add(new Bairro("Loteamento Jurema Park"));
//        bairros.add(new Bairro("Loteamento Parque Dom Pedro"));
//        bairros.add(new Bairro("Loteamento Parque Elisabeth"));
//        bairros.add(new Bairro("Loteamento Parque Montenegro"));
//        bairros.add(new Bairro("Loteamento Planalto João Xxiii"));
//        bairros.add(new Bairro("Loteamento Planalto Mondubim"));
//        bairros.add(new Bairro("Loteamento Sítio Santa Sofia"));
//        bairros.add(new Bairro("Manoel Dias Branco"));
//        bairros.add(new Bairro("Manuel Satiro"));
//        bairros.add(new Bairro("Maraponga"));
//        bairros.add(new Bairro("Meireles"));
//        bairros.add(new Bairro("Messejana"));
//        bairros.add(new Bairro("Mondubim"));
//        bairros.add(new Bairro("Monte Castelo"));
//        bairros.add(new Bairro("Montese"));
//        bairros.add(new Bairro("Moura Brasil"));
//        bairros.add(new Bairro("Mucuripe"));
//        bairros.add(new Bairro("Novo Mondubim"));
//        bairros.add(new Bairro("Olavo Oliveira"));
//        bairros.add(new Bairro("Padre Andrade"));
//        bairros.add(new Bairro("Pajuçara"));
//        bairros.add(new Bairro("Pan Americano"));
//        bairros.add(new Bairro("Papicu"));
//        bairros.add(new Bairro("Parangaba"));
//        bairros.add(new Bairro("Parque Alto Alegre"));
//        bairros.add(new Bairro("Parque Araxá"));
//        bairros.add(new Bairro("Parque Dois Irmãos"));
//        bairros.add(new Bairro("Parque Genibau"));
//        bairros.add(new Bairro("Parque Iracema"));
//        bairros.add(new Bairro("Parque Jerusalém"));
//        bairros.add(new Bairro("Parque Manibura"));
//        bairros.add(new Bairro("Parque Novo Mondubim"));
//        bairros.add(new Bairro("Parque Potira"));
//        bairros.add(new Bairro("Parque Presidente Vargas"));
//        bairros.add(new Bairro("Parque Santa Maria"));
//        bairros.add(new Bairro("Parque Santa Rosa"));
//        bairros.add(new Bairro("Parque São José"));
//        bairros.add(new Bairro("Parque São Vicente"));
//        bairros.add(new Bairro("Parquelândia"));
//        bairros.add(new Bairro("Parreão"));
//        bairros.add(new Bairro("Passaré"));
//        bairros.add(new Bairro("Patriolino Ribeiro"));
//        bairros.add(new Bairro("Paupina"));
//        bairros.add(new Bairro("Pedras"));
//        bairros.add(new Bairro("Pici"));
//        bairros.add(new Bairro("Pirambu"));
//        bairros.add(new Bairro("Planalto Ayrton Senna"));
//        bairros.add(new Bairro("Praia de Iracema"));
//        bairros.add(new Bairro("Praia Iracema"));
//        bairros.add(new Bairro("Prefeito José Walter"));
//        bairros.add(new Bairro("Presidente Kennedy"));
//        bairros.add(new Bairro("Presidente Tancredo Neves"));
//        bairros.add(new Bairro("Quintino Cunha"));
//        bairros.add(new Bairro("Rodolfo Teófilo"));
//        bairros.add(new Bairro("Sabiaguaba"));
//        bairros.add(new Bairro("Salinas"));
//        bairros.add(new Bairro("Santa Maria"));
//        bairros.add(new Bairro("Santa Rosa"));
//        bairros.add(new Bairro("São Bento"));
//        bairros.add(new Bairro("São Gerardo"));
//        bairros.add(new Bairro("São João do Tauape"));
//        bairros.add(new Bairro("Sapiranga"));
//        bairros.add(new Bairro("Serrinha"));
//        bairros.add(new Bairro("Siqueira"));
//        bairros.add(new Bairro("Varjota"));
//        bairros.add(new Bairro("Vicente Pinzon"));
//        bairros.add(new Bairro("Vila Ellery"));
//        bairros.add(new Bairro("Vila Peri"));
//        bairros.add(new Bairro("Vila União"));
//        bairros.add(new Bairro("Vila Velha"));
//
//


        ArrayList<Cidade> cidades = new ArrayList<>();
        cidades.add(new Cidade("Abaiara"));
        cidades.add(new Cidade("Acarape"));
        cidades.add(new Cidade("Acaraú"));
        cidades.add(new Cidade("Acopiara"));
        cidades.add(new Cidade("Aiuaba"));
        cidades.add(new Cidade("Alcântaras"));
        cidades.add(new Cidade("Altaneira"));
        cidades.add(new Cidade("Alto Santo"));
        cidades.add(new Cidade("Amontada"));
        cidades.add(new Cidade("Antonina do Norte"));
        cidades.add(new Cidade("Apuiarés"));
        cidades.add(new Cidade("Aquiraz"));
        cidades.add(new Cidade("Aracati"));
        cidades.add(new Cidade("Aracoiaba"));
        cidades.add(new Cidade("Ararendá"));
        cidades.add(new Cidade("Araripe"));
        cidades.add(new Cidade("Aratuba"));
        cidades.add(new Cidade("Arneiroz"));
        cidades.add(new Cidade("Assaré"));
        cidades.add(new Cidade("Aurora"));
        cidades.add(new Cidade("Baixio"));
        cidades.add(new Cidade("Banabuiú"));
        cidades.add(new Cidade("Barbalha"));
        cidades.add(new Cidade("Barreira"));
        cidades.add(new Cidade("Barro"));
        cidades.add(new Cidade("Barroquinha"));
        cidades.add(new Cidade("Baturité"));
        cidades.add(new Cidade("Beberibe"));
        cidades.add(new Cidade("Bela Cruz"));
        cidades.add(new Cidade("Boa Viagem"));
        cidades.add(new Cidade("Brejo Santo"));
        cidades.add(new Cidade("Camocim"));
        cidades.add(new Cidade("Campos Sales"));
        cidades.add(new Cidade("Canindé"));
        cidades.add(new Cidade("Capistrano"));
        cidades.add(new Cidade("Caridade"));
        cidades.add(new Cidade("Cariré"));
        cidades.add(new Cidade("Caririaçu"));
        cidades.add(new Cidade("Cariús"));
        cidades.add(new Cidade("Carnaubal"));
        cidades.add(new Cidade("Cascavel"));
        cidades.add(new Cidade("Catarina"));
        cidades.add(new Cidade("Catunda"));
        cidades.add(new Cidade("Caucaia"));
        cidades.add(new Cidade("Cedro"));
        cidades.add(new Cidade("Chaval"));
        cidades.add(new Cidade("Choró"));
        cidades.add(new Cidade("Chorozinho"));
        cidades.add(new Cidade("Coreaú"));
        cidades.add(new Cidade("Crateús"));
        cidades.add(new Cidade("Crato"));
        cidades.add(new Cidade("Croatá"));
        cidades.add(new Cidade("Cruz"));
        cidades.add(new Cidade("Deputado Irapuan Pinheiro"));
        cidades.add(new Cidade("Ererê"));
        cidades.add(new Cidade("Eusébio"));
        cidades.add(new Cidade("Farias Brito"));
        cidades.add(new Cidade("Forquilha"));
        cidades.add(new Cidade("Fortaleza"));
        cidades.add(new Cidade("Fortim"));
        cidades.add(new Cidade("Frecheirinha"));
        cidades.add(new Cidade("General Sampaio"));
        cidades.add(new Cidade("Graça"));
        cidades.add(new Cidade("Granja"));
        cidades.add(new Cidade("Granjeiro"));
        cidades.add(new Cidade("Groaíras"));
        cidades.add(new Cidade("Guaiúba"));
        cidades.add(new Cidade("Guaraciaba do Norte"));
        cidades.add(new Cidade("Guaramiranga"));
        cidades.add(new Cidade("Hidrolândia"));
        cidades.add(new Cidade("Horizonte"));
        cidades.add(new Cidade("Ibaretama"));
        cidades.add(new Cidade("Ibiapina"));
        cidades.add(new Cidade("Ibicuitinga"));
        cidades.add(new Cidade("Icapuí"));
        cidades.add(new Cidade("Icó"));
        cidades.add(new Cidade("Iguatu"));
        cidades.add(new Cidade("Independência"));
        cidades.add(new Cidade("Ipaporanga"));
        cidades.add(new Cidade("Ipaumirim"));
        cidades.add(new Cidade("Ipu"));
        cidades.add(new Cidade("Ipueiras"));
        cidades.add(new Cidade("Iracema"));
        cidades.add(new Cidade("Irauçuba"));
        cidades.add(new Cidade("Itaiçaba"));
        cidades.add(new Cidade("Itaitinga"));
        cidades.add(new Cidade("Itapajé"));
        cidades.add(new Cidade("Itapipoca"));
        cidades.add(new Cidade("Itapiúna"));
        cidades.add(new Cidade("Itarema"));
        cidades.add(new Cidade("Itatira"));
        cidades.add(new Cidade("Jaguaretama"));
        cidades.add(new Cidade("Jaguaribara"));
        cidades.add(new Cidade("Jaguaribe"));
        cidades.add(new Cidade("Jaguaruana"));
        cidades.add(new Cidade("Jardim"));
        cidades.add(new Cidade("Jati"));
        cidades.add(new Cidade("Jijoca de Jericoacoara"));
        cidades.add(new Cidade("Juazeiro do Norte"));
        cidades.add(new Cidade("Jucás"));
        cidades.add(new Cidade("Lavras da Mangabeira"));
        cidades.add(new Cidade("Limoeiro do Norte"));
        cidades.add(new Cidade("Madalena"));
        cidades.add(new Cidade("Maracanaú"));
        cidades.add(new Cidade("Maranguape"));
        cidades.add(new Cidade("Marco"));
        cidades.add(new Cidade("Martinópole"));
        cidades.add(new Cidade("Massapê"));
        cidades.add(new Cidade("Mauriti"));
        cidades.add(new Cidade("Meruoca"));
        cidades.add(new Cidade("Milagres"));
        cidades.add(new Cidade("Milhã"));
        cidades.add(new Cidade("Miraíma"));
        cidades.add(new Cidade("Missão Velha"));
        cidades.add(new Cidade("Mombaça"));
        cidades.add(new Cidade("Monsenhor Tabosa"));
        cidades.add(new Cidade("Morada Nova"));
        cidades.add(new Cidade("Moraújo"));
        cidades.add(new Cidade("Morrinhos"));
        cidades.add(new Cidade("Mucambo"));
        cidades.add(new Cidade("Mulungu"));
        cidades.add(new Cidade("Nova Olinda"));
        cidades.add(new Cidade("Nova Russas"));
        cidades.add(new Cidade("Novo Oriente"));
        cidades.add(new Cidade("Ocara"));
        cidades.add(new Cidade("Orós"));
        cidades.add(new Cidade("Pacajus"));
        cidades.add(new Cidade("Pacatuba"));
        cidades.add(new Cidade("Pacoti"));
        cidades.add(new Cidade("Pacujá"));
        cidades.add(new Cidade("Palhano"));
        cidades.add(new Cidade("Palmácia"));
        cidades.add(new Cidade("Paracuru"));
        cidades.add(new Cidade("Paraipaba"));
        cidades.add(new Cidade("Parambu"));
        cidades.add(new Cidade("Paramoti"));
        cidades.add(new Cidade("Pedra Branca"));
        cidades.add(new Cidade("Penaforte"));
        cidades.add(new Cidade("Pentecoste"));
        cidades.add(new Cidade("Pereiro"));
        cidades.add(new Cidade("Pindoretama"));
        cidades.add(new Cidade("Piquet Carneiro"));
        cidades.add(new Cidade("Pires Ferreira"));
        cidades.add(new Cidade("Poranga"));
        cidades.add(new Cidade("Porteiras"));
        cidades.add(new Cidade("Potengi"));
        cidades.add(new Cidade("Potiretama"));
        cidades.add(new Cidade("Quiterianópolis"));
        cidades.add(new Cidade("Quixadá"));
        cidades.add(new Cidade("Quixelô"));
        cidades.add(new Cidade("Quixeramobim"));
        cidades.add(new Cidade("Quixeré"));
        cidades.add(new Cidade("Redenção"));
        cidades.add(new Cidade("Reriutaba"));
        cidades.add(new Cidade("Russas"));
        cidades.add(new Cidade("Saboeiro"));
        cidades.add(new Cidade("Salitre"));
        cidades.add(new Cidade("Santa Quitéria"));
        cidades.add(new Cidade("Santana do Acaraú"));
        cidades.add(new Cidade("Santana do Cariri"));
        cidades.add(new Cidade("São Benedito"));
        cidades.add(new Cidade("São Gonçalo do Amarante"));
        cidades.add(new Cidade("São João do Jaguaribe"));
        cidades.add(new Cidade("São Luís do Curu"));
        cidades.add(new Cidade("Senador Pompeu"));
        cidades.add(new Cidade("Senador Sá"));
        cidades.add(new Cidade("Sobral"));
        cidades.add(new Cidade("Solonópole"));
        cidades.add(new Cidade("Tabuleiro do Norte"));
        cidades.add(new Cidade("Tamboril"));
        cidades.add(new Cidade("Tarrafas"));
        cidades.add(new Cidade("Tauá"));
        cidades.add(new Cidade("Tejuçuoca"));
        cidades.add(new Cidade("Tianguá"));
        cidades.add(new Cidade("Trairi"));
        cidades.add(new Cidade("Tururu"));
        cidades.add(new Cidade("Ubajara"));
        cidades.add(new Cidade("Umari"));
        cidades.add(new Cidade("Umirim"));
        cidades.add(new Cidade("Uruburetama"));
        cidades.add(new Cidade("Uruoca"));
        cidades.add(new Cidade("Varjota"));
        cidades.add(new Cidade("Várzea Alegre"));
        cidades.add(new Cidade("Viçosa do Ceará"));


        ArrayList<Delegacia> delegas = new ArrayList<Delegacia>();

        delegas.add(new Delegacia("CIOPS - Coordenadoria Integrada de Operações de Segurança"));
        delegas.add(new Delegacia("Delegacia Metropolitana de Caucaia "));
        delegas.add(new Delegacia("Delegacia Metropolitana de Pacatuba "));
        delegas.add(new Delegacia("Delegacia Metropolitana de Guaiuba "));
        delegas.add(new Delegacia("Delegacia Metropolitana de Maranguape "));
        delegas.add(new Delegacia("Delegacia Metropolitana de Eusebio "));
        delegas.add(new Delegacia("Delegacia Metropolitana de Itaitinga "));
        delegas.add(new Delegacia("Delegacia Municipal de Abaiara "));
        delegas.add(new Delegacia("Delegacia Municipal de Acarape "));
        delegas.add(new Delegacia("Delegacia Regional de Acarau "));
        delegas.add(new Delegacia("Delegacia Municipal de Acopiara "));
        delegas.add(new Delegacia("Delegacia Municipal de Aiuaba "));
        delegas.add(new Delegacia("Delegacia Municipal de Alcantara "));
        delegas.add(new Delegacia("Delegacia Municipal de Altaneira "));
        delegas.add(new Delegacia("Delegacia Municipal de Alto Santo "));
        delegas.add(new Delegacia("Delegacia Municipal de Amontada "));
        delegas.add(new Delegacia("Delegacia Municipal de Antonina do Norte "));
        delegas.add(new Delegacia("Delegacia Municipal de Apuiares "));
        delegas.add(new Delegacia("Delegacia Regional de Aracati "));
        delegas.add(new Delegacia("Delegacia Municipal de Aracoiaba "));
        delegas.add(new Delegacia("Delegacia Municipal de Araripe "));
        delegas.add(new Delegacia("Delegacia Municipal de Aratuba "));
        delegas.add(new Delegacia("Delegacia Municipal de Assare "));
        delegas.add(new Delegacia("Delegacia Municipal de Aurora "));
        delegas.add(new Delegacia("Delegacia Municipal de Barbalha "));
        delegas.add(new Delegacia("Delegacia Municipal de Barreira "));
        delegas.add(new Delegacia("Delegacia Regional de Baturite "));
        delegas.add(new Delegacia("Delegacia Municipal de Beberibe "));
        delegas.add(new Delegacia("Delegacia Municipal de Boa Viagem "));
        delegas.add(new Delegacia("Delegacia Regional de Brejo Santo "));
        delegas.add(new Delegacia("Delegacia Regional de Camocim "));
        delegas.add(new Delegacia("Delegacia Regional de Caninde "));
        delegas.add(new Delegacia("Delegacia Municipal de Capistrano "));
        delegas.add(new Delegacia("Delegacia Municipal de Caridade "));
        delegas.add(new Delegacia("Delegacia Municipal de Caririacu "));
        delegas.add(new Delegacia("Delegacia Municipal de Carius "));
        delegas.add(new Delegacia("Delegacia Municipal de Cascavel "));
        delegas.add(new Delegacia("Delegacia Municipal de Catarina "));
        delegas.add(new Delegacia("Delegacia Municipal de Cedro "));
        delegas.add(new Delegacia("Delegacia Municipal de Chaval "));
        delegas.add(new Delegacia("Delegacia Municipal de Chorozinho "));
        delegas.add(new Delegacia("Delegacia Municipal de Coreau "));
        delegas.add(new Delegacia("Delegacia Regional de Crateus "));
        delegas.add(new Delegacia("Delegacia Regional de Crato "));
        delegas.add(new Delegacia("Delegacia Municipal de Barroquinha "));
        delegas.add(new Delegacia("Delegacia Municipal de Cruz "));
        delegas.add(new Delegacia("Delegacia Municipal de Erere "));
        delegas.add(new Delegacia("Delegacia Municipal de Farias Brito "));
        delegas.add(new Delegacia("Delegacia Municipal de Frecheirinha "));
        delegas.add(new Delegacia("Delegacia Municipal de General Sampaio "));
        delegas.add(new Delegacia("Delegacia Municipal de Graca "));
        delegas.add(new Delegacia("Delegacia Municipal de Granja "));
        delegas.add(new Delegacia("Delegacia Municipal de Granjeiro "));
        delegas.add(new Delegacia("Delegacia Municipal de Groairas "));
        delegas.add(new Delegacia("Delegacia Municipal de Guaraciaba do Norte "));
        delegas.add(new Delegacia("Delegacia Municipal de Guaramiranga "));
        delegas.add(new Delegacia("Delegacia Municipal de Hidrolandia "));
        delegas.add(new Delegacia("Delegacia Municipal de Iraucuba "));
        delegas.add(new Delegacia("Delegacia Municipal de Itaicaba "));
        delegas.add(new Delegacia("Delegacia Municipal de Itapaje "));
        delegas.add(new Delegacia("Delegacia Municipal de Itapiuna "));
        delegas.add(new Delegacia("Delegacia Municipal de Itarema "));
        delegas.add(new Delegacia("Delegacia Municipal de Jaguaretama "));
        delegas.add(new Delegacia("Delegacia Municipal de Jaguaribara "));
        delegas.add(new Delegacia("Delegacia Regional de Jaguaribe "));
        delegas.add(new Delegacia("Delegacia Municipal de Jardim "));
        delegas.add(new Delegacia("Delegacia Municipal de Ibiapina "));
        delegas.add(new Delegacia("Delegacia Regional de Ico "));
        delegas.add(new Delegacia("Delegacia Regional de Iguatu "));
        delegas.add(new Delegacia("Delegacia Municipal de Independencia "));
        delegas.add(new Delegacia("Delegacia Municipal de Choro Limao "));
        delegas.add(new Delegacia("Delegacia Municipal de Ipaumirim "));
        delegas.add(new Delegacia("Delegacia Municipal de Ipu "));
        delegas.add(new Delegacia("Delegacia Municipal de Ipueiras "));
        delegas.add(new Delegacia("Delegacia Municipal de Iracema "));
        delegas.add(new Delegacia("Delegacia Municipal de Jati "));
        delegas.add(new Delegacia("Delegacia Regional de Juazeiro do Norte "));
        delegas.add(new Delegacia("Delegacia Municipal de Jucas "));
        delegas.add(new Delegacia("Delegacia Municipal de Lavras da Mangabeira "));
        delegas.add(new Delegacia("Delegacia Municipal de Limoeiro do Norte "));
        delegas.add(new Delegacia("Delegacia Municipal de Marco "));
        delegas.add(new Delegacia("Delegacia Municipal de Madalena "));
        delegas.add(new Delegacia("Delegacia Municipal de Martinopole "));
        delegas.add(new Delegacia("Delegacia Municipal de Massape "));
        delegas.add(new Delegacia("Delegacia Municipal de Mauriti "));
        delegas.add(new Delegacia("Delegacia Municipal de Meruoca "));
        delegas.add(new Delegacia("Delegacia Municipal de Milha "));
        delegas.add(new Delegacia("Delegacia Municipal de Croata "));
        delegas.add(new Delegacia("Delegacia Municipal de Mombaca "));
        delegas.add(new Delegacia("Delegacia Municipal de Monsenhor Tabosa "));
        delegas.add(new Delegacia("Delegacia Municipal de Morada Nova "));
        delegas.add(new Delegacia("Delegacia Municipal de Morrinhos "));
        delegas.add(new Delegacia("Delegacia Municipal de Mucambo "));
        delegas.add(new Delegacia("Delegacia Municipal de Mulungu "));
        delegas.add(new Delegacia("Delegacia Municipal de Nova Russas "));
        delegas.add(new Delegacia("Delegacia Municipal de Novo Oriente "));
        delegas.add(new Delegacia("Delegacia Municipal de Oros "));
        delegas.add(new Delegacia("Delegacia Municipal de Pacajus "));
        delegas.add(new Delegacia("Delegacia Municipal de Pacoti "));
        delegas.add(new Delegacia("Delegacia Municipal de Palhano "));
        delegas.add(new Delegacia("Delegacia Municipal de Palmacia "));
        delegas.add(new Delegacia("Delegacia Municipal de Paracuru "));
        delegas.add(new Delegacia("Delegacia Municipal de Paraipaba "));
        delegas.add(new Delegacia("Delegacia Municipal de Parambu "));
        delegas.add(new Delegacia("Delegacia Municipal de Paramoti "));
        delegas.add(new Delegacia("Delegacia Municipal de Pedra Branca "));
        delegas.add(new Delegacia("Delegacia Municipal de Penaforte "));
        delegas.add(new Delegacia("Delegacia Municipal de Pentecoste "));
        delegas.add(new Delegacia("Delegacia Municipal de Pereiro "));
        delegas.add(new Delegacia("Delegacia Municipal de Ibaretama "));
        delegas.add(new Delegacia("Delegacia Municipal de Piquet Carneiro "));
        delegas.add(new Delegacia("Delegacia Municipal de Pires Ferreira "));
        delegas.add(new Delegacia("Delegacia Municipal de Poranga "));
        delegas.add(new Delegacia("Delegacia Municipal de Porteiras "));
        delegas.add(new Delegacia("Delegacia Municipal de Potengi "));
        delegas.add(new Delegacia("Delegacia Municipal de Quiterianopolis "));
        delegas.add(new Delegacia("Delegacia Municipal de Redencao "));
        delegas.add(new Delegacia("Delegacia Regional de Russas "));
        delegas.add(new Delegacia("Delegacia Municipal de Saboeiro "));
        delegas.add(new Delegacia("Delegacia Municipal de Ibicuitinga "));
        delegas.add(new Delegacia("Delegacia Municipal de Santana do Cariri "));
        delegas.add(new Delegacia("Delegacia Municipal de Santa Quiteria "));
        delegas.add(new Delegacia("Delegacia Municipal de Sao Benedito "));
        delegas.add(new Delegacia("Delegacia Municipal de Sao Joao do Jaguaribe "));
        delegas.add(new Delegacia("Delegacia Municipal de Sao Luis do Curu "));
        delegas.add(new Delegacia("Delegacia Regional de Senador Pompeu "));
        delegas.add(new Delegacia("Delegacia Regional de Sobral "));
        delegas.add(new Delegacia("Delegacia Municipal de Solonopole "));
        delegas.add(new Delegacia("Delegacia Municipal de Tabuleiro do Norte "));
        delegas.add(new Delegacia("Delegacia Municipal de Tamboril "));
        delegas.add(new Delegacia("Delegacia Municipal de Ipaporanga "));
        delegas.add(new Delegacia("Delegacia Regional de Taua "));
        delegas.add(new Delegacia("Delegacia Municipal de Irapuan Pinheiro "));
        delegas.add(new Delegacia("Delegacia Regional de Tiangua "));
        delegas.add(new Delegacia("Delegacia Municipal de Trairi "));
        delegas.add(new Delegacia("Delegacia Municipal de Ubajara "));
        delegas.add(new Delegacia("Delegacia Municipal de Umari "));
        delegas.add(new Delegacia("Delegacia Municipal de Uruburetama "));
        delegas.add(new Delegacia("Delegacia Municipal de Uruoca "));
        delegas.add(new Delegacia("Delegacia Municipal de Varzea Alegre "));
        delegas.add(new Delegacia("Delegacia Municipal de Vicosa do Ceara "));
        delegas.add(new Delegacia("Delegacia Municipal de Miraima "));
        delegas.add(new Delegacia("Delegacia Municipal de Pindoretama "));
        delegas.add(new Delegacia("Delegacia Municipal de Salitre "));
        delegas.add(new Delegacia("Delegacia Municipal de Tarrafas "));
        delegas.add(new Delegacia("Delegacia Municipal de Tejucuoca "));
        delegas.add(new Delegacia("Delegacia Municipal de Ararenda "));
        delegas.add(new Delegacia("Delegacia Municipal de Jijoca "));
        delegas.add(new Delegacia("Delegacia Municipal de Senador Catunda "));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Fortaleza "));
        delegas.add(new Delegacia("Delegacia de Roubos e Furtos de Veiculos e Cargas - Drfvc "));
        delegas.add(new Delegacia("Delegacia de Roubos e Furtos "));
        delegas.add(new Delegacia("Delegacia de Defraudacoes e Falsificacoes "));
        delegas.add(new Delegacia("Delegacia de Capturas e Polinter "));
        delegas.add(new Delegacia("Delegacia de Acidentes e Delitos de Transito "));
        delegas.add(new Delegacia("Delegacia de Policia Maritima Aerea e de Fronteiras "));
        delegas.add(new Delegacia("Delegacia Regional do Trabalho "));
        delegas.add(new Delegacia("Delegacia Municipal de Parnaiba/pi "));
        delegas.add(new Delegacia("Delegacia Municipal de Itapipoca "));
        delegas.add(new Delegacia("Delegacia de Caio Prado "));
        delegas.add(new Delegacia("Delegacia Municipal de Mossoro "));
        delegas.add(new Delegacia("Delegacia de Crim. Contra Administracao e Financas Publicas "));
        delegas.add(new Delegacia("Delegacia Municipal de Quiterianopolis "));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Iguatu "));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Juazeiro "));
        delegas.add(new Delegacia("Delegacia de Policia Federal Em Juazeiro do Norte "));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Crato "));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Sobral "));
        delegas.add(new Delegacia("Delegacia Municipal de Catunda "));
        delegas.add(new Delegacia("Delegacia de Policia Civil de Rafael Godeiro-rn "));
        delegas.add(new Delegacia("Delegacia da Crianca e do Adolescente "));
        delegas.add(new Delegacia("Delegacia Municipal de Rafael Cordeiro/rn "));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Maracanau "));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Caucaia "));
        delegas.add(new Delegacia("10a Delegacia Regional de Policia Civil "));
        delegas.add(new Delegacia("Delegacia de Combate Aos Crimes Contra a Ordem Tributaria "));
        delegas.add(new Delegacia("Delegacia de Crimes Contra Adminastracao e Financas Publicas "));
        delegas.add(new Delegacia("Delegacia de Protecao Ao Turista "));
        delegas.add(new Delegacia("Delegacia Central de Flagrantes "));
        delegas.add(new Delegacia("Delegacia de Repressao a Entorpecentes "));
        //delegas.add(new Delegacia("Delegacia Metropolitana de Maracanau"));
        delegas.add(new Delegacia("Delegacia Metropolitana de Aquiraz "));
        delegas.add(new Delegacia("Delegacia Municipal de Arneiroz "));
        delegas.add(new Delegacia("Delegacia Municipal de Banabuiu "));
        delegas.add(new Delegacia("Delegacia Municipal de Barro "));
        delegas.add(new Delegacia("Delegacia Municipal de Bela Cruz "));
        delegas.add(new Delegacia("Delegacia Municipal de Campos Sales "));
        delegas.add(new Delegacia("Delegacia Municipal de Carire "));
        delegas.add(new Delegacia("Delegacia Municipal de Carnaubal "));
        delegas.add(new Delegacia("Delegacia Municipal de Forquilha "));
        delegas.add(new Delegacia("Delegacia Municipal de Horizonte "));
        delegas.add(new Delegacia("Delegacia Regional de Itapipoca "));
        delegas.add(new Delegacia("Delegacia Municipal de Itatira "));
        delegas.add(new Delegacia("Delegacia Municipal de Jaguaruana "));
        delegas.add(new Delegacia("Delegacia Municipal de Icapui "));
        delegas.add(new Delegacia("Delegacia Municipal de Milagres "));
        delegas.add(new Delegacia("Delegacia Municipal de Missao Velha "));
        delegas.add(new Delegacia("Delegacia Municipal de Moraujo "));
        delegas.add(new Delegacia("Delegacia Municipal de Nova Olinda "));
        delegas.add(new Delegacia("Delegacia Municipal de Fortim "));
        delegas.add(new Delegacia("Delegacia Municipal de Pacuja "));
        delegas.add(new Delegacia("Delegacia Municipal de Potiretama "));
        delegas.add(new Delegacia("Delegacia Municipal de Reriutaba "));
        delegas.add(new Delegacia("Delegacia Municipal de Santana do Acarau "));
        delegas.add(new Delegacia("Delegacia Municipal de Sao G. do Amarante "));
        delegas.add(new Delegacia("Delegacia Municipal de Senador Sa "));
        delegas.add(new Delegacia("Delegacia Municipal de Tururu "));
        delegas.add(new Delegacia("Delegacia Municipal de Umirim "));
        delegas.add(new Delegacia("Delegacia Municipal de Varjota "));
        delegas.add(new Delegacia("Delegacia Municipal de Ocara "));
        delegas.add(new Delegacia("Assessor Tec. da Delegacia Geral da Pol. Civil "));
        delegas.add(new Delegacia("Delegacia de Assuntos Internos "));
        delegas.add(new Delegacia("Gab. Delegado Geral da Policia Civil "));
        delegas.add(new Delegacia("Gabinete do Delegado Superintendente "));
        delegas.add(new Delegacia("Delegacia Municipal de Sobral "));
        delegas.add(new Delegacia("Delegacia Geral "));
        delegas.add(new Delegacia("Delegacia Municipal de Meruoca "));
        delegas.add(new Delegacia("Delegacia Municipal de Sao Goncalo do Amarante "));
        delegas.add(new Delegacia("Delegacia Municipal de Sobral "));
        delegas.add(new Delegacia("Delegacia de Defesa da Mulher de Pacatuba "));
        delegas.add(new Delegacia("Delegacia do Estado do Rio Grande do Norte "));
        delegas.add(new Delegacia("Divisao de Homicidios - 3a Delegacia "));
        delegas.add(new Delegacia("Divisao de Homicidios - 2a Delegacia "));
        delegas.add(new Delegacia("Delegacia Especial de Atendimento a Mulher do Distrito Federal "));
        delegas.add(new Delegacia("Divisão de Homicidios - 1ª Delegacia "));
        delegas.add(new Delegacia("Delegacia de Pedras de Fogo/pb "));
        delegas.add(new Delegacia("Divisao de Homicidios - 4a Delegacia "));
        delegas.add(new Delegacia("Delegacia de Repressao as Acoes Criminosas Organizadas - Draco "));
        delegas.add(new Delegacia("06a Delegacia Regional de Policia de Nova Cruz - Rn "));
        delegas.add(new Delegacia("Delegacia de Policia Federal Em Mossoro "));
        delegas.add(new Delegacia("Delegacia de Narcoticos"));

        //   for(Bairro b : bairros)
        //   b.save();

        for (DadosTerritoriais dt : dadosTerritoriais)
            dt.save();

        for (Cidade c : cidades)
            c.save();

        for (Delegacia de : delegas)
            de.save();


    }
}
