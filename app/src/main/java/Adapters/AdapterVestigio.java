package Adapters;

/**
 * Created by Pefoce on 23/01/2018.
 */

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

import Model.Vida.VestigioVida;

import Util.StringUtil;
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
            viewHolder.setImgvTipoVestigio((ImageView) convertView.findViewById(R.id.imgv_Vestigio));
            convertView.setTag(viewHolder);
        } else
        {
            viewHolder = (ViewHolderVestigioVida) convertView.getTag();
        }

        lastPosition = position;

        if (vestigioVida.getTipoVestigio() != null)
        {
            viewHolder.getTxvCategoriaVestigio().setText(vestigioVida.getTipoVestigio().getValor());

            switch (vestigioVida.getTipoVestigio())
            {
                case ARMA_DE_FOGO:
                    Picasso.with(convertView.getContext()).load(R.drawable.vestigio_balistico).into(viewHolder.getImgvTipoVestigio());
                    viewHolder.getTxvTipoVestigio().setText(vestigioVida.getTipoArma().getValor());

                    if (StringUtil.isNotNullAndEmpty(vestigioVida.getNumeracaoArma()))
                        viewHolder.getTxvObservacaoVestigio().setText(vestigioVida.getNumeracaoArma());
                    else
                        viewHolder.getTxvObservacaoVestigio().setText("(Sem numeração)");

                    break;

                case BIOLOGICO:
                    Picasso.with(convertView.getContext()).load(R.drawable.vestigio_biologico).into(viewHolder.getImgvTipoVestigio());

                    if (vestigioVida.getTiposVestigioBiologico() != null)
                        viewHolder.getTxvTipoVestigio().setText(vestigioVida.getTiposVestigioBiologico().getValor());

                    if (vestigioVida.getTipoRecolhimentoAmostraBiologica() != null)
                        viewHolder.getTxvObservacaoVestigio().setText(vestigioVida.getTipoRecolhimentoAmostraBiologica().getValor());
                    break;

                case DOCUMENTO:
                    Picasso.with(convertView.getContext()).load(R.drawable.vestigio_documento).into(viewHolder.getImgvTipoVestigio());

                    if (vestigioVida.getTipoDocumento() != null)
                        viewHolder.getTxvTipoVestigio().setText(vestigioVida.getTipoDocumento().getValor());

//                    if(vestigioVida.getNumDocumento()==null && vestigioVida.getNumDocumento().isEmpty())
//                        viewHolder.gettxv


                    if (StringUtil.isNotNullAndEmpty(vestigioVida.getNumDocumento()))
                        viewHolder.getTxvObservacaoVestigio().setText(vestigioVida.getNumDocumento());
                    else
                        viewHolder.getTxvObservacaoVestigio().setText("(Sem numeração)");
                    break;

                case MUNICAO:
                    Picasso.with(convertView.getContext()).load(R.drawable.vestigio_municao).into(viewHolder.getImgvTipoVestigio());

                    if (vestigioVida.getCalibreMunicao() != null)
                        viewHolder.getTxvTipoVestigio().setText(vestigioVida.getCalibreMunicao().getValor());

                    if (vestigioVida.getTipoMunicao() != null && vestigioVida.getQuantidadeMunicao() > 0)
                        viewHolder.getTxvObservacaoVestigio().setText(vestigioVida.getTipoMunicao().getValor() + " qtde: " + vestigioVida.getQuantidadeMunicao());
                    break;

                case PAPILOSCOPICO:
                    Picasso.with(convertView.getContext()).load(R.drawable.vestigio_papiloscopico).into(viewHolder.getImgvTipoVestigio());

                    if (StringUtil.isNotNullAndEmpty(vestigioVida.getObjetoRecolhidoPapiloscopia()))

                        viewHolder.getTxvTipoVestigio().setText(vestigioVida.getObjetoRecolhidoPapiloscopia());
                    else
                        viewHolder.getTxvTipoVestigio().setText("(Objeto não identificado)");

                    if (vestigioVida.getTipoRecolhimentoAmostraPapiloscopia() != null)
                        viewHolder.getTxvObservacaoVestigio().setText(vestigioVida.getTipoRecolhimentoAmostraPapiloscopia().getValor());

                    break;

                case OUTRO:
                    Picasso.with(convertView.getContext()).load(R.drawable.vestigio_outro).into(viewHolder.getImgvTipoVestigio());
                    if (StringUtil.isNotNullAndEmpty(vestigioVida.getObjetoRecolhido()))

                        viewHolder.getTxvTipoVestigio().setText(vestigioVida.getObjetoRecolhido());
                    else
                        viewHolder.getTxvTipoVestigio().setText("(Objeto não identificado)");

                    if (StringUtil.isNotNullAndEmpty(vestigioVida.getObservacao()))
                        viewHolder.getTxvObservacaoVestigio().setText(vestigioVida.getObservacao());
                    else
                        viewHolder.getTxvObservacaoVestigio().setText("(Sem observações)");

                    break;
            }
        }

        return convertView;
    }


    @Override
    public void onClick(View v)
    {

    }


}

