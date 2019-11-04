package com.example.e_survey.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.e_survey.DatabaseLokal.DataHelper;
import com.example.e_survey.Model.Cache.Draft;
import com.example.e_survey.Model.Cache.Logs;
import com.example.e_survey.R;

import java.util.List;

public class Historia extends AppCompatActivity {
    public EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);

        DataHelper dbs = new DataHelper(getApplicationContext());
        text= findViewById(R.id.textLog);
        String hasil = "";
        Log.d("reading", "reading all data");
        List<Logs> listBuku = dbs.findAllLog();
        for (Logs b : listBuku) {
            hasil+= "Jenis Aksi: " + b.getKEY_NamaAksi() + "\nNama Entitas: "+b.getKEY_NamaDesa()+"\nTanggal Aksi: "+ b.getKEY_TanggalAksi()+"\nJam Aksi: "+b.getKEY_JamAksi()+"\n==================\n";
        }

        text.setText(hasil);
        text.setEnabled(false);
    }

    public void bek(View v){
        Intent intent = new Intent(Historia.this, HomeActivity.class);
        startActivity(intent);
    }
}
