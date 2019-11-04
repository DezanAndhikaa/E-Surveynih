package com.example.e_survey.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.DatabaseLokal.DataHelper;
import com.example.e_survey.Model.Cache.Draft;
import com.example.e_survey.Model.SendJSON;
import com.example.e_survey.R;
import com.google.android.material.button.MaterialButtonToggleGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.example.e_survey.Util.Constant.KUESRB_URL;

public class kuisioner_pg extends AppCompatActivity {

    private Button btnSubmitPG;
    private RadioButton rb1, rb2, rb3, rb4;

    private String jawaban = "";
    private boolean answered;

    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        btnSubmitPG = findViewById(R.id.btnSubmitPG);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuisioner_pg);
        TextView textPertanyaan = (TextView) findViewById(R.id.tvPertanyaan);
        textPertanyaan.setText(getIntent().getStringExtra("soal"));

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);

        rb1.setText(getIntent().getStringExtra("jawabA"));
        rb2.setText(getIntent().getStringExtra("jawabB"));
        rb3.setText(getIntent().getStringExtra("jawabC"));
        rb4.setText(getIntent().getStringExtra("jawabD"));


    }

    public void radioA(View v) {
        rb1.setChecked(true);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
        jawaban = "A";
    }

    public void radioB(View v) {
        rb1.setChecked(false);
        rb2.setChecked(true);
        rb3.setChecked(false);
        rb4.setChecked(false);
        jawaban = "B";
    }

    public void radioC(View v) {
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(true);
        rb4.setChecked(false);
        jawaban = "C";
    }

    public void radioD(View v) {
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(true);
        jawaban = "D";
    }

    public void narikData(View view) {
        if (rb1.isChecked() == false && rb2.isChecked() == false && rb3.isChecked() == false && rb4.isChecked() == false) {
            Toast.makeText(getApplicationContext(), "Pilih Salah Satu!", Toast.LENGTH_SHORT).show();
        } else {
            if ((Soal.listObj.size()) != (Soal.parameter)) {
                Soal.listJawab.add(jawaban);
                Soal.listCode.add(getIntent().getStringExtra("kode_soal"));

                Log.d("Tag Kode Soal : ", getIntent().getStringExtra("kode_soal"));
                Log.d("Tag Jawaban : ", jawaban);

                try {
                    JSONObject objData = Soal.listObj.get(Soal.parameter);
                    Soal.parameter++;

                    String getJenisJawbaan = objData.getString("jenis_pertanyaan");

                    if (getJenisJawbaan.equals("isian")) {
                        Intent intent = new Intent(kuisioner_pg.this, KuesionerTipeInActivity.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                        Intent intent = new Intent(kuisioner_pg.this, kuisioner_pg.class);
                        intent.putExtra("jawabA", objData.getString("pilihanA"));
                        intent.putExtra("jawabB", objData.getString("pilihanB"));
                        intent.putExtra("jawabC", objData.getString("pilihanC"));
                        intent.putExtra("jawabD", objData.getString("pilihanD"));

                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("yesno")) {
                        Intent intent = new Intent(kuisioner_pg.this, kuisioner_yn.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("checkbox")) {
                        Intent intent = new Intent(kuisioner_pg.this, kuisioner_cb.class);
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
            } else {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                alertDialogBuilder.setTitle("Upload Hasil Kuisioner?");

                alertDialogBuilder
                        .setMessage("Jika tidak ada koneksi silahkan pilih menu 'Draft'")
                        .setIcon(R.mipmap.ic_launcher)
                        .setCancelable(false)
                        .setPositiveButton("Upload!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                boolean connected = false;
                                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                                    connected = true;
                                } else {
                                    connected = false;
                                }
                                if (connected) {
                                    SendJSON send = new SendJSON(getApplicationContext());
                                    Log.d("Identitas : ", Soal.jsonIdentitas.toString());
                                    Log.d("Jawaban : ", send.fetchJawaban());
                                    if (Soal.kategoriKuis.equals("Petani")) {
                                        send.PostJSONPetani();
                                    } else if (Soal.kategoriKuis.equals("Kelompok Tani")) {
                                        send.PostJSONKeltan();

                                    } else if (Soal.kategoriKuis.equals("Penyuluh")) {
                                        send.PostJSONPenyuluh();
                                    } else if (Soal.kategoriKuis.equals("Kios")) {
                                        send.PostJSONKios();
                                    }

                                    Intent intent = new Intent(kuisioner_pg.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(kuisioner_pg.this, "Berhasil Mengupload Hasil Kuesioner!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(kuisioner_pg.this, "Tidak Ada Koneksi Intetnet! Silahkan Pilih Menu Draft!", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("Draft!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                SendJSON send = new SendJSON(getApplicationContext());
                                Log.d("hasil : ", Soal.kategoriKuis);
                                DataHelper dbs = new DataHelper(getApplicationContext());
                                dbs.saveDraft(new Draft(Soal.jsonIdentitas.toString(), "" + send.fetchJawaban(), Soal.kategoriKuis));

                                Log.d("reading", "reading all data");
                                List<Draft> listBuku = dbs.findAllDraft();
                                for (Draft b : listBuku) {
                                    Log.d("data", "ID :" + b.getKategori() + " | JUDUL :" + b.getJsonJawaban() + " | PENULIS:" + b.getJsonResponden());
                                }

                                Intent intent = new Intent(kuisioner_pg.this, DraftActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(kuisioner_pg.this, "Berhasil di Menjadikan Draft!", Toast.LENGTH_LONG).show();

                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
    }
}

