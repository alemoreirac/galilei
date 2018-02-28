package Dialogs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pefoce.peritolocal.R;

import java.util.ArrayList;
import java.util.List;

import Enums.Vida.PosicaoBraco;
import Enums.Vida.PosicaoCabeca;
import Enums.Vida.PosicaoPerna;
import Enums.Vida.PosicaoTorax;
import Fragments.FragmentsVida.GerenciarEnderecoVida;
import Fragments.FragmentsVida.GerenciarEnvolvidoVida;
import Model.Vida.EnderecoVida;
import Model.Vida.EnvolvidoVida;
import Util.BuscadorEnum;

/**
 * Created by Pefoce on 10/01/2018.
 */


@SuppressLint("ValidFragment")
public class PosicaoCadaverDialog //extends android.support.v4.app.DialogFragment

{
    Spinner spnCabeca;
    Spinner spnTorax;
    Spinner spnBracoDireito;
    Spinner spnBracoEsquerdo;
    Spinner spnPernaDireita;
    Spinner spnPernaEsquerda;

    Button btnCancelar;
    Button btnSalvar;

    TextView txvResultado;

    Fragment fragment;
    Activity activity = null;
    Context context;

    ArrayList<String> resultado = null;

    EnvolvidoVida envolvidoVida;

    // OcorrenciaTransito ot;
    Dialog dialog;

    @SuppressLint("ValidFragment")
    public PosicaoCadaverDialog(Fragment f, Activity a)
    {
        super();
        fragment = f;
        context = a;

        dialog = new Dialog(a);
        dialog.setContentView(R.layout.dialog_posicao_cadaver);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Posição do Cadáver");
        dialog.show();

        envolvidoVida = ((GerenciarEnvolvidoVida)f).envolvidoVida;

        activity = a;
        //   ot = ((ManterPericiaTransito) context).ocorrenciaTransito;

        resultado = new ArrayList<>();
        for(int i = 0;i<6;i++)
            resultado.add("");

        AssociarLayout();
        AssociarEventos();
        CarregarPosicoes();
    }

    private void CarregarPosicoes()
    {
        if(envolvidoVida.getPosicaoBracoDireito()!=null)
            spnBracoDireito.setSelection(BuscadorEnum.getIndex(spnBracoDireito,envolvidoVida.getPosicaoBracoDireito().getValor()));

        if(envolvidoVida.getPosicaoBracoEsquerdo()!=null)
            spnBracoEsquerdo.setSelection(BuscadorEnum.getIndex(spnBracoEsquerdo,envolvidoVida.getPosicaoBracoEsquerdo().getValor()));

        if(envolvidoVida.getPosicaoPernaDireita()!=null)
            spnPernaDireita.setSelection(BuscadorEnum.getIndex(spnPernaDireita,envolvidoVida.getPosicaoPernaDireita().getValor()));

        if(envolvidoVida.getPosicaoPernaEsquerda()!=null)
            spnPernaEsquerda.setSelection(BuscadorEnum.getIndex(spnPernaEsquerda,envolvidoVida.getPosicaoPernaEsquerda().getValor()));

        if(envolvidoVida.getPosicaoCabeca()!=null)
            spnCabeca.setSelection(BuscadorEnum.getIndex(spnCabeca,envolvidoVida.getPosicaoCabeca().getValor()));

        if(envolvidoVida.getPosicaoCorpo()!=null)
            spnTorax.setSelection(BuscadorEnum.getIndex(spnTorax,envolvidoVida.getPosicaoCorpo().getValor()));
    }


    private void AssociarLayout()
    {
        spnBracoDireito = (Spinner) dialog.findViewById(R.id.spn_Posicao_Braco_Direito);
        spnBracoEsquerdo= (Spinner) dialog.findViewById(R.id.spn_Posicao_Braco_Esquerdo);
        spnPernaDireita = (Spinner) dialog.findViewById(R.id.spn_Posicao_Perna_Direita);
        spnPernaEsquerda= (Spinner) dialog.findViewById(R.id.spn_Posicao_Perna_Esquerda);
        spnCabeca = (Spinner) dialog.findViewById(R.id.spn_Posicao_Cabeca);
        spnTorax = (Spinner) dialog.findViewById(R.id.spn_Posicao_Torax);

        btnCancelar = (Button) dialog.findViewById(R.id.btn_Cancelar_Posicao_Corpo);
        btnSalvar = (Button) dialog.findViewById(R.id.btn_Salvar_Posicao_Corpo);

        txvResultado = (TextView) dialog.findViewById(R.id.txv_Resultado_Posicao);


        List<String> posicoesBracos = new ArrayList<>();

        for(PosicaoBraco pb : PosicaoBraco.values())
            posicoesBracos.add(pb.getValor());

        spnBracoEsquerdo.setAdapter(new ArrayAdapter<String>(activity,R.layout.support_simple_spinner_dropdown_item,posicoesBracos));
        spnBracoDireito.setAdapter(new ArrayAdapter<String>(activity,R.layout.support_simple_spinner_dropdown_item,posicoesBracos));

        List<String> posicoesPernas = new ArrayList<>();

        for(PosicaoPerna pp : PosicaoPerna.values())
            posicoesPernas.add(pp.getValor());

        spnPernaEsquerda.setAdapter(new ArrayAdapter<String>(activity,R.layout.support_simple_spinner_dropdown_item,posicoesPernas));
        spnPernaDireita.setAdapter(new ArrayAdapter<String>(activity,R.layout.support_simple_spinner_dropdown_item,posicoesPernas));


        List<String> posicoesCabeca = new ArrayList<>();

        for(PosicaoCabeca pc : PosicaoCabeca.values())
            posicoesCabeca.add(pc.getValor());

        spnCabeca.setAdapter(new ArrayAdapter<String>(activity,R.layout.support_simple_spinner_dropdown_item,posicoesCabeca));


        List<String> posicoesTorax = new ArrayList<>();

        for(PosicaoTorax pt : PosicaoTorax.values())
            posicoesTorax.add(pt.getValor());

        spnTorax.setAdapter(new ArrayAdapter<String>(activity,R.layout.support_simple_spinner_dropdown_item,posicoesTorax));

    }

    public void AtualizarResultado()
    {
        StringBuilder stringBuilder = new StringBuilder();

        if(resultado.get(0)!=null)
            stringBuilder.append("O tórax estava na posição de " + spnTorax.getSelectedItem().toString()+";\n");
        if(resultado.get(1)!=null)
            stringBuilder.append("A cabeça do cadáver estava "+spnCabeca.getSelectedItem().toString()+";\n");
        if(resultado.get(2)!=null)
            stringBuilder.append("O braço esquerdo da vítima estava "+spnBracoEsquerdo.getSelectedItem().toString()+";\n");
        if(resultado.get(3)!=null)
            stringBuilder.append("O braço direito da vítima estava " + spnBracoDireito.getSelectedItem().toString()+";\n");
        if(resultado.get(4)!=null)
            stringBuilder.append("A perna esquerda da vítima estava "+spnPernaEsquerda.getSelectedItem().toString()+";\n");
        if(resultado.get(5)!=null)
            stringBuilder.append("A perna direita da vítima estava "+spnPernaDireita.getSelectedItem().toString()+".");

        txvResultado.setText(stringBuilder.toString());
    }

    public void AssociarEventos()
    {
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                AtualizarResultado();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        };

        spnTorax.setOnItemSelectedListener(listener);
        spnPernaDireita.setOnItemSelectedListener(listener);
        spnPernaEsquerda.setOnItemSelectedListener(listener);
        spnBracoEsquerdo.setOnItemSelectedListener(listener);
        spnBracoDireito.setOnItemSelectedListener(listener);
        spnCabeca.setOnItemSelectedListener(listener);

        btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bd = new Bundle();

                bd.putString("BracoDireito",spnBracoDireito.getSelectedItem().toString());
                bd.putString("BracoEsquerdo",spnBracoEsquerdo.getSelectedItem().toString());
                bd.putString("PernaDireita",spnPernaDireita.getSelectedItem().toString());
                bd.putString("PernaEsquerda",spnPernaEsquerda.getSelectedItem().toString());
                bd.putString("Cabeca",spnCabeca.getSelectedItem().toString());
                bd.putString("Torax",spnTorax.getSelectedItem().toString());


                ((GerenciarEnvolvidoVida)fragment).InterfacePosicaoCadaver(bd);

                dialog.dismiss();
            }
        });
    }
}
