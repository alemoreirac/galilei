package com.example.pefoce.peritolocal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import Adapters.AdapterOcorrencia;
import Control.BairroBusiness;
import Control.MunicipioBusiness;
import Enums.TipoOcorrencia;
import Enums.Transito.TipoVia;
import Model.Transito.DanoVeiculo;
import Model.DocumentoOcorrencia;
import Model.Transito.EnderecoTransito;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransitoColisao;
import Model.Transito.OcorrenciaTransitoEndereco;
import Model.Transito.OcorrenciaTransitoEnvolvido;
import Model.Transito.OcorrenciaTransitoFoto;
import Model.Transito.OcorrenciaTransito;
import Model.Transito.OcorrenciaTransitoVeiculo;
import Model.Usuario;
import Model.Transito.VestigioTransito;
import Model.Transito.VestigioColisao;
import Model.Vida.EnderecoVida;
import Model.Vida.LesaoEnvolvido;
import Model.Vida.OcorrenciaVidaEndereco;
import Model.Vida.OcorrenciaEnvolvidoVida;
import Model.Vida.OcorrenciaVida;
import Model.Vida.OcorrenciaVidaFoto;
import Model.Vida.VestigioVidaOcorrencia;
import Util.AutoCompleteUtil;
import Util.BuscadorEnum;
import Util.PaginatorOcorrencia;
import Util.StringUtil;
import Util.TempoUtil;


public class MainActivity extends Activity
{
    Button btnLogout = null;
    ArrayList<Ocorrencia> ocorrencias = null;
    Usuario perito = null;
    FloatingActionButton fabOcorrencia;
    ImageButton imgbBusca = null;
    ListView lstvOcorrencias = null;
    EditText edtBusca = null;
    AdapterOcorrencia adapter;
    String AnoEscolhido = "";
    TextView txvNenhumaPericia, txvPerito, txvPaginas;
    Button btnAnterior, btnProxima;
    int paginaAtual = 1;
    int maximoPaginas = 1;


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
        perito = Usuario.findById(Usuario.class, peritoId);

        if (perito != null && perito.getNome() != null)
            txvPerito.setText(perito.getNome());
        else
            txvPerito.setText("(Perito sem nome)");

        paginaAtual = 1;
        btnAnterior.setEnabled(false);

        PaginatorOcorrencia.refreshOcorrencias(perito.getId());

        ocorrencias = PaginatorOcorrencia.findByFilterPaginated("", paginaAtual);

        maximoPaginas = PaginatorOcorrencia.maxPages;

        if (maximoPaginas == 1)
            btnProxima.setEnabled(false);

        txvPaginas.setText("Página " + paginaAtual + " de " + maximoPaginas);

        if (ocorrencias != null)
        {
            if (ocorrencias.size() == 0)
            {
                lstvOcorrencias.setVisibility(View.INVISIBLE);
                txvNenhumaPericia.setVisibility(View.VISIBLE);
            } else
            {
                Collections.sort(ocorrencias);
                lstvOcorrencias.setVisibility(View.VISIBLE);
                txvNenhumaPericia.setVisibility(View.INVISIBLE);
            }
        }

        //Carrega as ocorrencias na lista
        adapter = new AdapterOcorrencia(ocorrencias, getApplicationContext());
        lstvOcorrencias.setAdapter(adapter);

    }

    private void AssociarEventos()
    {

        btnProxima.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        adapter = new AdapterOcorrencia(PaginatorOcorrencia.findByFilterPaginated(edtBusca.getText().toString().trim(), ++paginaAtual), getApplicationContext());
                        maximoPaginas = PaginatorOcorrencia.maxPages;
                        lstvOcorrencias.setAdapter(adapter);

                        if (paginaAtual == maximoPaginas)
                            btnProxima.setEnabled(false);

                        btnAnterior.setEnabled(true);

                        txvPaginas.setText("Página " + paginaAtual + " de " + maximoPaginas);
                    }
                });

        btnAnterior.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        adapter = new AdapterOcorrencia(PaginatorOcorrencia.findByFilterPaginated(edtBusca.getText().toString().trim(), --paginaAtual), getApplicationContext());
                        maximoPaginas = PaginatorOcorrencia.maxPages;
                        lstvOcorrencias.setAdapter(adapter);

                        if (paginaAtual == 1)
                            btnAnterior.setEnabled(false);

                        btnProxima.setEnabled(true);

                        txvPaginas.setText("Página " + paginaAtual + " de " + maximoPaginas);
                    }
                });


        edtBusca.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                adapter = new AdapterOcorrencia(PaginatorOcorrencia.findByFilterPaginated(s.toString().trim(), 1), getApplicationContext());
                maximoPaginas = PaginatorOcorrencia.maxPages;

                lstvOcorrencias.setAdapter(adapter);

                btnAnterior.setEnabled(false);

                paginaAtual = 1;

                if (maximoPaginas == 1)

                    btnProxima.setEnabled(false);

                else

                    btnProxima.setEnabled(true);

                txvPaginas.setText("Página " + 1 + " de " + maximoPaginas);

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

                                Ocorrencia ocorrencia = (Ocorrencia) lstvOcorrencias.getAdapter().getItem(position);

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
                                } else
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
                        dialog.setContentView(R.layout.dialog_pericia_nova);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();

                        final TextView txvHoraChamada = (TextView) dialog.findViewById(R.id.txv_dialog_Hora_Chamado_Valor_Ocorrencia);
                        final TextView txvDataChamada = (TextView) dialog.findViewById(R.id.txv_dialog_Data_Chamado_Valor_Ocorrencia);
                        final EditText edtEndereco = (EditText) dialog.findViewById(R.id.edt_dialog_Endereco_Ocorrencia);
                        final AutoCompleteTextView aucCidade = (AutoCompleteTextView) dialog.findViewById(R.id.auc_dialog_Cidade_Ocorrencia);
                        final AutoCompleteTextView aucBairro = (AutoCompleteTextView) dialog.findViewById(R.id.auc_dialog_Bairro_Ocorrencia);
                        final TextView txvNumIncidencia = (TextView) dialog.findViewById(R.id.txv_dialog_Incidencia);
                        final Spinner spnTipoOcorrencia = (Spinner) dialog.findViewById(R.id.spn_dialog_TipoOcorrencia);
                        final Spinner spnAnoIncidencia = (Spinner) dialog.findViewById(R.id.spn_dialog_Ano_Incidencia);
                        final Spinner spnTipoVia = (Spinner) dialog.findViewById(R.id.spn_Tipo_Via_Dialog_Ocorrencia);
                        final EditText edtNumIncidencia = (EditText) dialog.findViewById(R.id.edt_dialog_NumIncidencia);
                        final EditText edtComplemento = (EditText) dialog.findViewById(R.id.edt_Complemento_Dialog_Ocorrencia);
                        final StringBuilder incidencia = new StringBuilder("0000000");

                        edtNumIncidencia.setNextFocusRightId(edtEndereco.getId());
                        edtEndereco.setNextFocusRightId(edtComplemento.getId());
                        edtComplemento.setNextFocusRightId(aucCidade.getId());
                        aucCidade.setNextFocusRightId(aucBairro.getId());

                        aucCidade.setAdapter(AutoCompleteUtil.getCidades(MainActivity.this));
                        aucBairro.setAdapter(AutoCompleteUtil.getBairros(MainActivity.this));
                        aucCidade.setText("Fortaleza");

                        txvDataChamada.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                TempoUtil.setDate(txvDataChamada, MainActivity.this);
                            }
                        });

                        txvHoraChamada.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                TempoUtil.setTime(txvHoraChamada, MainActivity.this);
                            }
                        });

                        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                        Calendar c = Calendar.getInstance();

                        txvDataChamada.setText(format.format(c.getTime()));

                        format = new SimpleDateFormat("HH:mm");

                        txvHoraChamada.setText(format.format(c.getTime()));

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


                        ArrayList<String> tiposVia = new ArrayList<String>();

                        for (TipoVia tv : TipoVia.values())
                            tiposVia.add(tv.getValor());

                        spnTipoVia.setAdapter(new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_dropdown_item, tiposVia));

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

                        edtNumIncidencia.addTextChangedListener(new TextWatcher()
                        {
                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count)
                            {

                            }

                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after)
                            {

                            }

                            @Override
                            public void afterTextChanged(Editable s)
                            {
                                incidencia.setLength(0);
                                incidencia.insert(0, "0000000");
                                incidencia.replace(incidencia.length() - s.length(), incidencia.length(), s.toString());
                                txvNumIncidencia.setText("I" + AnoEscolhido + incidencia.toString());
                            }
                        });

//                        //Carrega os valores do Enum Documento Solicitacao no Spinner
//                        ArrayList<String> docs = new ArrayList<String>();
//
//                        for (DocumentoSolicitacao d : DocumentoSolicitacao.values())
//                        {
//                            docs.add(d.getValor());
//                        }

                        Button btnComecaPericia = (Button) dialog.findViewById(R.id.btn_dialog_IniciarPericia);

                        btnComecaPericia.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                if (OcorrenciaTransito.find(OcorrenciaTransito.class, "num_incidencia = ?", txvNumIncidencia.getText().toString()).size() > 0)
                                {
                                    txvNumIncidencia.setText(txvNumIncidencia.getText().toString() + " Já existe, favor tente outro número!");
                                    txvNumIncidencia.setTextColor(Color.RED);
                                    return;
                                }
                                if (OcorrenciaVida.find(OcorrenciaVida.class, "num_incidencia = ?", txvNumIncidencia.getText().toString()).size() > 0)
                                {
                                    txvNumIncidencia.setText(txvNumIncidencia.getText().toString() + " Já existe, favor tente outro número!");
                                    txvNumIncidencia.setTextColor(Color.RED);
                                    return;
                                }

                                if (edtNumIncidencia.getText().toString().length() > 0)
                                {
                                    Bundle bd = new Bundle();
                                    bd.putString("incidencia", txvNumIncidencia.getText().toString());
                                    bd.putString("endereco", edtEndereco.getText().toString());
                                    bd.putString("cidade", aucCidade.getText().toString());
                                    bd.putString("bairro", aucBairro.getText().toString());
                                    bd.putString("complemento", edtComplemento.getText().toString());
                                    bd.putString("tipoVia", spnTipoVia.getSelectedItem().toString());
                                    bd.putString("data", txvDataChamada.getText().toString());
                                    bd.putString("hora", txvHoraChamada.getText().toString());


                                    if (spnTipoOcorrencia.getSelectedItem().toString().equals("Trânsito"))
                                        NovaPericiaTransito(bd);

                                    if (spnTipoOcorrencia.getSelectedItem().toString().equals("Crime contra a Vida"))
                                        NovaPericiaVida(bd);

                                } else
                                    Toast.makeText(getApplicationContext(), "Preencha corretamente os dados!", Toast.LENGTH_LONG).show();
                            }
                        });

                        aucBairro.setDropDownVerticalOffset(-200);
                        aucCidade.setDropDownVerticalOffset(-200);

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

        Ocorrencia ocorrenciaDelete = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransito.getOcorrencia());

        adapter.remove((Ocorrencia) lstvOcorrencias.getAdapter().getItem(position));
        adapter.notifyDataSetChanged();

        //Deletar Entidades envolvidas com endereço: EnderecoTransito, Endereco, EnderecoVeiculo e OcorrenciaTransitoEndereco

        List<OcorrenciaTransitoEndereco> ocorrenciaEnderecos = OcorrenciaTransitoEndereco.find(OcorrenciaTransitoEndereco.class, "ocorrencia_transito = ?", ocorrenciaTransito.getId().toString());

        for (int i = 0; i < ocorrenciaEnderecos.size(); i++)
        {
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
            if (ov.getVeiculo() != null)
            {
                danosVeiculo = DanoVeiculo.find(DanoVeiculo.class, "veiculo = ?", ov.getVeiculo().getId().toString());
                for (DanoVeiculo dv : danosVeiculo)
                {
                    dv.getDano().delete();
                    dv.delete();
                }
                ov.getVeiculo().delete();
            }
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
                if (vc.getVestigio() != null)
                {
                    VestigioTransito v = vc.getVestigio();
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
            if (oev.getEnvolvidoVida() != null)
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
        txvPerito = (TextView) findViewById(R.id.txv_Perito);
        txvPaginas = (TextView) findViewById(R.id.txv_Pagina);

        btnAnterior = (Button) findViewById(R.id.btn_Anterior_Pagina);
        btnProxima = (Button) findViewById(R.id.btn_Proxima_Pagina);
    }

    public void NovaPericiaVida(Bundle bundle)
    {
        Ocorrencia ocorrenciaNova = new Ocorrencia();

        OcorrenciaVida ocorrenciaVida = new OcorrenciaVida();

        DocumentoOcorrencia documentoOcorrencia = new DocumentoOcorrencia(null, "");

        EnderecoVida enderecoVida = new EnderecoVida();

        OcorrenciaVidaEndereco ocorrenciaVidaEndereco = new OcorrenciaVidaEndereco();

        documentoOcorrencia.save();

        ocorrenciaVida.setDocumento(documentoOcorrencia);

        enderecoVida.setMunicipio(MunicipioBusiness.findByDescricao(bundle.getString("municipio")));

//        enderecoVida.setBairro(StringUtil.checkValue(bundle.getString("bairro"),-1,""));
//
//        enderecoVida.setMunicipio(StringUtil.checkValue(bundle.getString("cidade"),-1,""));

        enderecoVida.setComplemento(StringUtil.checkValue(bundle.getString("complemento"),-1,""));

        enderecoVida.setTipoVia(BuscadorEnum.BuscarTipoVia(StringUtil.checkValue(bundle.getString("tipoVia"),-1,"")));

        enderecoVida.setDescricaoEndereco(StringUtil.checkValue(bundle.getString("endereco"),-1,""));

        ocorrenciaVida.setNumIncidencia(bundle.getString("incidencia"));

        ocorrenciaVida.setHoraChamado(bundle.getString("hora"));

        ocorrenciaVida.setDataChamadoString(bundle.getString("data"));

        ocorrenciaNova.setTipoOcorrencia(TipoOcorrencia.VIDA);

        ocorrenciaNova.setPerito(perito);

        ocorrenciaVida.save();

        ocorrenciaNova.setOcorrenciaVida(ocorrenciaVida);

        ocorrenciaNova.save();

        ocorrenciaVida.setOcorrenciaID(ocorrenciaNova.getId());

        ocorrenciaVida.save();

        enderecoVida.save();

        ocorrenciaVidaEndereco.setEnderecoVida(enderecoVida);

        ocorrenciaVidaEndereco.setOcorrenciaVida(ocorrenciaVida);

        ocorrenciaVidaEndereco.save();


        Intent it = new Intent(MainActivity.this, ManterPericiaVida.class);

        it.putExtra("OcorrenciaId", ocorrenciaVida.getId());
        if(enderecoVida.getBairro()!=null)
        it.putExtra("Bairro", enderecoVida.getBairro().getDescricao());
        it.putExtra("FragmentPicker", "Ocorrência");

        startActivity(it);
    }

    public void NovaPericiaTransito(Bundle bundle)
    {
        Ocorrencia ocorrenciaNova = new Ocorrencia();

        OcorrenciaTransito ocorrenciaTransito = new OcorrenciaTransito();

        DocumentoOcorrencia documentoOcorrencia = new DocumentoOcorrencia(null, "");

        EnderecoTransito enderecoTransito = new EnderecoTransito();

        documentoOcorrencia.save();

        ocorrenciaTransito.setDocumento(documentoOcorrencia);

        enderecoTransito.setMunicipio(MunicipioBusiness.findByDescricao(bundle.getString("cidade")));

        enderecoTransito.setBairro(BairroBusiness.findByDescricao(bundle.getString("bairro")));

        enderecoTransito.setComplemento(bundle.getString("complemento"));

        enderecoTransito.setTipoVia(BuscadorEnum.BuscarTipoVia(bundle.getString("tipoVia")));

        enderecoTransito.setDescricaoEndereco(bundle.getString("endereco"));

        ocorrenciaTransito.setHoraChamado(bundle.getString("hora"));

        ocorrenciaTransito.setDataChamado(bundle.getString("data"));

        ocorrenciaTransito.setNumIncidencia(bundle.getString("incidencia"));

        //ocorrenciaTransito.save();

        ocorrenciaNova.setOcorrenciaTransito(ocorrenciaTransito);

        ocorrenciaNova.setTipoOcorrencia(TipoOcorrencia.TRANSITO);

        ocorrenciaNova.setPerito(perito);

        ocorrenciaTransito.save();

        ocorrenciaNova.save();

        ocorrenciaTransito.setOcorrencia(ocorrenciaNova.getId());

        ocorrenciaTransito.save();

        enderecoTransito.save();

        OcorrenciaTransitoEndereco ocorrenciaTransitoEndereco = new OcorrenciaTransitoEndereco();

        ocorrenciaTransitoEndereco.setOcorrencia(ocorrenciaTransito);

        ocorrenciaTransitoEndereco.setEndereco(enderecoTransito);

        ocorrenciaTransitoEndereco.save();

        Intent it = new Intent(MainActivity.this, ManterPericiaTransito.class);

        if(ocorrenciaTransito!=null && ocorrenciaTransito.getId()!=null)
        it.putExtra("OcorrenciaId", ocorrenciaTransito.getId());

        if(enderecoTransito!=null && enderecoTransito.getBairro()!=null && enderecoTransito.getBairro().getDescricao()!=null)
        it.putExtra("Bairro", enderecoTransito.getBairro().getDescricao());

        startActivity(it);
    }

    public void onBackPressed()
    {
        //IGNORE O BACKPRESSED PARA ANULAR O BOTÂO VOLTAR
    }
}
