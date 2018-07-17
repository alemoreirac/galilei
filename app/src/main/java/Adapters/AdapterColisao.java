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

import Model.Transito.ColisaoTransito;
import Util.StringUtil;
import ViewHolders.ViewHolderColisao;


public class AdapterColisao extends ArrayAdapter<ColisaoTransito> implements View.OnClickListener
{

//    private ArrayList<ColisaoTransito> dataSet;

    Context mContext;

//    private int lastPosition = -1;

    public AdapterColisao(ArrayList<ColisaoTransito> data, Context context)
    {
        super(context, R.layout.row_ocorrencia,data);
//        this.dataSet = data;
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
//            viewHolder.setImgvAudio((ImageView) convertView.findViewById(R.id.imgv_row_AudioColisao));
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolderColisao) convertView.getTag();


        }

        switch(colisaoTransito.getAtoresColisao())
        {
            case PEDESTRE:
                Picasso.with(convertView.getContext())
                        .load(R.drawable.colisao_pedestre)
                        .into(viewHolder.getImgvTipoInteracao());


                if(colisaoTransito.getPedestre() != null)
                    viewHolder.getTxvAtor2().setText(StringUtil.checkValue(colisaoTransito.getPedestre().getNome(),17,"(Sem nome)"));
                else
                    viewHolder.getTxvAtor2().setText("(Sem Nome)");

                break;
            case VEICULO:
                Picasso.with(convertView.getContext())
                        .load(R.drawable.colisao_veiculos)
                        .into(viewHolder.getImgvTipoInteracao());

                if(colisaoTransito.getVeiculo2() != null)
                viewHolder.getTxvAtor2().setText(StringUtil.checkValue(colisaoTransito.getVeiculo2().toString(),17,"(Não descrito)"));
                break;
            case ANIMAL:
                Picasso.with(convertView.getContext())
                        .load(R.drawable.colisao_animal)
                        .into(viewHolder.getImgvTipoInteracao());

                    viewHolder.getTxvAtor2().setText(StringUtil.checkValue(colisaoTransito.getAnimalDescricao(),17,"(Não descrito)"));



                break;
            case OBJETO:
                Picasso.with(convertView.getContext())
                        .load(R.drawable.colisao_objeto)
                        .into(viewHolder.getImgvTipoInteracao());

                    viewHolder.getTxvAtor2().setText(StringUtil.checkValue(colisaoTransito.getObjetoDescricao(),17,"(Não descrito)"));

                break;
            case NENHUM:
                Picasso.with(convertView.getContext())
                        .load(R.drawable.colisao_adernamento)
                        .into(viewHolder.getImgvTipoInteracao());
                viewHolder.getTxvAtor2().setText("");
                break;
        }

//        if(colisaoTransito.getGravacaoObservacoes() != null)
//        {
//            Picasso.with(convertView.getContext())
//                    .load(R.drawable.microfone)
//                    .into(viewHolder.getImgvAudio());
//        }
//        else
//        {
//            Picasso.with(convertView.getContext())
//                    .load(R.drawable.microfone_apagado)
//                    .into(viewHolder.getImgvAudio());
//        }

        if(colisaoTransito.getVeiculo1() != null)
        viewHolder.getTxvAtor1().setText(colisaoTransito.getVeiculo1().toString() + " "+ colisaoTransito.getOrdemAcontecimento());

        if(colisaoTransito.getTipoInteracao() != null)
        viewHolder.getTxvTipoInteracao().setText(colisaoTransito.getTipoInteracao().getValor());

        return convertView;

    }
}
