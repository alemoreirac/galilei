package Fragments;

import android.app.AlertDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.MainActivity;
import com.example.pefoce.peritolocal.ManterEnderecoActivity;
import com.example.pefoce.peritolocal.ManterPericia;
import com.example.pefoce.peritolocal.PericiaTransito;
import com.example.pefoce.peritolocal.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import Adapters.AdapterEndereco;
import Model.EnderecoTransito;
import Model.OcorrenciaEndereco;
import Model.OcorrenciaTransito;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EnderecosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EnderecosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnderecosFragment extends  android.support.v4.app.Fragment  implements Step
{

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

OcorrenciaTransito ocorrenciaTransitoEndereco;


    private OnFragmentInteractionListener mListener;

    public EnderecosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EnderecosFragment.
     */

    public static EnderecosFragment newInstance(String param1, String param2)
    {
        EnderecosFragment fragment = new EnderecosFragment();
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
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enderecos, container, false);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        ((ManterPericia) getActivity()).toolbar.setTitle("Endereços");
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


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        //Bundle bd = this.getArguments();

        ocorrenciaTransitoEndereco =  ((ManterPericia) getActivity()).ocorrenciaTransito;
        // ocorrenciaTransitoEndereco = OcorrenciaTransito.findById(OcorrenciaTransito.class, bd.getLong("OcorrenciaId", 0));

         final List<OcorrenciaEndereco> ocorrenciaEnderecos = OcorrenciaEndereco.find(OcorrenciaEndereco.class,"ocorrencia_transito = ?", ocorrenciaTransitoEndereco.getId().toString());
        //final List<OcorrenciaEndereco> ocorrenciaEnderecos = OcorrenciaEndereco.listAll(OcorrenciaEndereco.class);
        //ArrayList<String> enderecosTransito = new ArrayList<String>();

        final ArrayList<EnderecoTransito> enderecoTransitoModel = new ArrayList<EnderecoTransito>();

        for(OcorrenciaEndereco oe : ocorrenciaEnderecos )
        {
            if(oe.getEnderecoTransito() != null)
            {
                enderecoTransitoModel.add(oe.getEnderecoTransito());
            }
        }

        final AdapterEndereco adp = new AdapterEndereco(enderecoTransitoModel,getActivity());

        ListView list = (ListView) view.findViewById(R.id.lstv_Enderecos);

        list.setAdapter(adp);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EnderecoTransito enderecoTransito = enderecoTransitoModel.get(position);

                Intent it = new Intent(getActivity(),ManterEnderecoActivity.class);
                it.putExtra("EnderecoId",enderecoTransito.getId());
                it.putExtra("OcorrenciaId",ocorrenciaTransitoEndereco.getId());
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

                                OcorrenciaEndereco ocorrenciaEndereco= ocorrenciaEnderecos.get(position);

                                adp.remove(ocorrenciaEndereco.getEnderecoTransito());

                                ocorrenciaEndereco.delete();

                                Toast.makeText(getActivity(), "Ocorrência Deletada com sucesso!", Toast.LENGTH_LONG).show();

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


        FloatingActionButton myFab = (FloatingActionButton)  view.findViewById(R.id.fab_Endereco);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(),ManterEnderecoActivity.class);
                it.putExtra("OcorrenciaId",ocorrenciaTransitoEndereco.getId());
                startActivity(it);

            }
        });



      // Button btnSalvar = (Button) getActivity().findViewById(R.id.btn_Salvar_Pericia);

      // btnSalvar.setOnClickListener(new View.OnClickListener()
      // {
      //     @Override
      //     public void onClick(View v)
      //     {
      //         Toast.makeText(v.getContext(), "Ocorrência salva com Sucesso!", Toast.LENGTH_LONG).show();

      //         Intent it = new Intent(v.getContext(),MainActivity.class);
      //         it.putExtra("PeritoId",ocorrenciaTransitoEndereco.getPerito().getId());
      //         startActivity(it);
      //     }
      // });

      // Button btnCancelar = (Button) getActivity().findViewById(R.id.btn_Cancelar_Pericia);

      // btnCancelar.setOnClickListener(new View.OnClickListener()
      // {
      //     @Override
      //     public void onClick(final View v)
      //     {
      //         AlertDialog.Builder builder;
      //         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      //             builder = new AlertDialog.Builder(v.getContext(), android.R.style.Theme_Material_Dialog_Alert);
      //         } else {
      //             builder = new AlertDialog.Builder(v.getContext());
      //         }
      //         builder.setTitle("Cancelar Ocorrência")
      //                 .setMessage("Você deseja cancelar as alterações desta Ocorrência?")
      //                 .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
      //                     public void onClick(DialogInterface dialog, int which) {
      //                         Intent it = new Intent(v.getContext(),MainActivity.class);
      //                          it.putExtra("PeritoId",ocorrenciaTransitoEndereco.getPerito().getId());
      //                         startActivity(it);
      //                     }
      //                 })
      //                 .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
      //                     public void onClick(DialogInterface dialog, int which) {
      //                         dialog.dismiss();
      //                     }
      //                 })
      //                 .show();
      //     }
      // });
    }
    }

