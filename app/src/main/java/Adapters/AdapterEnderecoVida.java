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
import Model.Vida.EnderecoVida;
import Util.StringUtil;
import ViewHolders.ViewHolderEndereco;

/**
 * Created by Pefoce on 28/06/2017.
 */

public class AdapterEnderecoVida extends ArrayAdapter<EnderecoVida> implements View.OnClickListener{

    private ArrayList<EnderecoVida> dataSet;

    Context mContext;

    public AdapterEnderecoVida(ArrayList<EnderecoVida> data, Context context)
    {
        super(context, R.layout.row_endereco,data);
        this.dataSet = data;
        this.mContext = context;
    }


    @Override
    public void onClick(View v)
    {
//        int position = (Integer) v.getTag();
    }
    public View getView(int position,View convertView,ViewGroup parent)
    {
        EnderecoVida enderecoVida= getItem(position);
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

        if(enderecoVida.getTipoVia()!=null)
            viewHolder.getTipoVia().setText(enderecoVida.getTipoVia().getValor());

            viewHolder.getEndereco().setText(StringUtil.checkValue(enderecoVida.getDescricaoEndereco(),15,"(Sem Endereço)"));


            //utilizando o getBairro apenas para não utilizar outro viewholder

                if(enderecoVida.getTipoLocalCrime()!=null)
                {
                    String texto = enderecoVida.getTipoLocalCrime().getValor()+ " ";
                    switch (enderecoVida.getTipoLocalCrime())
                    {
                        case VIA_PUBLICA:
                            if(enderecoVida.getPosicaoVia()!=null)
                           texto += enderecoVida.getPosicaoVia().getValor();
                            break;

                        case PRAIA:
                            if(enderecoVida.getLocalPraia()!=null)
                            texto += enderecoVida.getLocalPraia().getValor();
                            break;

                        case RESIDENCIAL:
                            if(enderecoVida.getComodo()!=null)
                                texto += enderecoVida.getComodo().getValor();
                            break;

                        case RURAL:
                            if(enderecoVida.getLocalAberto()!=null)
                            texto += enderecoVida.getLocalAberto().getValor();
                            break;
                        case OUTRO:
                            texto += enderecoVida.getObservacao();
                                    break;
                    }

                    viewHolder.getBairro().setText(texto);
                }

//                viewHolder.getBairro().setText(enderecoVida.getTipoLocalCrime().getValor());

//                viewHolder.getEndereco().setText(enderecoVida.getDescricaoEndereco());


        return convertView;

    }

}
