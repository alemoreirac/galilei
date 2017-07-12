package com.example.pefoce.peritolocal;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.adapter.StepAdapter;

import Fragments.ColisoesFragment;
import Fragments.EnderecosFragment;
import Fragments.OcorrenciaFragment;
import Fragments.VeiculoFragment;
import Fragments.VitimasFragment;
import Model.OcorrenciaTransito;
import Util.TempoUtil;

public class PericiaTransito extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public OcorrenciaTransito ocorrenciaTransito;
    public StepperLayout stepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pericia_transito);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        toolbar.setTitle("Dados da Ocorrência");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        CarregaOcorrencia();
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }

        else
        {
            Intent it = new Intent(this,MainActivity.class);
            it.putExtra("PeritoId",ocorrenciaTransito.getPerito().getId());
            it.putExtra("OcorrenciaId",0);
             startActivity(it);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pericia_transito, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Bundle bd = new Bundle();
        bd.putLong("OcorrenciaId",ocorrenciaTransito.getId());

        // Pega o FragmentManager
        FragmentManager fm = this.getFragmentManager();

        // Abre uma transação e adiciona
        FragmentTransaction ft = fm.beginTransaction();

        if (id == R.id.nav_ocorrencia)
        {
            OcorrenciaFragment fragment = new OcorrenciaFragment();
            fragment.setArguments(bd);
            //ft.replace(R.id.fragment_content,fragment);
            ft.commit();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("Dados da Ocorrência");
          }
          else if (id == R.id.nav_enderecos)
          {
              EnderecosFragment fragment = new EnderecosFragment();
              fragment.setArguments(bd);
              Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
           //   ft.replace(R.id.fragment_content,fragment);
              ft.commit();
              toolbar.setTitle("Endereços");
          }
          else if (id == R.id.nav_veiculos)
          {
              VeiculoFragment fragment = new VeiculoFragment();
              fragment.setArguments(bd);
              Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
           //   ft.replace(R.id.fragment_content,fragment);
              ft.commit();
              toolbar.setTitle("Dados dos Veículos");
          }
        else if (id == R.id.nav_vitimas)
        {
            VitimasFragment fragment = new VitimasFragment();
            fragment.setArguments(bd);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         //   ft.replace(R.id.fragment_content,fragment);
            ft.commit();
            toolbar.setTitle("Dados das Vítimas");
        }
        else if (id == R.id.nav_colisao)
        {
            ColisoesFragment fragment = new ColisoesFragment();
            fragment.setArguments(bd);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //      ft.replace(R.id.fragment_content,fragment);
            ft.commit();
            toolbar.setTitle("Dados das Colisões");
        }
        else if (id == R.id.nav_galeria)
        {

        }
        else if (id == R.id.nav_sair)
        {

        }
        else if (id == R.id.nav_salvar)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


public void CarregaOcorrencia()
{

    Intent it = getIntent();

    // Pega o FragmentManager
    FragmentManager fm = this.getFragmentManager();

    // Abre uma transação e adiciona
    FragmentTransaction ft = fm.beginTransaction();

     Bundle args = new Bundle();

    if(it.getLongExtra("OcorrenciaId",0) != 0)
    {
        args.putLong("OcorrenciaId", it.getLongExtra("OcorrenciaId", 0));
        ocorrenciaTransito = OcorrenciaTransito.findById(OcorrenciaTransito.class, it.getLongExtra("OcorrenciaId", 0));
    }

    if(it.getStringExtra("FragmentPicker")!=null)
    {
        switch (it.getStringExtra("FragmentPicker"))
        {
            case "Endereço" :
                EnderecosFragment fragmentEndereco = new EnderecosFragment();
                //fragmentEndereco.setArguments(args);
              //  ft.replace(R.id.fragment_content,fragmentEndereco);
                ft.commit();
                break;

            case "Vítima" :
                VitimasFragment fragmentVitima = new VitimasFragment();
                //fragmentEndereco.setArguments(args);
            //    ft.replace(R.id.fragment_content,fragmentVitima);
                ft.commit();
                break;


            default:
                OcorrenciaFragment fragmentOcorrencia = new OcorrenciaFragment();
                //fragmentOcorrencia.setArguments(args);
              //  ft.replace(R.id.fragment_content,fragmentOcorrencia);
                ft.commit();
                break;
        }
    }
    else{
        OcorrenciaFragment fragmentOcorrencia = new OcorrenciaFragment();
        fragmentOcorrencia.setArguments(args);
    //    ft.replace(R.id.fragment_content,fragmentOcorrencia);
        ft.commit();
    }
}

@RequiresApi(api = Build.VERSION_CODES.N)
public void TrocaHora(View v)
{
    final TextView txv ;

    switch (v.getId())
    {
        case R.id.txv_Hora_Atendimento_Valor:
            txv = (TextView)v.findViewById(R.id.txv_Hora_Atendimento_Valor);
            TempoUtil.setTime(txv,this);

            break;

        case R.id.txv_HoraChamado:
            txv = (TextView)v.findViewById(R.id.txv_HoraChamado);
            TempoUtil.setTime(txv,this);

            break;

        case R.id.txv_HoraOcorrencia:
            txv = (TextView)v.findViewById(R.id.txv_HoraOcorrencia);
            TempoUtil.setTime(txv,this);

            break;
    default:
    break;

    }
}

    public void TrocaData(View v)
    {
        final TextView txv;
        switch (v.getId())
        {
            case R.id.txv_Data_Atendimento_Valor:
                txv = (TextView)v.findViewById(R.id.txv_Data_Atendimento_Valor);
                TempoUtil.setDate(txv,this);
                break;

            case R.id.txv_DataNascimentoVitima:
                txv = (TextView)v.findViewById(R.id.txv_DataNascimentoVitima);
                TempoUtil.setDate(txv,this);
                break;
        }
    }

    public void Salvar_Fechar (View v)
    {
        Toast.makeText(v.getContext(), "Hello toast!!", Toast.LENGTH_LONG).show();

    }

}
