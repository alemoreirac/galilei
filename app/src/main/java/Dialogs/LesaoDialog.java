package Dialogs;


import android.app.Activity;
import android.app.Dialog;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.pefoce.peritolocal.GerenciarBracos;
import com.example.pefoce.peritolocal.GerenciarCabeca;
import com.example.pefoce.peritolocal.GerenciarPernas;
import com.example.pefoce.peritolocal.GerenciarTorax;
import com.example.pefoce.peritolocal.R;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

import Enums.Vida.LesoesEnabled;
import Enums.Vida.LocalizacaoLesao;
import Enums.Vida.NaturezaLesao;
import Enums.Vida.ParteCorpo;
import Enums.Vida.Secao;
import Model.Vida.EnvolvidoVida;
import Model.Vida.Lesao;
import Model.Vida.LesaoEnvolvido;
import Util.BuscadorEnum;

/**
 * Created by Pefoce on 15/01/2018.
 */

public class LesaoDialog
{

  static  Dialog dialog;
  static  public Context context = null;
  static  public Spinner spnNaturezaLesao, spnLocalLesao;
  static  public CheckBox cxbCompatibilidade;
  static  public Button btnLimparLesao, btnSalvarLesao, btnAddLesao;
  static  public ListView lstvLesoes;
  static  public ArrayAdapter<Lesao> adapterLesoes;
  static  public ArrayList<Lesao> lesoesModelNovas;
  static  public ArrayList<Lesao> lesoesModel;
  static  public List<LesaoEnvolvido> lesaoEnvolvidoList;
  static  public EnvolvidoVida envolvidoVida;
  static  Secao secao;
    static  ParteCorpo parteCorpo;
    static  LesoesEnabled lesoesEnabled;

    public static void show(Context ctx, EnvolvidoVida envolvidoVida_, Secao secao_, LesoesEnabled le)
    {
        context = ctx;
        lesoesEnabled = le;
        parteCorpo = BuscadorEnum.EncontrarParteCorpo(secao_);
        secao = secao_;
        envolvidoVida = envolvidoVida_;

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_lesao_detalhe);
        dialog.setTitle("Lesões: " + secao.getValor());
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        AssociarLayout();
        AssociarEventos();
        CarregarLesoes();





    }


    private static void AssociarLayout()
    {
        spnLocalLesao = (Spinner) dialog.findViewById(R.id.spn_dialog_Localizacao_Lesao);
        spnNaturezaLesao = (Spinner) dialog.findViewById(R.id.spn_dialog_Natureza_Lesao);

        cxbCompatibilidade = (CheckBox) dialog.findViewById(R.id.cxb_dialog_CompatibilidadeLesao);
        lstvLesoes = (ListView) dialog.findViewById(R.id.lstv_dialog_ListLesoes);

        btnAddLesao = (Button) dialog.findViewById(R.id.btn_dialog_AddLesao);
        btnLimparLesao = (Button) dialog.findViewById(R.id.btn_dialog_LimparLesao);
        btnSalvarLesao = (Button) dialog.findViewById(R.id.btn_dialog_SalvarLesao_Detalhe);

        ArrayList<String> locaisLesao = new ArrayList<String>();

        if(lesoesEnabled!=null)
        {
            switch (lesoesEnabled)
            {
                case TODOS:
                for (LocalizacaoLesao lesao : LocalizacaoLesao.values())
                    locaisLesao.add(lesao.getValor());
                break;
                case ANTERIOR:
                    locaisLesao.add(LocalizacaoLesao.FRONTAL.getValor());
                    spnLocalLesao.setEnabled(false);
                    break;
                case POSTERIOR:
                    locaisLesao.add(LocalizacaoLesao.TRASEIRA.getValor());
                    spnLocalLesao.setEnabled(false);
                    break;
            }
        }
        else
        {
            for (LocalizacaoLesao lesao : LocalizacaoLesao.values())
                locaisLesao.add(lesao.getValor());
        }

        spnLocalLesao.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, locaisLesao));

        ArrayList<String> naturezasLesao = new ArrayList<>();

        for (NaturezaLesao nl : NaturezaLesao.values())
            naturezasLesao.add(nl.getValor());

        spnNaturezaLesao.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, naturezasLesao));
    }

    private static void CarregarLesoes()
    {
        lesaoEnvolvidoList = LesaoEnvolvido.find(LesaoEnvolvido.class, "envolvido_vida = ?", envolvidoVida.getId().toString());

        lesoesModel = new ArrayList<>();
        lesoesModelNovas = new ArrayList<>();

        for (LesaoEnvolvido le : lesaoEnvolvidoList)
        {
            if (le.getLesao() != null)
            {
                if (le.getLesao().getSecaoLesao() != null)
                {
                    if (le.getLesao().getSecaoLesao() == secao)
                        lesoesModel.add(le.getLesao());
                }
            }
        }
        adapterLesoes = new ArrayAdapter<Lesao>(context, R.layout.support_simple_spinner_dropdown_item, lesoesModel);

        lstvLesoes.setAdapter(adapterLesoes);


        if (lesaoEnvolvidoList.size() > 0)
        {
            ParteCorpo pc = BuscadorEnum.EncontrarParteCorpo(lesaoEnvolvidoList.get(0).getLesao().getSecaoLesao());

            if (pc.equals(ParteCorpo.CABECA) || pc.equals(ParteCorpo.TORAX))

//                spnLocalLesao.setSelection(BuscadorEnum.getIndex(spnLocalLesao, LocalizacaoLesao.FRONTAL.getValor()));
                spnLocalLesao.setEnabled(false);


//            if (pc.equals(ParteCorpo.TORAX))
//            {
//                ArrayList<String> locaisLesao = new ArrayList<String>();
//
//                for (LocalizacaoLesao lesao : LocalizacaoLesao.values())
//                {
//                    if(!lesao.equals(LocalizacaoLesao.TRASEIRA))
//                    locaisLesao.add(lesao.getValor());
//                }
//
//                spnLocalLesao.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, locaisLesao));
//            }

        }
    }

    private static void AssociarEventos()
    {
        btnAddLesao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Lesao lesao = new Lesao();

                lesao.setParteCorpo(parteCorpo);
                lesao.setNatureza(BuscadorEnum.BuscarNaturezaLesao(spnNaturezaLesao.getSelectedItem().toString()));
                lesao.setSecaoLesao(secao);
                lesao.setLocalizacaoLesao(BuscadorEnum.BuscarLocalizacaoLesao(spnLocalLesao.getSelectedItem().toString()));
                lesao.setCompatibilidade(cxbCompatibilidade.isChecked());

                lesoesModel.add(lesao);

                adapterLesoes.notifyDataSetChanged();
            }
        });

        btnLimparLesao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

//                    for(int i = 0;i<lesoesModelNovas.size();i++)
//                        lesoesModelNovas.remove(i);

                for (int i = 0; i < lesoesModel.size(); i++)
                {
                    try
                    {
                        //lesoesModel.get(i).delete();
                        LesaoEnvolvido lesaoEnvolvido = LesaoEnvolvido.find(LesaoEnvolvido.class, "lesao = ?", lesoesModel.get(i).getId().toString()).get(i);
                        lesaoEnvolvido.getLesao().delete();
                        lesaoEnvolvido.delete();
                        lesoesModel.remove(i);
                    } catch (Exception e)
                    {
                        lesoesModel.remove(i);
                    }
                }
                adapterLesoes.clear();
            }
        });

        btnSalvarLesao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                for (LesaoEnvolvido le : lesaoEnvolvidoList)
                {
                    if (le.getLesao() != null)
                    {
                        if (le.getLesao().getSecaoLesao() != null)
                        {
                            if (le.getLesao().getSecaoLesao() == secao)
                                le.delete();
                        }
                    }
                }

                for (int i = 0; i < lesoesModel.size(); i++)
                {
                    LesaoEnvolvido lesaoEnvolvido = new LesaoEnvolvido(lesoesModel.get(i), envolvidoVida);
                    lesoesModel.get(i).save();
                    lesaoEnvolvido.save();
                }

                switch (context.getClass().getSimpleName())
                {
                    case "GerenciarBracos":
                        GerenciarBracos gerenciarBracos = (GerenciarBracos) context;
                        gerenciarBracos.AtualizarLesoes();
                        //gerenciarBracos.A)
                        break;
                    case "GerenciarPernas":
                        GerenciarPernas gerenciarPernas = (GerenciarPernas) context;
                        gerenciarPernas.AtualizarLesoes();
                        break;
                    case "GerenciarTorax":
                        GerenciarTorax gerenciarTorax = (GerenciarTorax) context;
                        gerenciarTorax.AtualizarLesoes();
                        break;
                    case "GerenciarCabeca":
                        GerenciarCabeca gerenciarCabeca = (GerenciarCabeca) context;
                        gerenciarCabeca.AtualizarLesoes();
                        break;
                }

                dialog.dismiss();
            }
        });
    }


}
