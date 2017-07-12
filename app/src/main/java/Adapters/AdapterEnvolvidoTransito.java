package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;

import java.util.ArrayList;

import Model.EnvolvidoTransito;
import ViewHolders.ViewHolderEnvolvidoTransito;

/**
 * Created by Pefoce on 20/06/2017.
 */

public class AdapterEnvolvidoTransito extends ArrayAdapter<EnvolvidoTransito>  implements View.OnClickListener{


    private ArrayList<EnvolvidoTransito> dataSet;

    Context mContext;

    private int lastPosition = -1;


    public AdapterEnvolvidoTransito(ArrayList<EnvolvidoTransito> data, Context context)
    {
        super(context, R.layout.row_envolvido_transito,data);
        this.mContext = context;
        this.dataSet = data;
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        EnvolvidoTransito envolvido = getItem(position);
        ViewHolderEnvolvidoTransito viewHolder;
        if(convertView==null)
        {
            viewHolder = new ViewHolderEnvolvidoTransito();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_envolvido_transito,parent,false);


            viewHolder.setTxvNome((TextView) convertView.findViewById(R.id.txv_row_Nome));
            viewHolder.setTxvIdade((TextView) convertView.findViewById(R.id.txv_row_Idade));
            viewHolder.setTxvFatal((TextView) convertView.findViewById(R.id.txv_row_Fatalidade));

            convertView.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolderEnvolvidoTransito) convertView.getTag();
        }

        lastPosition = position;

        if(envolvido.isFatal())
        viewHolder.getTxvFatal().setText("Vítima Fatal");
        else
        viewHolder.getTxvFatal().setText("Vítima Não Fatal");


        viewHolder.getTxvIdade().setText(envolvido.getNascimento().toString());



        viewHolder.getTxvNome().setText(envolvido.getNome());

        return convertView;

    }

    @Override
    public void onClick(View v) {

    }
}
