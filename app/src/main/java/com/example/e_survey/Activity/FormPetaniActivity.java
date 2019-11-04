package com.example.e_survey.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.DatabaseLokal.DataHelper;
import com.example.e_survey.R;
import com.example.e_survey.Util.Constant;
import com.example.e_survey.Util.SharedPreferenceCustom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.e_survey.Util.Constant.KUESRB_URL;

public class FormPetaniActivity extends AppCompatActivity {

    EditText inIDTani, inNamaPetani, inUsiaPetani, inAlamat, inNoHP, inDesa, inLuasLahan, inStatusLahan, inKlmpkTani, inKomoditas;
    Button btnNext;
    TextView tv_toolbar;
    SharedPreferenceCustom sharedPreferenceCustom;
    DataHelper dbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_petani);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);
        initFindView();
        hideKeyboardFrom();
        dbs = new DataHelper(getApplicationContext());
    }

    private void initFindView() {
        inIDTani = findViewById(R.id.inIDTani);
        inNamaPetani = findViewById(R.id.inNamaPetani);
        inUsiaPetani = findViewById(R.id.inUsiaPetani);
        inAlamat = findViewById(R.id.inAlamat);
        inNoHP = findViewById(R.id.inNoHP);
        inDesa = findViewById(R.id.inDesa);
        inLuasLahan = findViewById(R.id.inLuasLahan);
        inStatusLahan = findViewById(R.id.inStatusLahan);
        inKlmpkTani = findViewById(R.id.inKlmpkTani);
        inKomoditas = findViewById(R.id.inKomoditas);
        tv_toolbar = findViewById(R.id.tv_toolbar);
        btnNext = findViewById(R.id.btnNext);

        tv_toolbar.setText("Profil Petani");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (inIDTani.getText().toString().equals("")) {
                    inIDTani.setError("Masukkan ID Petani!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t\t ID Petani\n Tani tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (inNamaPetani.getText().toString().equals("")) {
                    inNamaPetani.setError("Masukkan Nama Petani!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t Nama Petani\nTani tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (inNamaPetani.getText().toString().equals("")) {
                    inNamaPetani.setError("Masukkan Usia Petani!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t Usia Petani\nTani tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (inAlamat.getText().toString().equals("")) {
                    inAlamat.setError("Masukkan Alamat!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t\tAlamat\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (inNoHP.getText().toString().equals("")) {
                    inNoHP.setError("Masukkan Nomor HP!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\tNomor HP\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (inDesa.getText().toString().equals("")) {
                    inDesa.setError("Masukkan Nama Desa!");
                    Toast.makeText(getApplicationContext(), "\t\t\t Nama Desa\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (inLuasLahan.getText().toString().equals("")) {
                    inLuasLahan.setError("Masukkan Luas Lahan!");
                    Toast.makeText(getApplicationContext(), "\t\t\tLuas Lahan\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (inStatusLahan.getText().toString().equals("")) {
                    inStatusLahan.setError("Masukkan Status Lahan!");
                    Toast.makeText(getApplicationContext(), "\t\t\tStatus Lahan\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (inKlmpkTani.getText().toString().equals("")) {
                    inKlmpkTani.setError("Masukkan Luas Lahan!");
                    Toast.makeText(getApplicationContext(), "\tNama Kelompok Tani\n\t tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (inKomoditas.getText().toString().equals("")) {
                    inKomoditas.setError("Masukkan Komoditas!");
                    Toast.makeText(getApplicationContext(), "\t\t\t Komoditas\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        Soal.jsonIdentitas.put("id_petani", inIDTani.getText().toString());
                        Soal.jsonIdentitas.put("nama_petani", inNamaPetani.getText().toString());
                        Soal.jsonIdentitas.put("usia_petani", inUsiaPetani.getText().toString());
                        Soal.jsonIdentitas.put("alamat", inAlamat.getText().toString());
                        Soal.jsonIdentitas.put("no_hp", inNoHP.getText().toString());
                        Soal.jsonIdentitas.put("nama_desa", inDesa.getText().toString());
                        Soal.jsonIdentitas.put("luas_lahan", inLuasLahan.getText().toString());
                        Soal.jsonIdentitas.put("status_lahan", inStatusLahan.getText().toString());
                        Soal.jsonIdentitas.put("kelompok_tani", inKlmpkTani.getText().toString());
                        Soal.jsonIdentitas.put("komoditas", inKomoditas.getText().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Soal.kategoriKuis = "Petani";
                    narikData2();
                }
            }
        });
    }


    public void hideKeyboardFrom() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    void narikData2() {
        Log.d("soal", dbs.cekKuesioner());
        Soal.listObj.clear();
        try {
            JSONArray data = new JSONArray(dbs.cekKuesioner());
            Soal.parameter++;

            for (int a = 0; a < data.length(); a++) {
                JSONObject oData = data.getJSONObject(a);
                String kategori = oData.getString("nama_kategori_kuisioner");

                if (kategori.equals("Petani")) {
                    Soal.listObj.add(oData);
                }
            }

            JSONObject objData = Soal.listObj.get(0);

            String getJenisJawbaan = objData.getString("jenis_pertanyaan");

            if (getJenisJawbaan.equals("isian")) {
                Intent intent = new Intent(FormPetaniActivity.this, KuesionerTipeInActivity.class);
                intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                intent.putExtra("kode_soal", objData.getString("code_kuisioner"));
                startActivity(intent);
            } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                Intent intent = new Intent(FormPetaniActivity.this, kuisioner_pg.class);
                intent.putExtra("jawabA", objData.getString("pilihanA"));
                intent.putExtra("jawabB", objData.getString("pilihanB"));
                intent.putExtra("jawabC", objData.getString("pilihanC"));
                intent.putExtra("jawabD", objData.getString("pilihanD"));
                intent.putExtra("kode_soal", objData.getString("code_kuisioner"));
                intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                startActivity(intent);
            } else if (getJenisJawbaan.equals("yesno")) {
                Intent intent = new Intent(FormPetaniActivity.this, kuisioner_yn.class);
                intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                intent.putExtra("kode_soal", objData.getString("code_kuisioner"));
                startActivity(intent);
            } else if (getJenisJawbaan.equals("checkbox")) {
                Intent intent = new Intent(FormPetaniActivity.this, kuisioner_cb.class);
                intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                intent.putExtra("jawabA", objData.getString("pilihanCB1"));
                intent.putExtra("jawabB", objData.getString("pilihanCB2"));
                intent.putExtra("jawabC", objData.getString("pilihanCB3"));
                intent.putExtra("jawabD", objData.getString("pilihanCB4"));
                intent.putExtra("jawabE", objData.getString("pilihanCB5"));
                intent.putExtra("kode_soal", objData.getString("code_kuisioner"));
                startActivity(intent);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}