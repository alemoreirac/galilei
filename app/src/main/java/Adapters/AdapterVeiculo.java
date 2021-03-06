package Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;

import java.util.ArrayList;

import Model.Transito.Veiculo;
import ViewHolders.ViewHolderVeiculo;

/**
 * Created by Pefoce on 19/06/2017.
 */

public class AdapterVeiculo extends ArrayAdapter<Veiculo> implements View.OnClickListener
{
    private ArrayList<Veiculo> dataSet;

    Context mContext;


    public AdapterVeiculo(ArrayList<Veiculo> data, Context context)
    {
        super(context, R.layout.row_veiculo, data);
        this.mContext = context;
        this.dataSet = data;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        Veiculo veiculo = getItem(position);
        ViewHolderVeiculo viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolderVeiculo();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_veiculo, parent, false);

            viewHolder.setTxvModelo((TextView) convertView.findViewById(R.id.txv_row_Modelo));
            viewHolder.setTxvPlaca((TextView) convertView.findViewById(R.id.txv_row_Placa));
            viewHolder.setTxvTipo((TextView) convertView.findViewById(R.id.txv_row_TipoVeiculo));

            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolderVeiculo) convertView.getTag();
        }

        if (!veiculo.getModelo().equals(""))
            viewHolder.getTxvModelo().setText(veiculo.getModelo());
        else
            viewHolder.getTxvModelo().setText("(Sem modelo)");

        if (!veiculo.getPlaca().equals("-"))
            viewHolder.getTxvPlaca().setText(veiculo.getPlaca());
        else
            viewHolder.getTxvPlaca().setText("(Sem placa) ");

        if (veiculo.getTipoVeiculo() != null)
            viewHolder.getTxvTipo().setText(veiculo.getTipoVeiculo().getValor());

        else
            viewHolder.getTxvTipo().setText("(Veículo indefinido)");

        return convertView;

    }

    @Override
    public void onClick(View v)
    {

    }
}
