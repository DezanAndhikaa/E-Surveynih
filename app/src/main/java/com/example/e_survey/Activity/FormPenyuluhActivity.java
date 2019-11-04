package com.example.e_survey.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.e_survey.DatabaseLokal.DataHelper;
import com.example.e_survey.R;
import com.example.e_survey.Util.SharedPreferenceCustom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.e_survey.Util.Constant.KUESRB_URL;

public class FormPenyuluhActivity extends AppCompatActivity {

    Button btnNext;
    EditText inNamaPenyuluh, inAlamat, inNoHP, inJumlahDesa, inKlmpkTani;
    TextView tv_toolbar;
    RadioGroup rdGroup;
    RadioButton rdButton,rdDesa,rdTani;
    SharedPreferenceCustom sharedPreferenceCustom;
    private RadioButton rdJumlahDesa, rdKlmpkTani;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_penyuluh);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);
        initFindView();
        hideKeyboardFrom();
        rdTani = findViewById(R.id.rdKlmpkTani);
        rdDesa = findViewById(R.id.rdJumlahDesa);
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
                } else if (rdTani.isChecked() == false && rdDesa.isChecked() == false) {
                    Toast.makeText(getApplicationContext(), "Pilih salah satu!", Toast.LENGTH_SHORT).show();
                } else if (rdDesa.isChecked() == true && inJumlahDesa.getText().toString().equals("")) {
                    inJumlahDesa.setError("Masukkan Jumlah Desa!");
                    Toast.makeText(getApplicationContext(), "\t\t Jumlah Desa\n tidak boleh kososng", Toast.LENGTH_SHORT).show();
                } else if (rdTani.isChecked() == true && inKlmpkTani.getText().toString().equals("")) {
                    inKlmpkTani.setError("Masukkan Jumlah Kelompok Tani!");
                    Toast.makeText(getApplicationContext(), "Jumlah Kelompok Tani\n tidak boleh kososng", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        int selectedID = rdGroup.getCheckedRadioButtonId();
                        rdButton = findViewById(selectedID);

                        Soal.jsonIdentitas.put("nama_penyuluh", inNamaPenyuluh.getText().toString());
                        Soal.jsonIdentitas.put("alamat_penyuluh", inAlamat.getText().toString());
                        Soal.jsonIdentitas.put("telp_penyuluh", inNoHP.getText().toString());
                        if (selectedID == 1) {
                            Soal.jsonIdentitas.put("jumlah_desa", inJumlahDesa.getText().toString());
                        } else if (selectedID == 2) {
                            Soal.jsonIdentitas.put("kelompok_tani", inKlmpkTani.getText().toString());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Soal.kategoriKuis = "Penyuluh";
                    narikData2();
                }
            }
        });
    }

    public void rdDesaVoid (View v){
        rdDesa.setChecked(true);
        rdTani.setChecked(false);
        inJumlahDesa.setEnabled(true);
        inKlmpkTani.setText("");
        inKlmpkTani.setEnabled(false);
    }
    public void rdTaniVoid (View v){
        rdDesa.setChecked(false);
        rdTani.setChecked(true);
        inKlmpkTani.setEnabled(true);
        inJumlahDesa.setText("");
        inJumlahDesa.setEnabled(false);
    }

    public void hideKeyboardFrom() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    void narikData2() {
        Soal.listObj.clear();
        DataHelper dbs = new DataHelper(getApplicationContext());
        try {
            JSONArray data = new JSONArray(dbs.cekKuesioner());
            Soal.parameter++;

            for (int a = 0; a < data.length(); a++) {
                JSONObject oData = data.getJSONObject(a);
                String kategori = oData.getString("nama_kategori_kuisioner");
                if (kategori.equals("Penyuluh")) {
                    Log.d("kate", kategori);

                    Soal.listObj.add(oData);
                }
            }


            JSONObject objData = Soal.listObj.get(0);

            String getJenisJawbaan = objData.getString("jenis_pertanyaan");

            if (getJenisJawbaan.equals("isian")) {
                Intent intent = new Intent(FormPenyuluhActivity.this, KuesionerTipeInActivity.class);
                intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                intent.putExtra("kode_soal", objData.getString("code_kuisioner"));
                startActivity(intent);
            } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                Intent intent = new Intent(FormPenyuluhActivity.this, kuisioner_pg.class);
                intent.putExtra("jawabA", objData.getString("pilihanA"));
                intent.putExtra("jawabB", objData.getString("pilihanB"));
                intent.putExtra("jawabC", objData.getString("pilihanC"));
                intent.putExtra("jawabD", objData.getString("pilihanD"));
                intent.putExtra("kode_soal", objData.getString("code_kuisioner"));
                intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                startActivity(intent);
            } else if (getJenisJawbaan.equals("yesno")) {
                Intent intent = new Intent(FormPenyuluhActivity.this, kuisioner_yn.class);
                intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                intent.putExtra("kode_soal", objData.getString("code_kuisioner"));
                startActivity(intent);
            } else if (getJenisJawbaan.equals("checkbox")) {
                Intent intent = new Intent(FormPenyuluhActivity.this, kuisioner_cb.class);
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
