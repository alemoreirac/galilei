package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;

import java.util.ArrayList;

import Enums.Transito.LesaoTransito;
import Model.Transito.EnvolvidoTransito;

/**
 * Created by Pefoce on 20/06/2017.
 */

public class AdapterEnvolvidoTransito extends ArrayAdapter<EnvolvidoTransito>  implements View.OnClickListener{


    private ArrayList<EnvolvidoTransito> dataSet;

    Context mContext;


    public AdapterEnvolvidoTransito(ArrayList<EnvolvidoTransito> data, Context context)
    {
        super(context, R.layout.row_envolvido_transito,data);
        this.mContext = context;
        this.dataSet = data;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        EnvolvidoTransito envolvido = getItem(position);


        if(convertView==null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_envolvido_transito,parent,false);
        }

        TextView nome = (TextView) convertView.findViewById(R.id.txv_row_Nome);
        TextView idade = (TextView) convertView.findViewById(R.id.txv_row_Idade);
        TextView fatalidade = (TextView) convertView.findViewById(R.id.txv_row_Fatalidade);


        if(envolvido.getNome() == null || envolvido.getNome().isEmpty()){
            nome.setText("(Sem Nome)");
        } else {
            nome.setText(envolvido.getNome());
        }

        if(envolvido.getLesaoTransito().equals(LesaoTransito.LEVE))
            fatalidade.setText("Lesão Leve");

        if(envolvido.getLesaoTransito().equals(LesaoTransito.GRAVE))
            fatalidade.setText("Lesão Grave");


        if(envolvido.getLesaoTransito().equals(LesaoTransito.FATAL))
            fatalidade.setText("Vítima Fatal");


//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//
//        idade.setText(format.format(envolvido.getNascimento()));
        if(envolvido.getNascimento()!=null)
            idade.setText(envolvido.getNascimentoString());
        else
            idade.setText("--/--/----");

        return convertView;

    }


    @Override
    public void onClick(View v) {

    }


}
