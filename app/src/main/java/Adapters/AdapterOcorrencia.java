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


import java.text.DateFormat;
import java.util.ArrayList;

import Enums.TipoOcorrencia;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransitoEndereco;
import Model.Vida.EnderecoVida;
import ViewHolders.ViewHolderOcorrencia;

/**
 * Created by Pefoce on 12/06/2017.
 */

public class AdapterOcorrencia extends ArrayAdapter<Ocorrencia> implements View.OnClickListener {

    private ArrayList<Ocorrencia> dataSet;

    Context mContext;


    public AdapterOcorrencia(ArrayList<Ocorrencia> data,Context context)
    {
        super(context,R.layout.row_ocorrencia,data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
//        int position = (Integer) v.getTag();
//        Object object = getItem(position);
//        OcorrenciaTransito ocorrencia = (OcorrenciaTransito) object;
//
    }

    private int lastPosition = -1;

    public View getView(int position,View convertView,ViewGroup parent)
    {
        Ocorrencia ocorrencia = getItem(position);
        ViewHolderOcorrencia viewHolder;

        if(convertView == null)
        {
            viewHolder = new ViewHolderOcorrencia();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_ocorrencia,parent,false);

            viewHolder.setTxtNumeroDoc ((TextView) convertView.findViewById(R.id.txv_row_NumIncidencia));
            viewHolder.setTxtEndereco ((TextView) convertView.findViewById(R.id.txv_row_EnderecoOcorrencia));
            viewHolder.setTxtOrigem ((TextView) convertView.findViewById(R.id.txv_row_OrgaoOrigem));
            viewHolder.setTxtDestino ((TextView) convertView.findViewById(R.id.txv_row_OrgaoDestino));
            viewHolder.setImgvTipoOcorrencia((ImageView) convertView.findViewById(R.id.imgv_row_TipoOcorrencia));
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolderOcorrencia) convertView.getTag();
        }

        lastPosition = position;

        if(ocorrencia.getOcorrenciaTransito() != null || ocorrencia.getTipoOcorrencia() == TipoOcorrencia.TRANSITO)
        {
            OcorrenciaTransitoEndereco ote;

            Picasso.with(mContext).load(R.drawable.colisao_veiculos).into(viewHolder.getImgvTipoOcorrencia());
            try
            {
                ote = OcorrenciaTransitoEndereco.find(OcorrenciaTransitoEndereco.class, "ocorrencia_transito = ?", ocorrencia.getOcorrenciaTransito().getId().toString()).get(0);
                viewHolder.getTxtEndereco().setText(ote.getEnderecoTransito().getTipoVia().getValor() +" "+ote.getEnderecoTransito().getDescricaoEndereco()+" "+ ote.getEnderecoTransito().getComplemento());
            }catch (Exception e)
            {
                viewHolder.getTxtEndereco().setText("Sem endereço");
            }

            viewHolder.getTxtNumeroDoc().setText(ocorrencia.getOcorrenciaTransito().getNumIncidencia()+ " - "+ ocorrencia.getOcorrenciaTransito().getDataAtendimentoString());

            if(ocorrencia.getOcorrenciaTransito().getOrgaoOrigem() != null)
                viewHolder.getTxtOrigem().setText(ocorrencia.getOcorrenciaTransito().getOrgaoOrigem());

            if(ocorrencia.getOcorrenciaTransito().getOrgaoDestino() != null)
            {
                if(!ocorrencia.getOcorrenciaTransito().getOrgaoDestino().equals(""))
                viewHolder.getTxtDestino().setText(ocorrencia.getOcorrenciaTransito().getOrgaoDestino());
                else
                    viewHolder.getTxtDestino().setText("(Sem órgão destino)");
            }
        }

        if(ocorrencia.getOcorrenciaVida() != null|| ocorrencia.getTipoOcorrencia() == TipoOcorrencia.VIDA)
        {
            Picasso.with(mContext).load(R.drawable.ocorrencia_vida_icon).into(viewHolder.getImgvTipoOcorrencia());
            try
            {
                EnderecoVida enderecoVida = EnderecoVida.find(EnderecoVida.class, "ocorrencia_id = ?", ocorrencia.getOcorrenciaVida().getId().toString()).get(0);
                viewHolder.getTxtEndereco().setText(enderecoVida.getTipoVia().getValor()+" "+ enderecoVida.getDescricaoEndereco()+" " + enderecoVida.getComplemento());
            }catch (Exception e)
            {
                viewHolder.getTxtEndereco().setText("Sem endereço");
            }

            viewHolder.getTxtNumeroDoc().setText(ocorrencia.getOcorrenciaVida().getNumIncidencia()+ " - "+ ocorrencia.getOcorrenciaVida().getDataAtendimentoString());

            if(ocorrencia.getOcorrenciaVida().getOrgaoOrigem() != null)
                viewHolder.getTxtOrigem().setText(ocorrencia.getOcorrenciaVida().getOrgaoOrigem());

            if(ocorrencia.getOcorrenciaVida().getOrgaoDestino() != null)
                viewHolder.getTxtDestino().setText(ocorrencia.getOcorrenciaVida().getOrgaoDestino());

//            Picasso.with(mContext).

            if(ocorrencia.getOcorrenciaVida().getOrgaoDestino() != null)
            {
                if(!ocorrencia.getOcorrenciaVida().getOrgaoDestino().equals(""))
                    viewHolder.getTxtDestino().setText(ocorrencia.getOcorrenciaVida().getOrgaoDestino());
                else
                    viewHolder.getTxtDestino().setText("(Sem órgão destino)");
            }
        }

        return convertView;
    }
}
