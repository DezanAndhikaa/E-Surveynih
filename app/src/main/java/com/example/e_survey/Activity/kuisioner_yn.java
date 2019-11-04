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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.Model.SendJSON;
import com.example.e_survey.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.e_survey.Util.Constant.KUESRB_URL;

public class kuisioner_yn extends AppCompatActivity {
    private TextView textPertanyaan;
    private RadioButton rb1, rb2;
    private String jawaban = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuisioner_yn);

        textPertanyaan = findViewById(R.id.tvPertanyaan);
        textPertanyaan.setText(getIntent().getStringExtra("soal"));

        rb1 = findViewById(R.id.rb_cek1);
        rb2 = findViewById(R.id.rb_cek2);
    }

    public void cek1(View v) {
        rb2.setChecked(false);
        jawaban = "Benar";
    }

    public void cek2(View v){
        rb1.setChecked(false);
        jawaban = "Salah";
    }
    public void cekJump() {
        JSONObject objData = Soal.listObj.get(Soal.parameter);
        try {
            String nomorJump = "";
            if (rb1.isChecked()) {
                nomorJump = objData.getString("pilihanYes_jump");
            } else if (rb2.isChecked()) {
                nomorJump = objData.getString("pilihanNo_jump");
            }

            Log.d("TAG :  ", nomorJump);

            if(!nomorJump.equals("-")){
                for (int a = Soal.parameter; a < Soal.listObj.size(); a++) {
                    JSONObject get = Soal.listObj.get(a);
                    String idJump = get.getString("id_kuisioner");
                    if (idJump.equals(nomorJump)) {
                        Soal.parameter = a;
                        break;
                    }
                }
            }else{
                finish();
                Intent intent = new Intent(kuisioner_yn.this, HomeActivity.class);
                startActivity(intent);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void narikData(View view) {
        cekJump();

        Soal.listJawab.add(jawaban);
        Soal.listCode.add(getIntent().getStringExtra("kode_soal"));

        Log.d("Tag Kode Soal : " , getIntent().getStringExtra("kode_soal"));
        Log.d("Tag Jawaban : " , jawaban);

        if (rb1.isChecked() == false && rb2.isChecked() == false) {
            Toast.makeText(getApplicationContext(), "Pilih Salah Satu!", Toast.LENGTH_SHORT).show();
        } else {
            if ((Soal.listObj.size()) != (Soal.parameter)) {
                try {
                    JSONObject objData = Soal.listObj.get(Soal.parameter);
                    Soal.parameter++;
                    String getJenisJawbaan = objData.getString("jenis_pertanyaan");
                    if (getJenisJawbaan.equals("isian")) {
                        Intent intent = new Intent(kuisioner_yn.this, KuesionerTipeInActivity.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                        Intent intent = new Intent(kuisioner_yn.this, kuisioner_pg.class);
                        intent.putExtra("jawabA", objData.getString("pilihanA"));
                        intent.putExtra("jawabB", objData.getString("pilihanB"));
                        intent.putExtra("jawabC", objData.getString("pilihanC"));
                        intent.putExtra("jawabD", objData.getString("pilihanD"));
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("yesno")) {
                        Intent intent = new Intent(kuisioner_yn.this, kuisioner_yn.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("checkbox")) {
                        Intent intent = new Intent(kuisioner_yn.this, kuisioner_cb.class);
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
                                if(connected){
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

                                    Intent intent = new Intent(kuisioner_yn.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                    Toast.makeText(kuisioner_yn.this, "Berhasil Mengupload Hasil Kuesioner!", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(kuisioner_yn.this, "Tidak Ada Koneksi Intetnet! Silahkan Pilih Menu Draft!", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("Draft!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent intent = new Intent(kuisioner_yn.this, DraftActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(kuisioner_yn.this, "Berhasil di Menjadikan Draft!", Toast.LENGTH_LONG).show();

                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        }
    }
}
