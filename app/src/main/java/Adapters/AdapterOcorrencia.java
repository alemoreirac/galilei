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

import Model.OcorrenciaTransito;
import Model.Veiculo;
import ViewHolders.ViewHolderOcorrencia;

/**
 * Created by Pefoce on 12/06/2017.
 */

public class AdapterOcorrencia extends ArrayAdapter<OcorrenciaTransito> implements View.OnClickListener {

    private ArrayList<OcorrenciaTransito> dataSet;

    Context mContext;


    public AdapterOcorrencia(ArrayList<OcorrenciaTransito> data,Context context)
    {
        super(context,R.layout.row_ocorrencia,data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Object object = getItem(position);
        OcorrenciaTransito ocorrencia = (OcorrenciaTransito) object;

    }

    private int lastPosition = -1;

    public View getView(int position,View convertView,ViewGroup parent)
    {
        OcorrenciaTransito ocorrenciaTransito = getItem(position);
        ViewHolderOcorrencia viewHolder;

        if(convertView == null)
        {
            viewHolder = new ViewHolderOcorrencia();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_ocorrencia,parent,false);

            viewHolder.setTxtTipoDoc((TextView) convertView.findViewById(R.id.txv_TipoDocumento));
            viewHolder.setTxtNumeroDoc ((TextView) convertView.findViewById(R.id.txt_NumeroDocumento));
            viewHolder.setTxtData ((TextView) convertView.findViewById(R.id.txt_DataOcorrencia));
            viewHolder.setTxtPlacas( (TextView) convertView.findViewById(R.id.txt_Placas));
            viewHolder.setTxtEndereco ( (TextView) convertView.findViewById(R.id.txt_Endereco));
            viewHolder.setTxtOrigem ( (TextView) convertView.findViewById(R.id.txt_OrgaoOrigem));
            viewHolder.setTxtDestino ( (TextView) convertView.findViewById(R.id.txt_OrgaoDestino));


            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolderOcorrencia) convertView.getTag();
        }

        lastPosition = position;

        if(ocorrenciaTransito.getDocumentoOcorrencia().getTipodocumento() != null)
        viewHolder.getTxtTipoDoc().setText(ocorrenciaTransito.getDocumentoOcorrencia().getTipodocumento().toString());

        if(ocorrenciaTransito.getDocumentoOcorrencia().getValor() != null)
        viewHolder.getTxtNumeroDoc().setText(ocorrenciaTransito.getDocumentoOcorrencia().getValor());

        String placas = "";
        if(ocorrenciaTransito.getVeiculos() != null)
        {

            for(Veiculo v : ocorrenciaTransito.getVeiculos())
            {
                placas += v.getPlaca();
                placas += ", ";
            }
            placas = placas.substring(0, placas.lastIndexOf(", "));

            viewHolder.getTxtPlacas().setText(placas);
        }
        else
        {
            viewHolder.getTxtPlacas().setText(placas);
        }



        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(mContext);

        viewHolder.getTxtData().setText(dateFormat.getDateInstance().format(ocorrenciaTransito.getDataOcorrencia()));

        if(ocorrenciaTransito.getEnderecos() != null)
            viewHolder.getTxtEndereco().setText(ocorrenciaTransito.getEnderecos().get(0).getEndereco().getDescricao());

        if(ocorrenciaTransito.getOrgaoOrigem() != null)
            viewHolder.getTxtOrigem().setText(ocorrenciaTransito.getOrgaoOrigem());

        if(ocorrenciaTransito.getOrgaoDestino() != null)
            viewHolder.getTxtDestino().setText(ocorrenciaTransito.getOrgaoDestino());

        if(ocorrenciaTransito.getDataAtendimento()!= null)
            viewHolder.getTxtData().setText(ocorrenciaTransito.getDataAtendimentoString());

        return convertView;
    }
}
