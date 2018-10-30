package Util;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Model.Bairro;
import Model.Municipio;
import Model.DadosTerritoriais;
import Model.Delegacia;
import Model.Usuario;

/**
 * Created by Pefoce on 16/08/2017.
 */

public class AutoCompleteUtil
{
    public static ArrayAdapter<String> getBairros(Context context)
    {
        ArrayList<Bairro> dados = (ArrayList<Bairro>) Bairro.listAll(Bairro.class);

        String[] bairros = new String[dados.size()];

        for(int i = 0; i < dados.size();i++)
        {
            if(dados.get(i)!=null)
            bairros[i] = dados.get(i).getDescricao();
        }
        return new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,bairros);
    }

    public static ArrayAdapter<String>getEmails(Context context)
    {
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) Usuario.listAll(Usuario.class);

        String[] emails = new String[usuarios.size()];

        for(int i = 0; i < usuarios.size(); i++)
            emails[i] = usuarios.get(i).getEmail();

        return new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line,emails);
    }

    public static ArrayAdapter<String> getCidades(Context context)
    {
        ArrayList<Municipio> municipios = (ArrayList<Municipio>) Municipio.listAll(Municipio.class);

        String[] cidadesString = new String[municipios.size()];

        for (int i = 0; i< municipios.size(); i++)
        {
            if(municipios.get(i)!=null)
            cidadesString[i] = municipios.get(i).getDescricao();
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
            arrayDelegacias.add(dt.getDelegacia().getDescricao());
        }
        for(Delegacia d : delegacias)
        {
            if(!arrayDelegacias.contains(d))
            arrayDelegacias.add(d.getDescricao());
        }

        Set<String> hs = new HashSet<>();
        hs.addAll(arrayDelegacias);
        arrayDelegacias.clear();
        arrayDelegacias.addAll(hs);

        return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, arrayDelegacias);
    }
}
