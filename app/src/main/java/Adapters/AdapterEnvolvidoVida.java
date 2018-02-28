package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Model.Vida.EnvolvidoVida;

/**
 * Created by Pefoce on 10/01/2018.
 */

public class AdapterEnvolvidoVida extends ArrayAdapter<EnvolvidoVida>
{
    private ArrayList<EnvolvidoVida> dataSet;
    Context mContext;

    public AdapterEnvolvidoVida(ArrayList<EnvolvidoVida> data, Context context)
    {
        super(context, R.layout.row_envolvido_transito,data);
        this.mContext = context;
        this.dataSet = data;
    }
    public View getView(int position, View convertView, ViewGroup parent)
    {
        EnvolvidoVida envolvidoVida = getItem(position);

        if(convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_envolvido_vida,parent,false);
        }
        TextView txvNome = (TextView) convertView.findViewById(R.id.txv_row_Nome_Envolvido_Vida);
        TextView txvIdade = (TextView) convertView.findViewById(R.id.txv_row_Idade_Envolvido_Vida);
        TextView txvGenero = (TextView) convertView.findViewById(R.id.txv_row_Genero_Envolvido_Vida);

        if(envolvidoVida != null)
        {

            if (envolvidoVida.getNome() == null || envolvidoVida.getNome().isEmpty())
                txvNome.setText("(Sem Nome)");
            else if (envolvidoVida.getNome().length() > 16)
            {
                txvNome.setText(envolvidoVida.getNome().substring(0, 15) + "...");
            } else
                txvNome.setText(envolvidoVida.getNome());

            if (envolvidoVida.getGenero() != null)
                txvGenero.setText(envolvidoVida.getGenero().getValor());

            if (envolvidoVida.getNascimento() != null)
            {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                if (format.format(envolvidoVida.getNascimento()).contains("0002"))
                    txvIdade.setText("(sem data de nascimento)");
                else
                    txvIdade.setText(format.format(envolvidoVida.getNascimento()));
            } else
                txvIdade.setText("(sem data de nascimento)");
        }
        else
            txvNome.setText("(Sem nome)");
        return convertView;
    }
}
