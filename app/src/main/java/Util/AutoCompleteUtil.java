package Util;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import Model.Cidade;
import Model.DadosTerritoriais;
import Model.Delegacia;

/**
 * Created by Pefoce on 16/08/2017.
 */

public class AutoCompleteUtil
{
    public static ArrayAdapter<String> getBairros(Context context)
    {
        ArrayList<DadosTerritoriais> dados = (ArrayList<DadosTerritoriais>) DadosTerritoriais.listAll(DadosTerritoriais.class);

        String[] bairros = new String[dados.size()];

        for(int i = 0; i < dados.size();i++)
            bairros[i] = dados.get(i).getBairro();

        return new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,bairros);
    }

    public static ArrayAdapter<String> getCidades(Context context)
    {
        ArrayList<Cidade> cidades = (ArrayList<Cidade>) Cidade.listAll(Cidade.class);

        String[] cidadesString = new String[cidades.size()];

        for (int i = 0; i<cidades.size();i++)
        {
            cidadesString[i] = cidades.get(i).getDescricao();
        }

        return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, cidadesString);
    }


    public static ArrayAdapter<String> getDelegacias(Context context)
    {
        ArrayList<DadosTerritoriais> dados = (ArrayList<DadosTerritoriais>) DadosTerritoriais.listAll(DadosTerritoriais.class);

        ArrayList<Delegacia> delegacias = (ArrayList<Delegacia>) Delegacia.listAll(Delegacia.class);

        ArrayList<String> arrayDelegacias = new ArrayList<>();

        for (DadosTerritoriais dt : dados)
        {
            if(!arrayDelegacias.contains(dt.getDelegacia()))
            arrayDelegacias.add(dt.getDelegacia());
        }
        for(Delegacia d : delegacias)
        {
            if(!arrayDelegacias.contains(d))
            arrayDelegacias.add(d.getDescricao());
        }


        return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, arrayDelegacias);
    }


}
