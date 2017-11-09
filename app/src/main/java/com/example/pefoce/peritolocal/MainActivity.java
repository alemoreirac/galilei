package com.example.pefoce.peritolocal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapters.AdapterOcorrencia;
import Enums.DocumentoSolicitacao;
import Enums.TipoOcorrencia;
import Model.Dano;
import Model.DanoVeiculo;
import Model.DocumentoOcorrencia;
import Model.EnderecoTransito;
import Model.EnderecoVeiculo;
import Model.EnvolvidoTransito;
import Model.Ocorrencia;
import Model.OcorrenciaColisao;
import Model.OcorrenciaEndereco;
import Model.OcorrenciaEnvolvido;
import Model.OcorrenciaFoto;
import Model.OcorrenciaTransito;
import Model.OcorrenciaVeiculo;
import Model.Pessoa;
import Model.Vestigio;
import Model.VestigioColisao;
import Util.BuscadorEnum;

public class MainActivity extends AppCompatActivity
{
    Button btnLogout = null;
    ArrayList<Ocorrencia> ocorrencias = null;
    Pessoa perito = null;
    FloatingActionButton fabOcorrencia;
    ImageButton imgbBusca = null;
    ListView lstvOcorrencias = null;
    EditText edtBusca = null;
    AdapterOcorrencia adapter;
    String AnoEscolhido = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssociarLayout();

        AssociarEventos();

        Intent intent = getIntent();
//        if(intent.getLongExtra("OcorrenciaId",0) != 0)
//        {
//            ocorrenciaTransito = OcorrenciaTransito.findById(OcorrenciaTransito.class, intent.getLongExtra("OcorrenciaId", 0));
//        }

        //   final Dialog dialog = new Dialog(MainActivity.this);
        //   dialog.setContentView(R.layout.dano_veiculo);
        //   dialog.setCanceledOnTouchOutside(false);
//
        //   dialog.setTitle("Definir Proprietário");
        //   dialog.show();


        //Recebe o ID do perito logado, caso não seja encontrado, volta para a tela de login
        Long peritoId = intent.getLongExtra("PeritoId", 0);

        if (peritoId == 0)
        {
            Intent it = new Intent(this, LoginActivity.class);
            startActivity(it);
        }

        //Busca o perito pelo id passado e depois busca as ocorrências relacionadas a ele
        perito = Pessoa.findById(Pessoa.class, peritoId);

        ocorrencias = (ArrayList<Ocorrencia>) Ocorrencia.find(Ocorrencia.class, "perito = ?", perito.getId().toString());

        //final ArrayList<OcorrenciaTransito> ocorrenciasTransito = (ArrayList<OcorrenciaTransito>) OcorrenciaTransito.find(OcorrenciaTransito.class, "perito = ?", p.getId().toString());

        //Carrega as ocorrencias na lista
        adapter = new AdapterOcorrencia(ocorrencias, getApplicationContext());
        lstvOcorrencias.setAdapter(adapter);

    }

    private void AssociarEventos()
    {
        imgbBusca.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(edtBusca.getText().toString().trim() == "")
                {
                    ocorrencias = (ArrayList<Ocorrencia>) Ocorrencia.find(Ocorrencia.class, "perito = ?", perito.getId().toString());
                    adapter = new AdapterOcorrencia(ocorrencias, getApplicationContext());
                    lstvOcorrencias.setAdapter(adapter);
                    return;
                }
                String query = "SELECT * FROM OCORRENCIA_TRANSITO where NUM_INCIDENCIA like '%" + edtBusca.getText().toString().trim() + "%'";
                List<OcorrenciaTransito> ocorrenciasTransito = OcorrenciaTransito.findWithQuery(OcorrenciaTransito.class, query);
                ocorrenciasTransito.size();

                ArrayList<Ocorrencia> ocorrenciaResultado = new ArrayList<Ocorrencia>();

                for(int i = 0;i<ocorrenciasTransito.size();i++)
                {
                    if(Ocorrencia.find(Ocorrencia.class,"ocorrencia_transito = ?",ocorrenciasTransito.get(i).getId().toString()).size()>0)
                    ocorrenciaResultado.add(Ocorrencia.find(Ocorrencia.class,"ocorrencia_transito = ?",ocorrenciasTransito.get(i).getId().toString()).get(0));
                }

                adapter = new AdapterOcorrencia(ocorrenciaResultado, getApplicationContext());
                lstvOcorrencias.setAdapter(adapter);

//                String[] argumentos = new String[4];
//                argumentos[0] = perito.getId().toString();
//                argumentos[1] = "Incidencia";
//                argumentos[2] = "Data";
//                argumentos[3] = "Envolvido_nome";
//
//                String nome = "alessandro";
//
//                List<EnvolvidoTransito> envolvidoTransitoList = EnvolvidoTransito.find(EnvolvidoTransito.class,"nome = ?",nome);
//
//                ArrayList<OcorrenciaEnvolvido> ocorrenciaEnvolvidos = new ArrayList<OcorrenciaEnvolvido>();
//
//                for(EnvolvidoTransito envolvido : envolvidoTransitoList)
//                {
//                    ocorrenciaEnvolvidos.addAll(OcorrenciaEnvolvido.find(OcorrenciaEnvolvido.class,"envolvido_transito = ?",envolvido.getId().toString()));
//                }
//
//                for(OcorrenciaEnvolvido oe : ocorrenciaEnvolvidos)
//
//                oe.getOcorrenciaTransito().getOcorrenciaID();
//
//
//
//                ocorrencias = (ArrayList<Ocorrencia>) Ocorrencia.find(Ocorrencia.class, "perito = ?", perito.getId().toString());

                //final ArrayList<OcorrenciaTransito> ocorrenciasTransito = (ArrayList<OcorrenciaTransito>) OcorrenciaTransito.find(OcorrenciaTransito.class, "perito = ?", p.getId().toString());

                //Carrega as ocorrencias na lista

            }
        });

        lstvOcorrencias.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                Ocorrencia ocorrencia = ocorrencias.get(position);
                Intent it = new Intent(getApplicationContext(), ManterPericia.class);
                it.putExtra("OcorrenciaId", ocorrencia.getOcorrenciaTransito().getId());
                startActivity(it);
            }
        });

        lstvOcorrencias.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           final int position, long id)
            {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else
                {
                    builder = new AlertDialog.Builder(MainActivity.this);
                }
                builder.setTitle("Deletar Ocorrência")
                        .setMessage("Você deseja deletar esta Ocorrência?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {


                                DeletarOcorrencia(position);


                                Toast.makeText(MainActivity.this, "Ocorrência Deletada com sucesso!", Toast.LENGTH_LONG).show();

                            }


                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                {
                    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else
                {
                    builder = new AlertDialog.Builder(MainActivity.this);
                }
                builder.setTitle("Logout")
                        .setMessage("Você deseja fazer logout?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

                                Intent it = new Intent(getBaseContext(),LoginActivity.class);
                                startActivity(it);


                                Toast.makeText(MainActivity.this, "Logout efetuado com sucesso", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });


        fabOcorrencia.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {

                        //String ano = Calendar.YEAR;

                        final Dialog dialog = new Dialog(MainActivity.this);
                        dialog.setContentView(R.layout.dialog_pericia_transito);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();

                        final TextView txvNumIncidencia = (TextView) dialog.findViewById(R.id.txv_dialog_Incidencia);
                        final Spinner spnTipoOcorrencia = (Spinner) dialog.findViewById(R.id.spn_dialog_TipoOcorrencia);
                        final Spinner spnAnoIncidencia = (Spinner) dialog.findViewById(R.id.spn_dialog_Ano_Incidencia);
                        final EditText edtNumDoc = (EditText) dialog.findViewById(R.id.edt_dialog_NumIncidencia);
                        final StringBuilder incidencia = new StringBuilder("0000000");


                        ArrayList<String> tiposOcorrencia = new ArrayList<>();

                        for (TipoOcorrencia to : TipoOcorrencia.values())
                            tiposOcorrencia.add(to.getValor());

                        ArrayList<String> anos = new ArrayList<String>();

                        int esteAno = Calendar.getInstance().get(Calendar.YEAR);
                        AnoEscolhido = Integer.toString(esteAno);
                        for (int i = esteAno; i >= 1990; i--)
                        {
                            anos.add(Integer.toString(i));
                        }

                        spnAnoIncidencia.setAdapter(new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_dropdown_item, anos));

                        spnTipoOcorrencia.setAdapter(new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_dropdown_item, tiposOcorrencia));
                        spnTipoOcorrencia.setEnabled(false);


                        spnAnoIncidencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                            {
                                AnoEscolhido = spnAnoIncidencia.getSelectedItem().toString();
                                txvNumIncidencia.setText("I"+AnoEscolhido + incidencia.toString());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent)
                            {

                            }
                        });

                        edtNumDoc.addTextChangedListener(new TextWatcher()
                        {
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count)
                            {

                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count,
                                                          int after)
                            {
                                // TODO Auto-generated method stub
                            }

                            @Override
                            public void afterTextChanged(Editable s)
                            {
                                incidencia.setLength(0);
                                incidencia.insert(0, "0000000");
                                incidencia.replace(incidencia.length() - s.length(), incidencia.length(), s.toString());

                                //txvNumIncidencia.setText(incidencia.replace("0000000",s.toString()));
                                txvNumIncidencia.setText("I"+AnoEscolhido + incidencia.toString());
                            }
                        });

                        // final Spinner spnTipoDoc = (Spinner) dialog.findViewById(R.id.spn_dialog_TipoDoc);

                        //Carrega os valores do Enum Documento Solicitacao no Spinner
                        ArrayList<String> docs = new ArrayList<String>();

                        for (DocumentoSolicitacao d : DocumentoSolicitacao.values())
                        {
                            docs.add(d.getValor());
                        }

                        //   spnTipoDoc.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,docs));

                        Button btnComecaPericia = (Button) dialog.findViewById(R.id.btn_dialog_IniciarPericia);

                        btnComecaPericia.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {

                                //if (OcorrenciaTransito.find(OcorrenciaTransito.class, "num_incidencia = ?", txvNumIncidencia.getText().toString().substring(7)).size() > 0)
                                if (OcorrenciaTransito.find(OcorrenciaTransito.class, "num_incidencia = ?", txvNumIncidencia.getText().toString()).size() > 0)
                                {
                                    txvNumIncidencia.setText(txvNumIncidencia.getText().toString() + " Já existe, favor tente outro número!");
                                    txvNumIncidencia.setTextColor(Color.RED);
                                    return;
                                }

                                if (edtNumDoc.getText().toString().length() > 0)
                                {
                                    Ocorrencia ocorrenciaNova = new Ocorrencia();

                                    OcorrenciaTransito ocorrenciaTransito = new OcorrenciaTransito();

                                    DocumentoOcorrencia documentoOcorrencia = new DocumentoOcorrencia(null, "");

                                    documentoOcorrencia.save();

                                    ocorrenciaTransito.setDocumentoOcorrencia(documentoOcorrencia);

                                    //ocorrenciaTransito.setNumIncidencia(txvNumIncidencia.getText().toString().substring(txvNumIncidencia.getText().toString().length() - 7));

                                    ocorrenciaTransito.setNumIncidencia(txvNumIncidencia.getText().toString());

                                    //ocorrenciaTransito.save();

                                    ocorrenciaNova.setOcorrenciaTransito(ocorrenciaTransito);

                                    ocorrenciaNova.setTipoOcorrencia(BuscadorEnum.BuscarTipoOcorrencia(spnTipoOcorrencia.getSelectedItem().toString()));

                                    ocorrenciaNova.setPerito(perito);

                                    ocorrenciaTransito.save();

                                    ocorrenciaNova.save();

                                    ocorrenciaTransito.setOcorrenciaID(ocorrenciaNova.getId());

                                    ocorrenciaTransito.save();

                                    //     ocorrenciaTransito.save();

                                    Intent it = new Intent(MainActivity.this, ManterPericia.class);

                                    it.putExtra("OcorrenciaId", ocorrenciaTransito.getId());

                                    startActivity(it);
                                } else
                                    Toast.makeText(getApplicationContext(), "Preencha corretamente os dados!", Toast.LENGTH_LONG).show();
                            }
                        });

                        //Botão de cancelar a criação de perícia
                        Button btnCancelaPericia = (Button) dialog.findViewById(R.id.btn_dialog_Cancelar);

                        btnCancelaPericia.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                dialog.dismiss();
                            }
                        });
                    }
                });

    }

public void DeletarOcorrencia(int position)
{
    OcorrenciaTransito ocorrenciaTransito = ocorrencias.get(position).getOcorrenciaTransito();

    Ocorrencia ocorrenciaDelete = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransito.getOcorrenciaID());



    adapter.remove((Ocorrencia)lstvOcorrencias.getAdapter().getItem(position));
    adapter.notifyDataSetChanged();


    //Deletar Entidades envolvidas com endereço: EnderecoTransito, Endereco, EnderecoVeiculo e OcorrenciaEndereco

    List<OcorrenciaEndereco> ocorrenciaEnderecos = OcorrenciaEndereco.find(OcorrenciaEndereco.class,"ocorrencia_transito = ?",ocorrenciaTransito.getId().toString());

    List<EnderecoVeiculo> enderecosVeiculo = null;

    for(int i = 0;i<ocorrenciaEnderecos.size();i++)
    {
        enderecosVeiculo = EnderecoVeiculo.find(EnderecoVeiculo.class,"endereco = ?",ocorrenciaEnderecos.get(i).getEnderecoTransito().getEndereco().getId().toString());
        for(EnderecoVeiculo ev: enderecosVeiculo)
            ev.delete();

        ocorrenciaEnderecos.get(i).getEnderecoTransito().getEndereco().delete();
        ocorrenciaEnderecos.get(i).getEnderecoTransito().delete();
        ocorrenciaEnderecos.get(i).delete();
    }

    //Deletar Entidades relacionadas ao Envolvido: EnvolvidoTransito e OcorrenciaEnvolvido

    List<OcorrenciaEnvolvido> ocorrenciasEnvolvido = OcorrenciaEnvolvido.find(OcorrenciaEnvolvido.class,"ocorrencia_transito = ?",ocorrenciaTransito.getId().toString());

    for(OcorrenciaEnvolvido oe : ocorrenciasEnvolvido)
    {
        oe.getEnvolvidoTransito().delete();
        oe.delete();
    }


    List<OcorrenciaVeiculo> ocorrenciasVeiculo = OcorrenciaVeiculo.find(OcorrenciaVeiculo.class,"ocorrencia_transito = ?",ocorrenciaTransito.getId().toString());

    List<DanoVeiculo> danosVeiculo = null;

    for(OcorrenciaVeiculo ov : ocorrenciasVeiculo)
    {
        danosVeiculo = DanoVeiculo.find(DanoVeiculo.class,"veiculo = ?",ov.getVeiculo().getId().toString());
        for(DanoVeiculo dv : danosVeiculo)
        {
            dv.getDano().delete();
            dv.delete();
        }
        ov.getVeiculo().delete();
        ov.delete();
    }


    List<OcorrenciaFoto> ocorrenciaFotos = OcorrenciaFoto.find(OcorrenciaFoto.class,"ocorrencia_transito = ?",ocorrenciaTransito.getId().toString());

    for(OcorrenciaFoto of : ocorrenciaFotos)
    {
        of.getFoto().delete();
        of.delete();
    }

    List<OcorrenciaColisao> ocorrenciaColisoes = OcorrenciaColisao.find(OcorrenciaColisao.class,"ocorrencia_transito = ?",ocorrenciaTransito.getId().toString());

    List<VestigioColisao> vestigioColisoes = null;
    for(OcorrenciaColisao oc : ocorrenciaColisoes)
    {
        vestigioColisoes = VestigioColisao.find(VestigioColisao.class,"colisao_transito = ?",oc.getColisaoTransito().getId().toString());
        for(VestigioColisao vc : vestigioColisoes)
        {
            if(vc.getVestigioId()!= null)
            {
                Vestigio v = Vestigio.findById(Vestigio.class,vc.getVestigioId());
                v.delete();
            }
            vc.delete();
        }
        oc.getColisaoTransito().delete();
        oc.delete();
    }


    ocorrenciaTransito.delete();
    ocorrenciaDelete.delete();

}


    public void AssociarLayout()
    {
        fabOcorrencia = (FloatingActionButton) findViewById(R.id.fab_Pericia);
        lstvOcorrencias = (ListView) findViewById(R.id.lstvOcorrencias);
        edtBusca = (EditText) findViewById(R.id.edt_busca);
        imgbBusca = (ImageButton) findViewById(R.id.imgb_Busca);
        btnLogout = (Button) findViewById(R.id.btn_Logout);

    }


}
