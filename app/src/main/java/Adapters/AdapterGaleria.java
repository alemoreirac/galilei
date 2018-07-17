package Adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;
import com.squareup.picasso.Picasso;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import Enums.CategoriaFoto;
import Model.Foto;
import Util.ImageItem;
import Util.ImageUtil;
import ViewHolders.ViewHolderColisao;
import ViewHolders.ViewHolderGaleria;

public class AdapterGaleria extends ArrayAdapter
{

    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public AdapterGaleria(Context context, int layoutResourceId, ArrayList data)
    {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        ViewHolderGaleria holder;

        if (convertView == null)
        {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_galeria, parent, false);
            holder = new ViewHolderGaleria();

            holder.setTxvTitulo((TextView) convertView.findViewById(R.id.txv_row_Imagem_Descricao));
            holder.setImgvFoto((ImageView) convertView.findViewById(R.id.imgv_row_Imagem_Galeria));
            convertView.setTag(holder);
        } else
        {
            holder = (ViewHolderGaleria) convertView.getTag();
        }

        Foto foto = (Foto) data.get(position);

        if (foto.getArquivo() != null)
            Picasso.with(getContext()).load(new File(foto.getArquivo())).resize(50, 50).placeholder(R.drawable.placeholder).into(holder.getImgvFoto());
        else
            Picasso.with(getContext()).load(R.drawable.placeholder_error).resize(50, 50)
                    .into(holder.getImgvFoto());


        if (foto.getCategoriaFoto() == null || foto.getDescricao() == null)
        {
            holder.getTxvTitulo().setText("(Foto sem descrição)");
            return convertView;
        }
        else
        {
            if(!foto.getCategoriaFoto().getValor().equals(CategoriaFoto.DESENHO.getValor()))
                holder.getTxvTitulo().setText(foto.getCategoriaFoto().getValor() + " - " + foto.getDescricao());
            else
                holder.getTxvTitulo().setText("Mapa das lesões");
        }

        return convertView;
    }


}