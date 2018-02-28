package Adapters;

/**
 * Created by Pefoce on 23/01/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;

import java.util.ArrayList;

import Model.Vida.VestigioVida;

import ViewHolders.ViewHolderVestigioVida;


/**
 * Created by Pefoce on 19/06/2017.
 */

public class AdapterVestigio extends ArrayAdapter<VestigioVida> implements View.OnClickListener
{
    private ArrayList<VestigioVida> dataSet;

    Context mContext;

    private int lastPosition = -1;


    public AdapterVestigio(ArrayList<VestigioVida> data, Context context)
    {
        super(context, R.layout.row_vestigio_vida, data);
        this.dataSet = data;
        this.mContext = context;
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        VestigioVida vestigioVida = getItem(position);
        ViewHolderVestigioVida viewHolder;

        if (convertView == null)
        {
            viewHolder = new ViewHolderVestigioVida();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_vestigio_vida, parent, false);

            viewHolder.setTxvCategoriaVestigio((TextView) convertView.findViewById(R.id.txv_row_Categoria_Vestigio));
            viewHolder.setTxvTipoVestigio((TextView) convertView.findViewById(R.id.txv_row_Tipo_Vestigio));
            viewHolder.setTxvObservacaoVestigio((TextView) convertView.findViewById(R.id.txv_row_Observacao_Vestigio));
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolderVestigioVida) convertView.getTag();
        }

        lastPosition = position;

        if (vestigioVida.getTipoVestigio() != null)
            viewHolder.getTxvCategoriaVestigio().setText(vestigioVida.getTipoVestigio().getValor());
        else
            viewHolder.getTxvCategoriaVestigio().setText("(Sem categoria definida)");

        if (vestigioVida.getObservacao() != null)
            viewHolder.getTxvObservacaoVestigio().setText(vestigioVida.getObservacao());
        else
            viewHolder.getTxvObservacaoVestigio().setText("(Sem observações)");

        //VESTÍGIO PAPILOSCÓPICO
        if (vestigioVida.getTipoDocumento() == null &&
                vestigioVida.getTiposVestigioBiologico() == null &&
                vestigioVida.getTipoArma() == null &&
                vestigioVida.getTipoRecolhimentoAmostraPapiloscopia() != null &&
                vestigioVida.getCalibreMunicao()== null)
            viewHolder.getTxvTipoVestigio().setText(vestigioVida.getTipoRecolhimentoAmostraPapiloscopia().getValor());

        //VESTÍGIO BALÍSTICO
        if (vestigioVida.getTipoDocumento() == null &&
                vestigioVida.getTiposVestigioBiologico() == null &&
                vestigioVida.getTipoArma() != null &&
                vestigioVida.getTipoRecolhimentoAmostraPapiloscopia() == null &&
                vestigioVida.getCalibreMunicao() == null)

            viewHolder.getTxvTipoVestigio().setText(vestigioVida.getTipoArma().getValor());

        //VESTÍGIO BIOLÓGICO
        if (vestigioVida.getTipoDocumento() == null &&
                vestigioVida.getTiposVestigioBiologico() != null &&
                vestigioVida.getTipoArma() == null &&
                vestigioVida.getTipoRecolhimentoAmostraPapiloscopia() == null &&
                vestigioVida.getCalibreMunicao() == null)

            viewHolder.getTxvTipoVestigio().setText(vestigioVida.getTiposVestigioBiologico().getValor());

        //VESTÍGIO DE DOCUMENTO

        if (vestigioVida.getTipoDocumento() != null &&
                vestigioVida.getTiposVestigioBiologico() == null &&
                vestigioVida.getTipoArma() == null &&
                vestigioVida.getTipoRecolhimentoAmostraPapiloscopia() == null &&
                vestigioVida.getCalibreMunicao() == null)

            viewHolder.getTxvTipoVestigio().setText(vestigioVida.getTipoDocumento().getValor());

        //VESTÍGIO MUNIÇÂO

        if (vestigioVida.getTipoDocumento() == null &&
                vestigioVida.getTiposVestigioBiologico() == null &&
                vestigioVida.getTipoArma() == null &&
                vestigioVida.getTipoRecolhimentoAmostraPapiloscopia() == null &&
                vestigioVida.getCalibreMunicao() != null)

            viewHolder.getTxvTipoVestigio().setText(vestigioVida.getCalibreMunicao().getValor());

        else
            viewHolder.getTxvObservacaoVestigio().setText("(Sem tipo)");


        return convertView;
    }


    @Override
    public void onClick(View v)
    {

    }


}

