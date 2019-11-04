package com.example.e_survey.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.e_survey.Util.Constant.KUESRB_URL;

public class FormKiosActivity extends AppCompatActivity {

    private EditText inNamaKios, inPemilik, inAlamat, inHP, inNamaDesa, inStatus, inAverage, inTahunKios, inThnPenyaluran, inJumlahKeltan;
    private Button btnSubmit;
    private TextView tv_toolbar;
    RadioGroup rdGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kios);
        initFindView();

    }

    private void initFindView() {
        inNamaKios = findViewById(R.id.inNamaKios);
        inPemilik = findViewById(R.id.inNamaPengelola);
        inAlamat = findViewById(R.id.inAlamat);
        inHP = findViewById(R.id.inNoHP);
        inNamaDesa = findViewById(R.id.inNamaDesa);
        inStatus = findViewById(R.id.inStatusBadanHukum);
        inAverage = findViewById(R.id.inAvgPenyaluran);
        inTahunKios = findViewById(R.id.inTahunKios);
        inThnPenyaluran = findViewById(R.id.inThnPenyaluran);
        tv_toolbar = findViewById(R.id.tv_toolbar);
        btnSubmit = findViewById(R.id.btnNext);
        rdGroup = findViewById(R.id.rdGroup);
        inJumlahKeltan = findViewById(R.id.jumlahKeltan);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (inNamaKios.getText().toString().equals("")) {
                    inNamaKios.setError("Masukkan Nama Kios!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t\t Nama Kios\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (inPemilik.getText().toString().equals("")) {
                    inPemilik.setError("Masukkan Nama Pemilik!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t Nama Pemilik\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (inAlamat.getText().toString().equals("")) {
                    inAlamat.setError("Masukkan Alamat Kios!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t Alamat Kios\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (inHP.getText().toString().equals("")) {
                    inHP.setError("Masukkan Nomer HP Pemilik!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t Nomor HP\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (inNamaDesa.getText().toString().equals("")) {
                    inNamaDesa.setError("Masukkan Nama Desa!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t Nama Desa\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (inStatus.getText().toString().equals("")) {
                    inStatus.setError("Masukkan Status Badan Hukum!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t Status Badan Hukum\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (inAverage.getText().toString().equals("")) {
                    inAverage.setError("Masukkan Rata-rata penyaluran!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t Rata-rata penyaluran\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (inTahunKios.getText().toString().equals("")) {
                    inTahunKios.setError("Masukkan Tahun berdiri kios!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t Tahun berdiri kios\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (inThnPenyaluran.getText().toString().equals("")) {
                    inThnPenyaluran.setError("Masukkan Tahun Penyaluran!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t Tahun penyaluran\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if (inJumlahKeltan.getText().toString().equals("")) {
                    inJumlahKeltan.setError("Masukkan Jumlah Kelompok Tani yang Dilayani!");
                    Toast.makeText(getApplicationContext(), "\t\t\t\t umlah Kelompok Tani yang Dilayani\n tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                try {
                    Soal.jsonIdentitas.put("nama_kios",inNamaKios.getText().toString());
                    Soal.jsonIdentitas.put("pemilik_kios",inPemilik.getText().toString());
                    Soal.jsonIdentitas.put("no_hp",inHP.getText().toString());
                    Soal.jsonIdentitas.put("nama_desa", inNamaDesa.getText().toString());
                    Soal.jsonIdentitas.put("status_badan_hukum", inStatus.getText().toString());
                    Soal.jsonIdentitas.put("avg_penyaluran",inAverage.getText().toString());
                    Soal.jsonIdentitas.put("tahun_berdiri_kios", inTahunKios.getText().toString());
                    Soal.jsonIdentitas.put("tahun_penyaluran", inThnPenyaluran.getText().toString());
                    Soal.jsonIdentitas.put("alamat_kios",inAlamat.getText().toString());
                    Soal.jsonIdentitas.put("jumlah_kelompok_tani", inJumlahKeltan.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Soal.kategoriKuis = "Kios";
                narikData2();
            }
        });

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

                if (kategori.equals("Kios")) {
                    Soal.listObj.add(oData);
                }
            }

            JSONObject objData = Soal.listObj.get(0);

            String getJenisJawbaan = objData.getString("jenis_pertanyaan");

            if (getJenisJawbaan.equals("isian")) {
                Intent intent = new Intent(FormKiosActivity.this, KuesionerTipeInActivity.class);
                intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                intent.putExtra("kode_soal", objData.getString("code_kuisioner"));
                startActivity(intent);
            } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                Intent intent = new Intent(FormKiosActivity.this, kuisioner_pg.class);
                intent.putExtra("jawabA", objData.getString("pilihanA"));
                intent.putExtra("jawabB", objData.getString("pilihanB"));
                intent.putExtra("jawabC", objData.getString("pilihanC"));
                intent.putExtra("jawabD", objData.getString("pilihanD"));
                intent.putExtra("kode_soal", objData.getString("code_kuisioner"));
                intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                startActivity(intent);
            } else if (getJenisJawbaan.equals("yesno")) {
                Intent intent = new Intent(FormKiosActivity.this, kuisioner_yn.class);
                intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                intent.putExtra("kode_soal", objData.getString("code_kuisioner"));
                startActivity(intent);
            } else if (getJenisJawbaan.equals("checkbox")) {
                Intent intent = new Intent(FormKiosActivity.this, kuisioner_cb.class);
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
