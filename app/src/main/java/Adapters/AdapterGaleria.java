package Adapters;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;

import java.util.ArrayList;

import Util.ImageItem;
import ViewHolders.ViewHolderGaleria;

public class AdapterGaleria extends ArrayAdapter {

    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public AdapterGaleria(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolderGaleria holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolderGaleria();
            holder.setImageTitle((TextView) row.findViewById(R.id.text));

            holder.setImage((ImageView) row.findViewById(R.id.image));
            row.setTag(holder);
        } else {
            holder = (ViewHolderGaleria) row.getTag();
        }

        ImageItem item = (ImageItem)data.get(position);
        holder.getImage().setImageBitmap(item.getImage());


        return row;
    }




}