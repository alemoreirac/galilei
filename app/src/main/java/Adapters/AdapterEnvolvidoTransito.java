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

import Enums.Transito.Lesao;
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

        if(envolvido.getLesao().equals(Lesao.LEVE))
            fatalidade.setText("Lesão Leve");

        if(envolvido.getLesao().equals(Lesao.GRAVE))
            fatalidade.setText("Lesão Grave");


        if(envolvido.getLesao().equals(Lesao.FATAL))
            fatalidade.setText("Vítima Fatal");


        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        idade.setText(format.format(envolvido.getNascimento()));
        return convertView;

      // if(convertView==null)
      // {
      //     viewHolder = new ViewHolderEnvolvidoTransito();

      //     LayoutInflater inflater = LayoutInflater.from(getContext());

      //     if(lastAdded == position)
      //     {
      //         convertView = inflater.inflate(R.layout.row_envolvido_selecionado,parent,false);
      //     }
      //     else
      //     {
      //         convertView = inflater.inflate(R.layout.row_envolvido_transito,parent,false);
      //     }
      //     viewHolder.setTxvNome((TextView) convertView.findViewById(R.id.txv_row_Nome));
      //     viewHolder.setTxvIdade((TextView) convertView.findViewById(R.id.txv_row_Idade));
      //     viewHolder.setTxvFatal((TextView) convertView.findViewById(R.id.txv_row_Fatalidade));

      //     convertView.setTag(viewHolder);

      // }
      // else
      // {
      //     viewHolder = (ViewHolderEnvolvidoTransito) convertView.getTag();
      // }

      // lastPosition = position;

      // if(envolvido.getNome().length()== 0)
      //     viewHolder.getTxvNome().setText("(Sem Nome)");
      // else
      //     viewHolder.getTxvNome().setText(envolvido.getNome());

      // if(envolvido.isFatal())
      // viewHolder.getTxvFatal().setText("Vítima Fatal");

      // else
      // viewHolder.getTxvFatal().setText("Vítima Não Fatal");


      // SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

      // viewHolder.getTxvIdade().setText(format.format(envolvido.getNascimento()));

      // return convertView;

    }


    @Override
    public void onClick(View v) {

    }


}
