package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import Model.ColisaoTransito;
import ViewHolders.ViewHolderColisao;


public class AdapterColisao extends ArrayAdapter<ColisaoTransito> implements View.OnClickListener
{

    private ArrayList<ColisaoTransito> dataSet;

    Context mContext;

    private int lastPosition = -1;

    public AdapterColisao(ArrayList<ColisaoTransito> data, Context context)
    {
        super(context, R.layout.row_ocorrencia,data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v)
    {
    }

    public View getView(int position,View convertView,ViewGroup parent)
    {
        ColisaoTransito colisaoTransito = getItem(position);
        ViewHolderColisao viewHolder;

        if(convertView == null && colisaoTransito != null)
        {
            viewHolder = new ViewHolderColisao();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_colisao,parent,false);

            viewHolder.setTxvAtor1((TextView) convertView.findViewById(R.id.txv_row_Ator1));
            viewHolder.setTxvAtor2((TextView) convertView.findViewById(R.id.txv_row_Ator2));
            viewHolder.setTxvTipoInteracao((TextView) convertView.findViewById(R.id.txv_row_TipoInteracao));
            viewHolder.setImgvTipoInteracao((ImageView) convertView.findViewById(R.id.imgv_row_TipoInteracao));
            viewHolder.setImgvAudio((ImageView) convertView.findViewById(R.id.imgv_row_AudioColisao));
            convertView.setTag(viewHolder);

            switch(colisaoTransito.getAtoresColisao())
            {
                case PEDESTRE:
                    Picasso.with(convertView.getContext())
                            .load(R.drawable.pedestre)
                            .into(viewHolder.getImgvTipoInteracao());
                    break;
                case VEICULO:
                    Picasso.with(convertView.getContext())
                            .load(R.drawable.veiculo_veiculo)
                            .into(viewHolder.getImgvTipoInteracao());
                    if(colisaoTransito.getVeiculo2() != null)
                        viewHolder.getTxvAtor2().setText(colisaoTransito.getVeiculo2().toString());
                    break;
                case ANIMAL:
                    Picasso.with(convertView.getContext())
                            .load(R.drawable.veiculo_animal)
                            .into(viewHolder.getImgvTipoInteracao());
                    break;
                case OBJETO:
                    Picasso.with(convertView.getContext())
                            .load(R.drawable.veiculo_objeto)
                            .into(viewHolder.getImgvTipoInteracao());
                    break;
                case NENHUM:
                    Picasso.with(convertView.getContext())
                            .load(R.drawable.veiculo)
                            .into(viewHolder.getImgvTipoInteracao());
                    viewHolder.getTxvAtor2().setText("");
                    break;
            }

            if(colisaoTransito.getGravacaoObservacoes() != null)
            {
                Picasso.with(convertView.getContext())
                        .load(R.drawable.microfone)
                        .into(viewHolder.getImgvAudio());
            }
            else
            {
                Picasso.with(convertView.getContext())
                        .load(R.drawable.microfone_apagado)
                        .into(viewHolder.getImgvAudio());
            }

        }
        else
        {
            viewHolder = (ViewHolderColisao) convertView.getTag();
        }

        lastPosition = position;
        if(colisaoTransito.getVeiculo1() != null)
        viewHolder.getTxvAtor1().setText(colisaoTransito.getVeiculo1().toString());
        if(colisaoTransito.getTipoInteracao() != null)
        viewHolder.getTxvTipoInteracao().setText(colisaoTransito.getTipoInteracao().getValor());

        return convertView;

    }
}
