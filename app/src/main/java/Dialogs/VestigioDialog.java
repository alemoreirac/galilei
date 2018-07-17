package Dialogs;

/**
 * Created by Pefoce on 16/10/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.pefoce.peritolocal.ManterPericiaTransito;
import com.example.pefoce.peritolocal.R;

import java.util.ArrayList;
import java.util.List;

import Enums.Transito.TipoVestigioTransito;
import Fragments.FragmentsTransito.GerenciarColisoesTransito;
import Model.Transito.ColisaoTransito;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransito;
import Model.Transito.VestigioTransito;
import Model.Transito.VestigioColisao;
import Util.BuscadorEnum;


/**
 * Created by Pefoce on 21/08/2017.
 */

public class VestigioDialog //extends  android.support.v4.app.DialogFragment
{
  static Dialog dialog;
  static ColisaoTransito colisaoTransito;
  static Activity activity = null;
  static Spinner spnTIpoVestigio;
  static Button btnLimparVestigio, btnSalvarVestigio;
  static Button btnAddVestigio;
  static ListView lstvVestigios;
  static EditText edtAreaVestigio, edtDistanciaVestigio;
  static OcorrenciaTransito ocorrenciaTransito;
  static ArrayAdapter<VestigioTransito> adapterVestigio;
  static ArrayList<VestigioTransito> vestigioModel;
  static List<VestigioColisao> vestigioColisaoModel;
  static CheckBox cxbDeterminante;
  static Fragment fragment;

    public static void show(Fragment f, Context ctx)
    {
        fragment = f;

        colisaoTransito = ((GerenciarColisoesTransito)fragment).colisaoTransito;
        ocorrenciaTransito = ((ManterPericiaTransito)activity).ocorrenciaTransito;

        dialog = new Dialog(ctx);
        dialog.setContentView(R.layout.dialog_vestigios);
        dialog.setTitle("Vestígios");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        AssociarLayout();
        AssociarEventos();
        CarregarVestigios();
    }



    private static void AssociarLayout()
    {
        btnAddVestigio = (Button) dialog.findViewById(R.id.btn_dialog_AddVestigio);
        btnLimparVestigio = (Button) dialog.findViewById(R.id.btn_dialog_LimparVestigios);
        btnSalvarVestigio = (Button) dialog.findViewById(R.id.btn_dialog_SalvarVestigio_Detalhe);
        edtDistanciaVestigio = (EditText) dialog.findViewById(R.id.edt_dialog_Distancia_Vestigio);
        edtAreaVestigio = (EditText) dialog.findViewById(R.id.edt_dialog_Area_Vestigio);
        spnTIpoVestigio = (Spinner) dialog.findViewById(R.id.spn_dialog_Tipo_Vestigio);
        lstvVestigios = (ListView) dialog.findViewById(R.id.lstv_dialog_Vestigios);
        cxbDeterminante = (CheckBox) dialog.findViewById(R.id.cxb_dialog_Determinante);


        ArrayList<String> tiposVestigio = new ArrayList<>();

        for (TipoVestigioTransito tipoVestigio : TipoVestigioTransito.values())
            tiposVestigio.add(tipoVestigio.getValor());

        spnTIpoVestigio.setAdapter(new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, tiposVestigio));

    }

    private static void CarregarVestigios()
    {
        vestigioColisaoModel = VestigioColisao.find(VestigioColisao.class, "colisao_transito = ?", colisaoTransito.getId().toString());


        if(vestigioColisaoModel==null)
            vestigioColisaoModel = new ArrayList<>();

        vestigioModel = new ArrayList<VestigioTransito>();

        for (VestigioColisao vc : vestigioColisaoModel)
            vestigioModel.add(VestigioTransito.findById(VestigioTransito.class,vc.getVestigioId()));

        adapterVestigio = new ArrayAdapter<VestigioTransito>(activity, android.R.layout.simple_spinner_dropdown_item, vestigioModel);

        lstvVestigios.setAdapter(adapterVestigio);
    }

    private static void AssociarEventos()
    {

        btnAddVestigio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                VestigioTransito vestigio = new VestigioTransito();

                try
                {
                    if (edtAreaVestigio.getText().toString().length() > 0)
                        vestigio.setArea(Float.valueOf(edtAreaVestigio.getText().toString() ));
                } catch (Exception e)
                {
                    vestigio.setArea(0f);

                }
                try
                {
                    if (edtDistanciaVestigio.getText().toString().length() > 0)
                        vestigio.setDistancia(Float.valueOf(edtDistanciaVestigio.getText().toString()));
                } catch (Exception e)
                {
                    vestigio.setDistancia(0f);
                }
                vestigio.setDeterminante(cxbDeterminante.isChecked());

                vestigio.setTipoVestigio(BuscadorEnum.BuscarTipoVestigio(spnTIpoVestigio.getSelectedItem().toString()));

                adapterVestigio.add(vestigio);
            }

            ;
        });

        btnLimparVestigio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                adapterVestigio.clear();
            }
        });


        btnSalvarVestigio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (colisaoTransito == null)
                    dialog.dismiss();
                VestigioTransito vestigioDelete;
                for (VestigioColisao vc : vestigioColisaoModel)
                {
                    if(vc.getVestigioId()!=null)
                    //vc.getVestigio().delete();
                    {
                        vestigioDelete = VestigioTransito.findById(VestigioTransito.class, vc.getVestigioId());

                        if(vestigioDelete!=null)
                            vestigioDelete.delete();
                    }
                    vc.delete();
                }

                for (int i = 0; i < adapterVestigio.getCount(); i++)
                {
                    VestigioColisao vestigioColisao ;
                    VestigioTransito vestigio = new VestigioTransito();
                    vestigio.setArea(adapterVestigio.getItem(i).getArea());
                    vestigio.setDistancia(adapterVestigio.getItem(i).getDistancia());
                    vestigio.setDeterminante(adapterVestigio.getItem(i).isDeterminante());
                    vestigio.setTipoVestigio(adapterVestigio.getItem(i).getTipoVestigio());
                    vestigio.save();
                    vestigioColisao = new VestigioColisao(vestigio.getId(), colisaoTransito);
                    vestigio.save();
                    vestigioColisao.save();
                }

                dialog.dismiss();
            }
        });

        spnTIpoVestigio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                if (spnTIpoVestigio.getSelectedItem() == TipoVestigioTransito.DERRAPAGEM.getValor()
                        || spnTIpoVestigio.getSelectedItem() == TipoVestigioTransito.DERRAPAGEM_CURVA.getValor()
                        || spnTIpoVestigio.getSelectedItem() == TipoVestigioTransito.FRENAGEM.getValor()
                        || spnTIpoVestigio.getSelectedItem() == TipoVestigioTransito.SULCAGEM.getValor())
                {
                    edtDistanciaVestigio.setEnabled(true);
                    edtAreaVestigio.setEnabled(false);
                }
                if (spnTIpoVestigio.getSelectedItem() == TipoVestigioTransito.SANGUE.getValor())
                {
                    edtDistanciaVestigio.setEnabled(true);
                    edtAreaVestigio.setEnabled(true);
                }
                if (spnTIpoVestigio.getSelectedItem() == TipoVestigioTransito.FRAGMENTOS_VITREOS.getValor()
                        || spnTIpoVestigio.getSelectedItem() == TipoVestigioTransito.FRAGMENTOS_METALICOS.getValor())
                {
                    edtDistanciaVestigio.setEnabled(false);
                    edtAreaVestigio.setEnabled(true);
                }
                edtAreaVestigio.setText("");
                edtDistanciaVestigio.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });
        lstvVestigios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View v, final int position, long id)
            {
                new AlertDialog.Builder(activity)
                        .setTitle("Deletar Dano")
                        .setMessage("Você deseja deletar este Dano?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                       //         VestigioColisao vc = VestigioColisao.find(VestigioColisao.class,"vestigio = ?",adapterVestigio.getItem(position).getId().toString()).get(0);
                       //         vc.getVestigio().delete();
                       //         vc.delete();
                                //vestigioModel.remove(adapterVestigio.getItem(position));
                                adapterVestigio.remove(adapterVestigio.getItem(position));
                                //  adapterVestigio.remove(adapterVestigio.getItem(position));
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        }).show();

                return true;
            }
        });


    }



}