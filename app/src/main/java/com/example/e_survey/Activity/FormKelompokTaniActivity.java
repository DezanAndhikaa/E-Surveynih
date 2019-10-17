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

import com.example.e_survey.R;
import com.example.e_survey.Util.Constant;
import com.example.e_survey.Util.SharedPreferenceCustom;

import org.json.JSONException;
import org.json.JSONObject;

public class FormKelompokTaniActivity extends AppCompatActivity {

    Button btnNext;
    EditText inNamaKlmpkTani, inKetuaKlmpkTani, inLuasLahan, inKomoditasUtama, inJlhAnggota;
    TextView tv_toolbar;
    RadioGroup rdGroup;
    RadioButton rdButton;
    SharedPreferenceCustom sharedPreferenceCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kelompok_tani);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);
        initFindView();
        hideKeyboardFrom();
    }

    private void initFindView() {
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    sharedPreferenceCustom.putSharedPref(Constant.FORM_KEL_TANI, inNamaKlmpkTani.getText().toString());

//                    Intent intent = new Intent(FormKelompokTaniActivity.this, KuesionerTipeCbActivity.class);
//                    startActivity(intent);
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
}
