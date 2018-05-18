package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;

import java.util.ArrayList;

import Model.Transito.EnderecoTransito;
import ViewHolders.ViewHolderEndereco;

/**
 * Created by Pefoce on 28/06/2017.
 */

public class AdapterEndereco extends ArrayAdapter<EnderecoTransito> implements View.OnClickListener{

    private ArrayList<EnderecoTransito> dataSet;

    Context mContext;

    public AdapterEndereco(ArrayList<EnderecoTransito> data, Context context)
    {
        super(context, R.layout.row_ocorrencia,data);
        this.dataSet = data;
        this.mContext = context;
    }


    @Override
    public void onClick(View v) {
//        int position = (Integer) v.getTag();

    }


    public View getView(int position,View convertView,ViewGroup parent)
    {
        EnderecoTransito enderecoTransito = getItem(position);
        ViewHolderEndereco viewHolder;

        if(convertView == null)
        {
            viewHolder = new ViewHolderEndereco();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_endereco,parent,false);

            viewHolder.setEndereco((TextView) convertView.findViewById(R.id.txv_row_Endereco));
            viewHolder.setBairro((TextView) convertView.findViewById(R.id.txv_row_Bairro));
            viewHolder.setTipoVia((TextView) convertView.findViewById(R.id.txv_row_TipoVia));

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolderEndereco) convertView.getTag();
        }


        if(enderecoTransito.getDescricaoEndereco()!= null )
        {
            if(enderecoTransito.getDescricaoEndereco().equals(""))
            {
                viewHolder.getEndereco().setText("(Sem endereço)");
                return convertView;
            }

            else
            {
                if(enderecoTransito.getTipoVia()!=null)
                viewHolder.getTipoVia().setText(enderecoTransito.getTipoVia().getValor());

                viewHolder.getEndereco().setText(enderecoTransito.getDescricaoEndereco());

                if(enderecoTransito.getBairro()!=null)
                viewHolder.getBairro().setText(enderecoTransito.getBairro());
            }
        }



        return convertView;

    }

}
