package Dialogs;

/**
 * Created by Pefoce on 16/10/2017.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.pefoce.peritolocal.ManterPericia;
import com.example.pefoce.peritolocal.R;

import java.util.ArrayList;
import java.util.List;

import Enums.TipoVestigio;
import Model.ColisaoTransito;
import Model.Ocorrencia;
import Model.OcorrenciaTransito;
import Model.Vestigio;
import Model.VestigioColisao;
import Util.BuscadorEnum;


/**
 * Created by Pefoce on 21/08/2017.
 */

public class VestigioDialog extends  android.support.v4.app.DialogFragment
{
    public ColisaoTransito colisaoTransito;
    public Activity activity = null;
    public Spinner spnTIpoVestigio;
    public Button btnLimparVestigio, btnSalvarVestigio;
    public FloatingActionButton btnAddVestigio;
    public ListView lstvVestigios;
    public EditText edtAreaVestigio, edtDistanciaVestigio;
    public OcorrenciaTransito ocorrenciaTransito;
    public Ocorrencia ocorrencia;
    public ArrayAdapter<Vestigio> adapterVestigio;
    public ArrayList<Vestigio> vestigioModel;
    public List<VestigioColisao> vestigioColisaoModel;
    public CheckBox cxbDeterminante;

    public VestigioDialog()
    {
    }

    public static VestigioDialog newInstance(String title, String local)
    {
        VestigioDialog frag = new VestigioDialog();

        Bundle args = new Bundle();

        args.putString("Gravar Áudio", title);
        args.putString("Local", local);

        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dialog_vestigios, container, false);

        Bundle bd = getArguments();

        colisaoTransito = ColisaoTransito.findById(ColisaoTransito.class, bd.getLong("ColisaoID"));

        activity = getActivity();

        ocorrenciaTransito = ((ManterPericia) activity).ocorrenciaTransito;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransito.getOcorrenciaID());

        AssociarLayout(view);
        AssociarEventos();

        CarregarVestigios();
        return view;
    }

    private void AssociarLayout(View view)
    {
        btnAddVestigio = (FloatingActionButton) view.findViewById(R.id.btn_dialog_AddVestigio);
        btnLimparVestigio = (Button) view.findViewById(R.id.btn_dialog_LimparVestigios);
        btnSalvarVestigio = (Button) view.findViewById(R.id.btn_dialog_SalvarVestigio_Detalhe);
        edtDistanciaVestigio = (EditText) view.findViewById(R.id.edt_dialog_Distancia_Vestigio);
        edtAreaVestigio = (EditText) view.findViewById(R.id.edt_dialog_Area_Vestigio);
        spnTIpoVestigio = (Spinner) view.findViewById(R.id.spn_dialog_Tipo_Vestigio);
        lstvVestigios = (ListView) view.findViewById(R.id.lstv_dialog_Vestigios);
        cxbDeterminante = (CheckBox) view.findViewById(R.id.cxb_dialog_Determinante);


        ArrayList<String> tiposVestigio = new ArrayList<>();

        for (TipoVestigio tipoVestigio : TipoVestigio.values())
            tiposVestigio.add(tipoVestigio.getValor());

        spnTIpoVestigio.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, tiposVestigio));

    }

    private void CarregarVestigios()
    {
        vestigioColisaoModel = VestigioColisao.find(VestigioColisao.class, "colisao_transito = ?", colisaoTransito.getId().toString());

        if(vestigioColisaoModel==null)
            vestigioColisaoModel = new ArrayList<>();

        vestigioModel = new ArrayList<Vestigio>();

        for (VestigioColisao vc : vestigioColisaoModel)
            vestigioModel.add(Vestigio.findById(Vestigio.class,vc.getVestigioId()));

        adapterVestigio = new ArrayAdapter<Vestigio>(getActivity(), android.R.layout.simple_spinner_dropdown_item, vestigioModel);

        lstvVestigios.setAdapter(adapterVestigio);


    }

    private void AssociarEventos()
    {

        btnAddVestigio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Vestigio vestigio = new Vestigio();

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
                    dismiss();
                Vestigio vestigioDelete;
                for (VestigioColisao vc : vestigioColisaoModel)
                {
                    if(vc.getVestigioId()!=null)
                    //vc.getVestigio().delete();
                    {
                        vestigioDelete = Vestigio.findById(Vestigio.class, vc.getVestigioId());

                        if(vestigioDelete!=null)
                            vestigioDelete.delete();
                    }
                    vc.delete();
                }

                VestigioColisao vestigioColisao ;

Vestigio vestigio;

                for (int i = 0; i < adapterVestigio.getCount(); i++)
                {

                    vestigio = adapterVestigio.getItem(i);
                    vestigio.save();
                    vestigioColisao = new VestigioColisao(vestigio.getId(), colisaoTransito);
                    vestigio.save();
                    vestigioColisao.save();
                    vestigioColisao.toString();
                }

                dismiss();
            }
        });

        spnTIpoVestigio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                if (spnTIpoVestigio.getSelectedItem() == TipoVestigio.DERRAPAGEM.getValor()
                        || spnTIpoVestigio.getSelectedItem() == TipoVestigio.DERRAPAGEM_CURVA.getValor()
                        || spnTIpoVestigio.getSelectedItem() == TipoVestigio.FRENAGEM.getValor()
                        || spnTIpoVestigio.getSelectedItem() == TipoVestigio.SULCAGEM.getValor())
                {
                    edtDistanciaVestigio.setEnabled(true);
                    edtAreaVestigio.setEnabled(false);
                }
                if (spnTIpoVestigio.getSelectedItem() == TipoVestigio.SANGUE.getValor())
                {
                    edtDistanciaVestigio.setEnabled(true);
                    edtAreaVestigio.setEnabled(true);
                }
                if (spnTIpoVestigio.getSelectedItem() == TipoVestigio.FRAGMENTOS_VITREOS.getValor()
                        || spnTIpoVestigio.getSelectedItem() == TipoVestigio.FRAGMENTOS_METALICOS.getValor())
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
                new AlertDialog.Builder(getActivity())
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


    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }


}