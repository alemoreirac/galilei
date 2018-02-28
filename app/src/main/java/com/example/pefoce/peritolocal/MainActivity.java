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
import android.view.WindowManager;
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
import Model.Transito.DanoVeiculo;
import Model.DocumentoOcorrencia;
import Model.Transito.EnderecoVeiculo;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransitoColisao;
import Model.Transito.OcorrenciaTransitoEndereco;
import Model.Transito.OcorrenciaTransitoEnvolvido;
import Model.Transito.OcorrenciaTransitoFoto;
import Model.Transito.OcorrenciaTransito;
import Model.Transito.OcorrenciaTransitoVeiculo;
import Model.Pessoa;
import Model.Transito.VestigioTransito;
import Model.Transito.VestigioColisao;
import Model.Vida.LesaoEnvolvido;
import Model.Vida.OcorrenciaEnvolvidoVida;
import Model.Vida.OcorrenciaVida;
import Model.Vida.OcorrenciaVidaFoto;
import Model.Vida.VestigioVidaOcorrencia;

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
    TextView txvNenhumaPericia;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssociarLayout();

        AssociarEventos();

        Intent intent = getIntent();



        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



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

        if(ocorrencias!=null)
        {
            if (ocorrencias.size() == 0)
            {
                lstvOcorrencias.setVisibility(View.INVISIBLE);
                txvNenhumaPericia.setVisibility(View.VISIBLE);
            }
            else
            {
                lstvOcorrencias.setVisibility(View.VISIBLE);
                txvNenhumaPericia.setVisibility(View.INVISIBLE);
            }
        }

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
                if (edtBusca.getText().toString().trim() == "")
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

                for (int i = 0; i < ocorrenciasTransito.size(); i++)
                {
                    if (Ocorrencia.find(Ocorrencia.class, "ocorrencia_transito = ?", ocorrenciasTransito.get(i).getId().toString()).size() > 0)
                        ocorrenciaResultado.add(Ocorrencia.find(Ocorrencia.class, "ocorrencia_transito = ?", ocorrenciasTransito.get(i).getId().toString()).get(0));
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
//                ArrayList<OcorrenciaTransitoEnvolvido> ocorrenciaEnvolvidos = new ArrayList<OcorrenciaTransitoEnvolvido>();
//
//                for(EnvolvidoTransito envolvido : envolvidoTransitoList)
//                {
//                    ocorrenciaEnvolvidos.addAll(OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class,"envolvido_transito = ?",envolvido.getId().toString()));
//                }
//
//                for(OcorrenciaTransitoEnvolvido oe : ocorrenciaEnvolvidos)
//
//                oe.getOcorrenciaVida().getOcorrenciaID();
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

                switch (ocorrencia.getTipoOcorrencia())
                {
                    case TRANSITO:
                        Intent intentTransito = new Intent(getApplicationContext(), ManterPericiaTransito.class);
                        intentTransito.putExtra("OcorrenciaId", ocorrencia.getOcorrenciaTransito().getId());


                        startActivity(intentTransito);
                        break;
                    case VIDA:
                        Intent intentVida = new Intent(getApplicationContext(), ManterPericiaVida.class);
                        intentVida.putExtra("OcorrenciaId", ocorrencia.getOcorrenciaVida().getId());
                        intentVida.putExtra("FragmentPicker", "Ocorrência");
                        startActivity(intentVida);
                        break;
                }

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

                                Ocorrencia ocorrencia = (Ocorrencia)lstvOcorrencias.getAdapter().getItem(position);

                                switch (ocorrencia.getTipoOcorrencia())
                                {
                                    case TRANSITO:
                                        DeletarOcorrenciaTransito(position);
                                        break;
                                    case VIDA:
                                        DeletarOcorrenciaVida(position);
                                        break;
                                }


                                if (adapter.getCount() == 0)
                                {
                                    lstvOcorrencias.setVisibility(View.INVISIBLE);
                                    txvNenhumaPericia.setVisibility(View.VISIBLE);
                                }
                                else
                                {
                                    lstvOcorrencias.setVisibility(View.VISIBLE);
                                    txvNenhumaPericia.setVisibility(View.INVISIBLE);
                                }

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

                                Intent it = new Intent(getBaseContext(), LoginActivity.class);
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
                        //spnTipoOcorrencia.setEnabled(false);


                        spnAnoIncidencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                            {
                                AnoEscolhido = spnAnoIncidencia.getSelectedItem().toString();
                                txvNumIncidencia.setText("I" + AnoEscolhido + incidencia.toString());
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
                            public void beforeTextChanged(CharSequence s, int start, int count,int after)
                            {

                            }

                            @Override
                            public void afterTextChanged(Editable s)
                            {
                                incidencia.setLength(0);
                                incidencia.insert(0, "0000000");
                                incidencia.replace(incidencia.length() - s.length(), incidencia.length(), s.toString());

                                //txvNumIncidencia.setText(incidencia.replace("0000000",s.toString()));
                                txvNumIncidencia.setText("I" + AnoEscolhido + incidencia.toString());
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
                                    if (spnTipoOcorrencia.getSelectedItem().toString().equals("Trânsito"))
                                        NovaPericiaTransito(txvNumIncidencia.getText().toString());

                                    if (spnTipoOcorrencia.getSelectedItem().toString().equals("Crime contra a Vida"))
                                        NovaPericiaVida(txvNumIncidencia.getText().toString());

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

    public void DeletarOcorrenciaTransito(int position)
    {
        OcorrenciaTransito ocorrenciaTransito = ocorrencias.get(position).getOcorrenciaTransito();

        Ocorrencia ocorrenciaDelete = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransito.getOcorrenciaID());


        adapter.remove((Ocorrencia) lstvOcorrencias.getAdapter().getItem(position));
        adapter.notifyDataSetChanged();


        //Deletar Entidades envolvidas com endereço: EnderecoTransito, Endereco, EnderecoVeiculo e OcorrenciaTransitoEndereco

        List<OcorrenciaTransitoEndereco> ocorrenciaEnderecos = OcorrenciaTransitoEndereco.find(OcorrenciaTransitoEndereco.class, "ocorrencia_transito = ?", ocorrenciaTransito.getId().toString());

        List<EnderecoVeiculo> enderecosVeiculo = null;

        for (int i = 0; i < ocorrenciaEnderecos.size(); i++)
        {

            try
            {
                enderecosVeiculo = EnderecoVeiculo.find(EnderecoVeiculo.class, "endereco = ?", ocorrenciaEnderecos.get(i).getEnderecoTransito().getEndereco().getId().toString());
            } catch (Exception e)
            {
                continue;
            }
            for (EnderecoVeiculo ev : enderecosVeiculo)
                ev.delete();

            ocorrenciaEnderecos.get(i).getEnderecoTransito().getEndereco().delete();
            ocorrenciaEnderecos.get(i).getEnderecoTransito().delete();
            ocorrenciaEnderecos.get(i).delete();
        }

        //Deletar Entidades relacionadas ao Envolvido: EnvolvidoTransito e OcorrenciaTransitoEnvolvido

        List<OcorrenciaTransitoEnvolvido> ocorrenciasEnvolvido = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class, "ocorrencia_transito = ?", ocorrenciaTransito.getId().toString());

        for (OcorrenciaTransitoEnvolvido oe : ocorrenciasEnvolvido)
        {
            oe.getEnvolvidoTransito().delete();
            oe.delete();
        }


        List<OcorrenciaTransitoVeiculo> ocorrenciasVeiculo = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "ocorrencia_transito = ?", ocorrenciaTransito.getId().toString());

        List<DanoVeiculo> danosVeiculo = null;

        for (OcorrenciaTransitoVeiculo ov : ocorrenciasVeiculo)
        {
            danosVeiculo = DanoVeiculo.find(DanoVeiculo.class, "veiculo = ?", ov.getVeiculo().getId().toString());
            for (DanoVeiculo dv : danosVeiculo)
            {
                dv.getDano().delete();
                dv.delete();
            }
            ov.getVeiculo().delete();
            ov.delete();
        }


        List<OcorrenciaTransitoFoto> ocorrenciaFotos = OcorrenciaTransitoFoto.find(OcorrenciaTransitoFoto.class, "ocorrencia_transito = ?", ocorrenciaTransito.getId().toString());

        for (OcorrenciaTransitoFoto of : ocorrenciaFotos)
        {
            of.getFoto().delete();
            of.delete();
        }

        List<OcorrenciaTransitoColisao> ocorrenciaColisoes = OcorrenciaTransitoColisao.find(OcorrenciaTransitoColisao.class, "ocorrencia_transito = ?", ocorrenciaTransito.getId().toString());

        List<VestigioColisao> vestigioColisoes = null;
        for (OcorrenciaTransitoColisao oc : ocorrenciaColisoes)
        {
            vestigioColisoes = VestigioColisao.find(VestigioColisao.class, "colisao_transito = ?", oc.getColisaoTransito().getId().toString());
            for (VestigioColisao vc : vestigioColisoes)
            {
                if (vc.getVestigioId() != null)
                {
                    VestigioTransito v = VestigioTransito.findById(VestigioTransito.class, vc.getVestigioId());
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


    public void DeletarOcorrenciaVida(int position)
    {
        OcorrenciaVida ocorrenciaVida = ocorrencias.get(position).getOcorrenciaVida();

        Ocorrencia ocorrenciaDelete = Ocorrencia.findById(Ocorrencia.class, ocorrenciaVida.getOcorrenciaID());

        adapter.remove((Ocorrencia) lstvOcorrencias.getAdapter().getItem(position));
        adapter.notifyDataSetChanged();


        List<OcorrenciaEnvolvidoVida> ocorrenciaEnvolvidos = OcorrenciaEnvolvidoVida.find(OcorrenciaEnvolvidoVida.class, "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());

        List<LesaoEnvolvido> lesoesEnvolvido = null;

        for (int i = 0; i < ocorrenciaEnvolvidos.size(); i++)
        {

            try
            {
                lesoesEnvolvido = LesaoEnvolvido.find(LesaoEnvolvido.class, "envolvido_vida = ?", ocorrenciaEnvolvidos.get(i).getEnvolvidoVida().getId().toString());
            } catch (Exception e)
            {
                continue;
            }
            for (LesaoEnvolvido le : lesoesEnvolvido)
            {
                le.getLesao().delete();
                le.delete();
            }

            lesoesEnvolvido = null;
        }


        List<OcorrenciaEnvolvidoVida> ocorrenciaEnvolvidoVidaList = OcorrenciaEnvolvidoVida.find(OcorrenciaEnvolvidoVida.class, "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());

        for (OcorrenciaEnvolvidoVida oev : ocorrenciaEnvolvidoVidaList)
        {
            if(oev.getEnvolvidoVida()!=null)
            oev.getEnvolvidoVida().delete();
            oev.delete();
        }

        ocorrenciaEnvolvidoVidaList = null;


        List<VestigioVidaOcorrencia> vestigiosOcorrenciaList = VestigioVidaOcorrencia.find(VestigioVidaOcorrencia.class, "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());

        for (VestigioVidaOcorrencia vvo : vestigiosOcorrenciaList)
        {
            vvo.getVestigioVida().delete();
            vvo.delete();
        }

        vestigiosOcorrenciaList = null;


        List<OcorrenciaVidaFoto> ocorrenciaFotos = OcorrenciaVidaFoto.find(OcorrenciaVidaFoto.class, "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());

        for (OcorrenciaVidaFoto of : ocorrenciaFotos)
        {
            of.getFoto().delete();
            of.delete();
        }

        ocorrenciaVida.delete();
        ocorrenciaDelete.delete();

    }


    public void AssociarLayout()
    {
        txvNenhumaPericia = (TextView) findViewById(R.id.txv_Nenhuma_Pericia);
        fabOcorrencia = (FloatingActionButton) findViewById(R.id.fab_Pericia);
        lstvOcorrencias = (ListView) findViewById(R.id.lstvOcorrencias);
        edtBusca = (EditText) findViewById(R.id.edt_busca);
        imgbBusca = (ImageButton) findViewById(R.id.imgb_Busca);
        btnLogout = (Button) findViewById(R.id.btn_Logout);

    }

    public void NovaPericiaVida(String numIncidencia)
    {
        Ocorrencia ocorrenciaNova = new Ocorrencia();

        OcorrenciaVida ocorrenciaVida = new OcorrenciaVida();

        DocumentoOcorrencia documentoOcorrencia = new DocumentoOcorrencia(null, "");

        documentoOcorrencia.save();

        ocorrenciaVida.setDocumento(documentoOcorrencia);

        ocorrenciaVida.setNumIncidencia(numIncidencia);

        ocorrenciaNova.setTipoOcorrencia(TipoOcorrencia.VIDA);

        ocorrenciaNova.setPerito(perito);

        ocorrenciaVida.save();

        ocorrenciaNova.setOcorrenciaVida(ocorrenciaVida);

        ocorrenciaNova.save();

        ocorrenciaVida.setOcorrenciaID(ocorrenciaNova.getId());

        ocorrenciaVida.save();

        Intent it = new Intent(MainActivity.this, ManterPericiaVida.class);

        it.putExtra("OcorrenciaId", ocorrenciaVida.getId());

        it.putExtra("FragmentPicker", "Ocorrência");

        startActivity(it);
    }

    public void NovaPericiaTransito(String numIncidencia)
    {
        Ocorrencia ocorrenciaNova = new Ocorrencia();

        OcorrenciaTransito ocorrenciaTransito = new OcorrenciaTransito();

        DocumentoOcorrencia documentoOcorrencia = new DocumentoOcorrencia(null, "");

        documentoOcorrencia.save();

        ocorrenciaTransito.setDocumentoOcorrencia(documentoOcorrencia);

        //ocorrenciaTransito.setNumIncidencia(txvNumIncidencia.getText().toString().substring(txvNumIncidencia.getText().toString().length() - 7));

        ocorrenciaTransito.setNumIncidencia(numIncidencia);

        //ocorrenciaTransito.save();

        ocorrenciaNova.setOcorrenciaTransito(ocorrenciaTransito);

        ocorrenciaNova.setTipoOcorrencia(TipoOcorrencia.TRANSITO);

        ocorrenciaNova.setPerito(perito);

        ocorrenciaTransito.save();

        ocorrenciaNova.save();

        ocorrenciaTransito.setOcorrenciaID(ocorrenciaNova.getId());

        ocorrenciaTransito.save();


        Intent it = new Intent(MainActivity.this, ManterPericiaTransito.class);


        it.putExtra("OcorrenciaId", ocorrenciaTransito.getId());

        startActivity(it);
    }

    public void onBackPressed()
    {
        //IGNORE O BACKPRESSED PARA ANULAR O BOTÂO VOLTAR
    }
}
