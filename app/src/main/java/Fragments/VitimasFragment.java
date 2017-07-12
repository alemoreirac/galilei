package Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.MainActivity;
import com.example.pefoce.peritolocal.ManterEnderecoActivity;
import com.example.pefoce.peritolocal.ManterPericia;
import com.example.pefoce.peritolocal.ManterVitimaActivity;
import com.example.pefoce.peritolocal.PericiaTransito;
import com.example.pefoce.peritolocal.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import Adapters.AdapterEnvolvidoTransito;
import Model.EnderecoTransito;
import Model.EnvolvidoTransito;
import Model.OcorrenciaEndereco;
import Model.OcorrenciaEnvolvido;
import Model.OcorrenciaTransito;


public class VitimasFragment extends  android.support.v4.app.Fragment implements Step {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    OcorrenciaTransito ocorrenciaTransitoVitima;

    private OnFragmentInteractionListener mListener;

    public VitimasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VitimasFragment.
     */

    public static VitimasFragment newInstance(String param1, String param2) {
        VitimasFragment fragment = new VitimasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vitimas, container, false);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ocorrenciaTransitoVitima = ((ManterPericia) getActivity()).ocorrenciaTransito;

        final List<OcorrenciaEnvolvido> ocorrenciaEnvolvidos = OcorrenciaEnvolvido.find(OcorrenciaEnvolvido.class,"ocorrencia_transito = ?",ocorrenciaTransitoVitima.getId().toString());
//

        final ArrayList<EnvolvidoTransito> envolvidotransitoModel = new ArrayList<>();

        for(OcorrenciaEnvolvido oe : ocorrenciaEnvolvidos)
        {
            if(oe.getEnvolvidoTransito() != null)
            {
                envolvidotransitoModel.add(oe.getEnvolvidoTransito());
            }
        }

        final AdapterEnvolvidoTransito adp = new AdapterEnvolvidoTransito(envolvidotransitoModel,getActivity());

        ListView list = (ListView) view.findViewById(R.id.lstv_Vitimas);

        list.setAdapter(adp);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                EnvolvidoTransito envolvidoTransito = envolvidotransitoModel.get(position);

                Intent it = new Intent(getActivity(),ManterVitimaActivity.class);
                it.putExtra("EnvolvidoId",envolvidoTransito.getId());
                it.putExtra("OcorrenciaId",ocorrenciaTransitoVitima.getId());
                startActivity(it);
            }
        });



        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           final int position, long id) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(getActivity());
                }
                builder.setTitle("Deletar Ocorrência")
                        .setMessage("Você deseja deletar esta Ocorrência?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                OcorrenciaEnvolvido ocorrenciaEnvolvido= ocorrenciaEnvolvidos.get(position);

                                adp.remove(ocorrenciaEnvolvido.getEnvolvidoTransito());

                                ocorrenciaEnvolvido.delete();

                                Toast.makeText(getActivity(), "Envolvido Deletado com sucesso!", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        });


        FloatingActionButton myFab = (FloatingActionButton)  view.findViewById(R.id.fab_Vitima);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(),ManterVitimaActivity.class);
                it.putExtra("OcorrenciaId",ocorrenciaTransitoVitima.getId());
                startActivity(it);
            }
        });


    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
