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

    EditText inIDTani, inNamaPetani, inAlamat, inNoHP, inDesa, inLuasLahan, inStatusLahan, inKlmpkTani, inKomoditas;
    Button btnNext;
    TextView tv_toolbar;
    SharedPreferenceCustom sharedPreferenceCustom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_petani);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);
        initFindView();
        hideKeyboardFrom();
    }

    private void initFindView() {
        inIDTani = findViewById(R.id.inIDTani);
        inNamaPetani = findViewById(R.id.inNamaPetani);
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
                    JSONObject kelompoktani = new JSONObject();
                    try {
                        kelompoktani.put("id_petani", inIDTani.getText().toString());
                        kelompoktani.put("nama_petani", inNamaPetani.getText().toString());
                        kelompoktani.put("alamat", inAlamat.getText().toString());
                        kelompoktani.put("no_hp", inNoHP.getText().toString());
                        kelompoktani.put("nama_desa", inDesa.getText().toString());
                        kelompoktani.put("luas_lahan", inLuasLahan.getText().toString());
                        kelompoktani.put("status_lahan", inStatusLahan.getText().toString());
                        kelompoktani.put("kelompok_tani", inKlmpkTani.getText().toString());
                        kelompoktani.put("komoditas", inKomoditas.getText().toString());

                        sharedPreferenceCustom.putSharedPref(Constant.FORM_PETANI, kelompoktani.toString());
////                        Log.d("Data JSON: ", kelompoktani.toString());
//                        List objList = new ArrayList();
//                        objList.add("obj1");
//                        objList.add("obj2");
//                        objList.add("obj3");
//                        List objList2 = new ArrayList();
//                        objList2.add("obj1");
//                        objList2.add("obj2");
//                        objList2.add("obj3");
//                        String append = "{";
//                        for (int x = 0; x < objList.size(); x++) {
//
//                            append += "\""+ objList.get(x) + "\":" + "\"" + objList2.get(x) + "\",";
//                            if (x == (objList.size() - 1)){
//                                append += "\""+ objList.get(x) + "\":" + "\"" + objList2.get(x) + "\"}";
////                                append += "}";
//                            }
//                        }
////                        Log.d("Value Identitas : " , kelompoktani.toString());
////                        Log.d("Value Jawaban : " , append);
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }
                    Soal.kategoriKuis = "Petani";
                    narikData2();
                    sharedPreferenceCustom.putSharedPref(Constant.FORM_PETANI, inNamaPetani.getText().toString());
                }
            }
        });
    }


    public void hideKeyboardFrom() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    void narikData() {
        RequestQueue req = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, KUESRB_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    JSONObject objData = data.getJSONObject(0);
                    Soal.parameter++;

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
                        Intent intent = new Intent(FormPetaniActivity.this, KuesionerTipeYnActivity.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("checkbox")) {
                        Intent intent = new Intent(FormPetaniActivity.this, KuesionerTipeYnActivity.class);
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        req.add(request);
    }

    void narikData2() {
        RequestQueue req = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, KUESRB_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    Soal.parameter++;

                    for (int a = 0; a < data.length(); a++) {
                        JSONObject oData = data.getJSONObject(a);
                        String kategori = oData.getString("nama_kategori_kuisioner");

                        if (kategori.equals("Petani")) {
                            Soal.listObj.add(oData);
                            Soal.listCode.add(oData.getString("code_kuisioner"));
                        }
                    }

                    JSONObject objData = Soal.listObj.get(0);

                    String getJenisJawbaan = objData.getString("jenis_pertanyaan");

                    if (getJenisJawbaan.equals("isian")) {
                        Intent intent = new Intent(FormPetaniActivity.this, KuesionerTipeInActivity.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                        Intent intent = new Intent(FormPetaniActivity.this, kuisioner_pg.class);
                        intent.putExtra("jawabA", objData.getString("pilihanA"));
                        intent.putExtra("jawabB", objData.getString("pilihanB"));
                        intent.putExtra("jawabC", objData.getString("pilihanC"));
                        intent.putExtra("jawabD", objData.getString("pilihanD"));

                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("yesno")) {
                        Intent intent = new Intent(FormPetaniActivity.this, kuisioner_yn.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("checkbox")) {
                        Intent intent = new Intent(FormPetaniActivity.this, kuisioner_cb.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("jawabA", objData.getString("pilihanCB1"));
                        intent.putExtra("jawabB", objData.getString("pilihanCB2"));
                        intent.putExtra("jawabC", objData.getString("pilihanCB3"));
                        intent.putExtra("jawabD", objData.getString("pilihanCB4"));
                        intent.putExtra("jawabE", objData.getString("pilihanCB5"));

                        startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        req.add(request);
    }
}