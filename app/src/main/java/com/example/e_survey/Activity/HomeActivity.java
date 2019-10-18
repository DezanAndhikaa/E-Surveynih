package com.example.e_survey.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_survey.R;
import com.example.e_survey.Util.SharedPreferenceCustom;

public class HomeActivity extends AppCompatActivity {

    private TextView tv_toolbar;
    LinearLayout ll_kios, ll_petani, ll_penyuluh, ll_klmpk_tani;
    ImageView iv_logout;
    SharedPreferenceCustom sharedPreferenceCustom;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);
//        Loader load = new Loader();
        tv_toolbar = findViewById(R.id.tv_toolbar);
        iv_logout = findViewById(R.id.iv_logout);
        ll_kios = findViewById(R.id.ll_kios);
        ll_petani = findViewById(R.id.ll_petani);
        ll_penyuluh = findViewById(R.id.ll_penyuluh);
        ll_klmpk_tani = findViewById(R.id.ll_klmpk_tani);

        tv_toolbar.setText("Menu");

        ll_kios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listkios = new Intent(HomeActivity.this, ListKiosActivity.class);
                startActivity(listkios);
            }
        });
        ll_petani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prof_petani = new Intent(HomeActivity.this, FormPetaniActivity.class);
                startActivity(prof_petani);
            }
        });
        ll_penyuluh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prof_penyuluh = new Intent(HomeActivity.this, FormPenyuluhActivity.class);
                startActivity(prof_penyuluh);
            }
        });
        ll_klmpk_tani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prof_klmpk_tani = new Intent(HomeActivity.this, FormKelompokTaniActivity.class);
                startActivity(prof_klmpk_tani);
            }
        });
        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClicker = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case DialogInterface.BUTTON_POSITIVE:
                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
                alert.setMessage("Apakah Anda yakin?").setPositiveButton("Iya", dialogClicker).setNegativeButton("Tidak", dialogClicker).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClicker = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Apakah Anda yakin?").setPositiveButton("Iya", dialogClicker).setNegativeButton("Tidak", dialogClicker).show();
    }
}