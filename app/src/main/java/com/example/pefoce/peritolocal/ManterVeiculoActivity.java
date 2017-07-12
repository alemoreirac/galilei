package com.example.pefoce.peritolocal;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class ManterVeiculoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_veiculo);

        Button addProprietario = (Button)findViewById(R.id.btn_AddProprietario);

        addProprietario.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final Dialog dialog = new Dialog(ManterVeiculoActivity.this);
                dialog.setContentView(R.layout.dialog_proprietario);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

            };

        });

        Button addDano = (Button)findViewById(R.id.btn_Add_Dano);

        addDano.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                final Dialog dialog = new Dialog(ManterVeiculoActivity.this);
                dialog.setContentView(R.layout.dialog_dano);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

            };

        });

    }

}
