package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;

import java.util.ArrayList;

import Enums.SetorDano;
import Enums.TercoDano;
import Model.Dano;
import ViewHolders.ViewHolderDano;

import static Enums.SetorDano.ANTERIOR_DIREITO;

/**
 * Created by Pefoce on 19/06/2017.
 */

public class AdapterDano extends ArrayAdapter<Dano> implements View.OnClickListener
{
    private ArrayList<Dano> dataSet;

    Context mContext;

    private int lastPosition = -1;



    public AdapterDano(ArrayList<Dano> data,Context context)
    {
        super(context, R.layout.row_dano,data);
        this.dataSet = data;
        this.mContext = context;
    }


    public View getView(int position,View convertView,ViewGroup parent)
    {
        Dano dano = getItem(position);
        ViewHolderDano viewHolder;

        if(convertView == null)
        {
            viewHolder = new ViewHolderDano();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_dano,parent,false);

            viewHolder.setTxvCompatibilidade ((TextView)convertView.findViewById(R.id.txv_row_Compatibilidade));

            viewHolder.setTxvTipoDano ((TextView) convertView.findViewById(R.id.txv_row_TipoDano));
            viewHolder.setTxvTercoDano((TextView) convertView.findViewById(R.id.txv_row_TercoDano));
            viewHolder.setTxvSetorDano((TextView) convertView.findViewById(R.id.txv_SetorDano));
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolderDano) convertView.getTag();
        }

        lastPosition = position;

        if(dano.getCompatibilidade())
            viewHolder.getTxvCompatibilidade().setText("Dano Compatível");
        else
            viewHolder.getTxvCompatibilidade().setText("Dano Não Compatível");

        viewHolder.getTxvTercoDano().setText(dano.getTerco().getValor());

        viewHolder.getTxvSetorDano().setText(dano.getSetor().getValor());

        viewHolder.getTxvTipoDano().setText(dano.getTipo().getValor());

        return convertView;
    }



    @Override
    public void onClick(View v)
    {

    }


}
