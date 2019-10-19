package com.example.e_survey.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import static com.example.e_survey.Util.Constant.KUESRB_URL;

public class FormKelompokTaniActivity extends AppCompatActivity {

    Button btnNext;
    EditText inNamaKlmpkTani, inKetuaKlmpkTani, inLuasLahan, inKomoditasUtama, inJlhAnggota;
    TextView tv_toolbar;
    RadioGroup rdGroup;
    RadioButton rdButton,rb_tani1,rb_tani2;
    SharedPreferenceCustom sharedPreferenceCustom;
    private RadioButton rbkeltan1, rbkeltan2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kelompok_tani);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);
        initFindView();
        hideKeyboardFrom();
        rb_tani1 = findViewById(R.id.rb_tani1);
        rb_tani2 = findViewById(R.id.rb_tani2);
    }
        private void initFindView () {
            inNamaKlmpkTani = findViewById(R.id.inNamaKlmpkTani);
            inKetuaKlmpkTani = findViewById(R.id.inKetuaKlmpkTani);
            inLuasLahan = findViewById(R.id.inLuasLahan);
            rdGroup = findViewById(R.id.rdGroup);
            inKomoditasUtama = findViewById(R.id.inKomoditasUtama);
            inJlhAnggota = findViewById(R.id.inJlhAnggota);
            tv_toolbar = findViewById(R.id.tv_toolbar);
            btnNext = findViewById(R.id.btnNext);

            tv_toolbar.setText("Profil Kelompok Tani");
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (inNamaKlmpkTani.getText().toString().equals("")) {
                        inNamaKlmpkTani.setError("Masukkan Nama Kelompok Tani!");
                        Toast.makeText(getApplicationContext(), "\t\t\tNama Kelompok \n Tani tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    } else if (inKetuaKlmpkTani.getText().toString().equals("")) {
                        inKetuaKlmpkTani.setError("Masukkan Nama Ketua Kelompok Tani!");
                        Toast.makeText(getApplicationContext(), "\tNama Ketua Kelompok \nTani tidak boleh kososng", Toast.LENGTH_SHORT).show();
                    } else if (inLuasLahan.getText().toString().equals("")) {
                        inLuasLahan.setError("Masukkan Luas Lahan!");
                        Toast.makeText(getApplicationContext(), "\t\t\t\tLuas Lahan\n tidak boleh kososng", Toast.LENGTH_SHORT).show();
                    } else if (inKomoditasUtama.getText().toString().equals("")) {
                        inKomoditasUtama.setError("Masukkan Komoditas Utama!");
                        Toast.makeText(getApplicationContext(), "\tKomoditas Utama\n tidak boleh kososng", Toast.LENGTH_SHORT).show();
                    } else if (inJlhAnggota.getText().toString().equals("")) {
                        inJlhAnggota.setError("Masukkan Jumlah Anggota!");
                        Toast.makeText(getApplicationContext(), "\t\tJumlah Anggota\n tidak boleh kososng", Toast.LENGTH_SHORT).show();
                    } else {
                        JSONObject kelompoktani = new JSONObject();
                        try {
                            int selectedID = rdGroup.getCheckedRadioButtonId();
                            rdButton = findViewById(selectedID);

                            kelompoktani.put("nama_klmpk_tani", inNamaKlmpkTani.getText().toString());
                            kelompoktani.put("ketua_klmpk_tani", inKetuaKlmpkTani.getText().toString());
                            kelompoktani.put("luas_lahan", inLuasLahan.getText().toString());
                            if (selectedID == 1) {
                                kelompoktani.put("status_klmpk_tani", rdButton.getText().toString());
                            } else if (selectedID == 2) {
                                kelompoktani.put("status_klmpk_tani", rdButton.getText().toString());
                            }

                            sharedPreferenceCustom.putSharedPref(Constant.FORM_KEL_TANI, kelompoktani.toString());
                            narikData2();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        sharedPreferenceCustom.putSharedPref(Constant.FORM_KEL_TANI, inNamaKlmpkTani.getText().toString());

                    }
                }
            });
        }


    public void hideKeyboardFrom() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void checkButton(View view) {
        int selectedID = rdGroup.getCheckedRadioButtonId();
        rdButton = findViewById(selectedID);
    }

    void narikData2() {
        Soal.listObj.clear();
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

                        if (kategori.equals("Kelompok Tani")) {
                            Soal.listObj.add(oData);
                            Soal.listCode.add(oData.getString("code_kuisioner"));
                        }
                    }

                    JSONObject objData = Soal.listObj.get(0);

                    String getJenisJawbaan = objData.getString("jenis_pertanyaan");

                    if (getJenisJawbaan.equals("isian")) {
                        Intent intent = new Intent(FormKelompokTaniActivity.this, KuesionerTipeInActivity.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                        Intent intent = new Intent(FormKelompokTaniActivity.this, kuisioner_pg.class);
                        intent.putExtra("jawabA", objData.getString("pilihanA"));
                        intent.putExtra("jawabB", objData.getString("pilihanB"));
                        intent.putExtra("jawabC", objData.getString("pilihanC"));
                        intent.putExtra("jawabD", objData.getString("pilihanD"));

                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("yesno")) {
                        Intent intent = new Intent(FormKelompokTaniActivity.this, kuisioner_yn.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("checkbox")) {
                        Intent intent = new Intent(FormKelompokTaniActivity.this, kuisioner_cb.class);
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

    public void taniButtonA (View v){
        rb_tani1.setChecked(true);
        rb_tani2.setChecked(false);
    }
    public void taniButtonB (View v){
        rb_tani1.setChecked(false);
        rb_tani2.setChecked(true);
    }
}
