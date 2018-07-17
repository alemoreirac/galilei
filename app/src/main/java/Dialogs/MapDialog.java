package Dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import Fragments.FragmentsVida.GerenciarEnderecoVida;
import Util.StringUtil;

/**
 * Created by Pefoce on 28/05/2018.
 */

public class MapDialog extends android.support.v4.app.DialogFragment
{
    //    TextView txvCoords;
    public Activity activity = null;
    GoogleMap gmap;
    Marker marker;
    MapView mMapView;

    Button btnAlterar;
    Button btnCancelar;

    LatLng coords = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dialog_map, container, false);

        WindowManager.LayoutParams wmlp = getDialog().getWindow().getAttributes();
        wmlp.gravity = Gravity.CENTER;

        getDialog().setCanceledOnTouchOutside(false);

        Bundle bd = getArguments();

        activity = getActivity();

        Bundle bundle = getArguments();

        coords = new LatLng(bundle.getDouble("Latitude"), bundle.getDouble("Longitude"));

//        txvCoords = (TextView) dialog.findViewById(R.id.txv_dialog_Coordenadas);

        mMapView = (MapView) view.findViewById(R.id.mpv_dialog_Ponto_Ocorrencia);

        mMapView.onCreate(bd);

        mMapView.onResume();

        AssociarLayout(view);

        try
        {
            MapsInitializer.initialize(activity.getApplicationContext());
        } catch (Exception e)
        {
            e.printStackTrace();
        }


        mMapView.getMapAsync(new OnMapReadyCallback()
        {
            @Override
            public void onMapReady(GoogleMap googleMap)
            {
                gmap = googleMap;

                gmap.setMapType(GoogleMap.MAP_TYPE_NONE);

                MarkerOptions mo = new MarkerOptions();
                mo.draggable(true);

                gmap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener()
                {
                    @Override
                    public void onMarkerDragStart(Marker marker)
                    {

                    }

                    @Override
                    public void onMarkerDrag(Marker marker)
                    {

                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker_)
                    {
                        marker = marker_;
                        String texto = "Lat: " + StringUtil.converterLatitude(marker.getPosition().latitude) +
                                " Long: " + StringUtil.converterLongitude(marker.getPosition().longitude);

                        marker.setTitle(texto);
                        marker.showInfoWindow();


                    }
                });

                //Círculo que marca o local inicial
                CircleOptions co = new CircleOptions();
                co.center(coords);
                co.strokeColor(Color.RED);
                co.radius(1.6d);
                gmap.addCircle(co);


                mo.position(coords);
                marker = gmap.addMarker(mo);

                gmap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener()
                {
                    @Override
                    public void onMapLongClick(LatLng latLng)
                    {
                        marker.setPosition(latLng);
                        String texto = "Lat: " + StringUtil.converterLatitude(marker.getPosition().latitude) +
                                " Long: " + StringUtil.converterLongitude(marker.getPosition().longitude);

                        marker.setTitle(texto);
                        marker.showInfoWindow();

                    }
                });
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(coords, 15);
                gmap.moveCamera(cameraUpdate);
            }
        });
        return view;
    }

    public void AssociarLayout(View v)
    {
        btnCancelar = (Button) v.findViewById(R.id.btn_dialog_Cancelar_Coordenadas);
        btnAlterar = (Button) v.findViewById(R.id.btn_dialog_Alterar_Coordenadas);


        btnAlterar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Bundle bd = new Bundle();
                if (marker != null)
                {
                    bd.putString("Latitude", StringUtil.converterLatitude(marker.getPosition().latitude));
                    bd.putString("Longitude", StringUtil.converterLongitude(marker.getPosition().longitude));
                } else
                {
                    bd.putString("Latitude", StringUtil.converterLatitude(coords.latitude));
                    bd.putString("Longitude", StringUtil.converterLongitude(coords.longitude));
                }
                ((GerenciarEnderecoVida) getTargetFragment()).DialogResult(bd);

                getDialog().dismiss();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getDialog().dismiss();
            }
        });
    }
}
