package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;


import java.text.DateFormat;
import java.util.ArrayList;

import Enums.TipoOcorrencia;
import Model.Ocorrencia;
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

            viewHolder.setTxtTipoDoc((TextView) convertView.findViewById(R.id.txv_TipoDocumento));
            viewHolder.setTxtNumeroDoc ((TextView) convertView.findViewById(R.id.txt_NumeroDocumento));
            viewHolder.setTxtData ((TextView) convertView.findViewById(R.id.txt_DataOcorrencia));
            viewHolder.setTxtTipoOcorrencia((TextView) convertView.findViewById(R.id.txt_TipoOcorrencia));
            viewHolder.setTxtEndereco ((TextView) convertView.findViewById(R.id.txt_Endereco));
            viewHolder.setTxtOrigem ((TextView) convertView.findViewById(R.id.txt_OrgaoOrigem));
            viewHolder.setTxtDestino ((TextView) convertView.findViewById(R.id.txt_OrgaoDestino));

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolderOcorrencia) convertView.getTag();
        }

        lastPosition = position;

        if(ocorrencia.getOcorrenciaTransito() != null || ocorrencia.getTipoOcorrencia() == TipoOcorrencia.TRANSITO)
        {
            if(ocorrencia.getOcorrenciaTransito().getNumIncidencia() != null)
                viewHolder.getTxtTipoDoc().setText(ocorrencia.getOcorrenciaTransito().getNumIncidencia());

            if(ocorrencia.getTipoOcorrencia() != null)
                viewHolder.getTxtTipoOcorrencia().setText(ocorrencia.getTipoOcorrencia().getValor());


            DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(mContext);

            viewHolder.getTxtData().setText(dateFormat.getDateInstance().format(ocorrencia.getOcorrenciaTransito().getDataChamado()));

            if(ocorrencia.getOcorrenciaTransito().getOrgaoOrigem() != null)
                viewHolder.getTxtOrigem().setText(ocorrencia.getOcorrenciaTransito().getOrgaoOrigem());

            if(ocorrencia.getOcorrenciaTransito().getOrgaoDestino() != null)
                viewHolder.getTxtDestino().setText(ocorrencia.getOcorrenciaTransito().getOrgaoDestino());

            if(ocorrencia.getOcorrenciaTransito().getDataAtendimento()!= null)
                viewHolder.getTxtData().setText(ocorrencia.getOcorrenciaTransito().getDataAtendimentoString());

        }

        if(ocorrencia.getOcorrenciaVida() != null|| ocorrencia.getTipoOcorrencia() == TipoOcorrencia.VIDA)
        {
            if(ocorrencia.getOcorrenciaVida().getNumIncidencia() != null)
                viewHolder.getTxtTipoDoc().setText(ocorrencia.getOcorrenciaVida().getNumIncidencia());

            if(ocorrencia.getTipoOcorrencia() != null)
                viewHolder.getTxtTipoOcorrencia().setText(ocorrencia.getTipoOcorrencia().getValor());


            DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(mContext);

            viewHolder.getTxtData().setText(ocorrencia.getOcorrenciaVida().getDataAtendimentoString());

            if(ocorrencia.getOcorrenciaVida().getOrgaoOrigem() != null)
                viewHolder.getTxtOrigem().setText(ocorrencia.getOcorrenciaVida().getOrgaoOrigem());

            if(ocorrencia.getOcorrenciaVida().getOrgaoDestino() != null)
                viewHolder.getTxtDestino().setText(ocorrencia.getOcorrenciaVida().getOrgaoDestino());

            if(ocorrencia.getOcorrenciaVida().getDataAtendimento()!= null)
                viewHolder.getTxtData().setText(ocorrencia.getOcorrenciaVida().getDataAtendimentoString());

        }

        return convertView;
    }
}
