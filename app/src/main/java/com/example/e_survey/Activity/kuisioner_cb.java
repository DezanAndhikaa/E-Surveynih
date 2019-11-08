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
import android.widget.CheckBox;
import android.widget.RadioButton;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static com.example.e_survey.Util.Constant.KUESRB_URL;


public class kuisioner_cb extends AppCompatActivity {
    private CheckBox cb1, cb2, cb3, cb4, cb5;
    String jawaban = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuisioner_cb);

        TextView textPertanyaan = (TextView) findViewById(R.id.tvPertanyaan);
        textPertanyaan.setText(getIntent().getStringExtra("soal"));

        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);

        cb1.setText(getIntent().getStringExtra("jawabA"));
        cb2.setText(getIntent().getStringExtra("jawabB"));
        cb3.setText(getIntent().getStringExtra("jawabC"));
        cb4.setText(getIntent().getStringExtra("jawabD"));
        cb5.setText(getIntent().getStringExtra("jawabE"));


    }

    public void narikData(View view) {


        if (cb1.isChecked()) {
            jawaban += "A,";
        }
        if (cb2.isChecked()) {
            jawaban += "B,";

        }
        if (cb3.isChecked()) {
            jawaban += "C,";

        }
        if (cb4.isChecked()) {
            jawaban += "D,";

        }

        if (cb5.isChecked()) {
            jawaban += "E,";

        }

        Soal.listJawab.add(jawaban);
        Soal.listCode.add(getIntent().getStringExtra("kode_soal"));

        Log.d("Tag Kode Soal : ", getIntent().getStringExtra("kode_soal"));
        Log.d("Tag Jawaban : ", jawaban);

        if (cb1.isChecked() == false && cb2.isChecked() == false && cb3.isChecked() == false && cb4.isChecked() == false && cb5.isChecked() == false) {
            Toast.makeText(getApplicationContext(), "Pilih Salah Satu!", Toast.LENGTH_SHORT).show();
        } else {
            if ((Soal.listObj.size()) != (Soal.parameter)) {

                try {

                    JSONObject objData = Soal.listObj.get(Soal.parameter);
                    Soal.parameter++;

                    String getJenisJawbaan = objData.getString("jenis_pertanyaan");

                    if (getJenisJawbaan.equals("isian")) {
                        Intent intent = new Intent(kuisioner_cb.this, KuesionerTipeInActivity.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                        Intent intent = new Intent(kuisioner_cb.this, kuisioner_pg.class);
                        intent.putExtra("jawabA", objData.getString("pilihanA"));
                        intent.putExtra("jawabB", objData.getString("pilihanB"));
                        intent.putExtra("jawabC", objData.getString("pilihanC"));
                        intent.putExtra("jawabD", objData.getString("pilihanD"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("yesno")) {
                        Intent intent = new Intent(kuisioner_cb.this, kuisioner_yn.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("checkbox")) {
                        Intent intent = new Intent(kuisioner_cb.this, kuisioner_cb.class);
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

                                    Intent intent = new Intent(kuisioner_cb.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(kuisioner_cb.this, "Berhasil Mengupload Hasil Kuesioner!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(kuisioner_cb.this, "Tidak Ada Koneksi Intetnet! Silahkan Pilih Menu Draft!", Toast.LENGTH_LONG).show();
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

                                Intent intent = new Intent(kuisioner_cb.this, DraftActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(kuisioner_cb.this, "Berhasil di Menjadikan Draft!", Toast.LENGTH_LONG).show();

                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }

    }
}
