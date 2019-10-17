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

public class FormPenyuluhActivity extends AppCompatActivity {

    Button btnNext;
    EditText inNamaPenyuluh, inAlamat, inNoHP, inJumlahDesa, inKlmpkTani;
    TextView tv_toolbar;
    RadioGroup rdGroup;
    RadioButton rdButton;
    SharedPreferenceCustom sharedPreferenceCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_penyuluh);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);
        initFindView();
        hideKeyboardFrom();
    }

    private void initFindView() {
        inNamaPenyuluh = findViewById(R.id.inNamaPenyuluh);
        inAlamat = findViewById(R.id.inAlamat);
        inNoHP = findViewById(R.id.inNoHP);
        rdGroup = findViewById(R.id.rdGroup);
        inJumlahDesa = findViewById(R.id.inJumlahDesa);
        inKlmpkTani = findViewById(R.id.inKlmpkTani);
        tv_toolbar = findViewById(R.id.tv_toolbar);
        btnNext = findViewById(R.id.btnNext);

        tv_toolbar.setText("Profil Penyuluh");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inNamaPenyuluh.getText().toString().equals("")) {
                    inNamaPenyuluh.setError("Masukkan Nama Penyuluh!");
                    Toast.makeText(getApplicationContext(), "\t\tNama Penyuluh\n tidak boleh kososng", Toast.LENGTH_SHORT).show();
                } else if (inAlamat.getText().toString().equals("")) {
                    inAlamat.setError("Masukkan Alamat!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t\tAlamat\n tidak boleh kososng", Toast.LENGTH_SHORT).show();
                } else if (inNoHP.getText().toString().equals("")) {
                    inNoHP.setError("Masukkan Nomor HP!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\tNomor HP\n tidak boleh kososng", Toast.LENGTH_SHORT).show();
                }   else {
                    JSONObject penyuluh = new JSONObject();
                    try {
                        int selectedID = rdGroup.getCheckedRadioButtonId();
                        rdButton = findViewById(selectedID);
                        //Querynya musti diganti
                        penyuluh.put("nama_penyuluh", inNamaPenyuluh.getText().toString());
                        penyuluh.put("alamat_penyuluh", inAlamat.getText().toString());
                        penyuluh.put("telp_penyuluh", inNoHP.getText().toString());
                        if (selectedID == 1) {
                            penyuluh.put("jumlah_desa", inJumlahDesa.getText().toString());
                        } else if (selectedID == 2) {
                            penyuluh.put("jlh_klmpk_tani", inKlmpkTani.getText().toString());
                        }

                        sharedPreferenceCustom.putSharedPref(Constant.FORM_PENYULUH, penyuluh.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    sharedPreferenceCustom.putSharedPref(Constant.FORM_PENYULUH, inNamaPenyuluh.getText().toString());

                    Intent intent = new Intent(FormPenyuluhActivity.this, KuesionerTipeRbActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void hideKeyboardFrom() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
