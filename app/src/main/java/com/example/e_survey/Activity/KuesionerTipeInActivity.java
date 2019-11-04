package com.example.e_survey.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.e_survey.DatabaseLokal.DataHelper;
import com.example.e_survey.Model.Cache.Draft;
import com.example.e_survey.Model.SendJSON;
import com.example.e_survey.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.example.e_survey.Model.Cache.Logs;

public class KuesionerTipeInActivity extends AppCompatActivity {

    private Button btnSubmit;
    private EditText jawaban;
    DataHelper dbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kuesioner_tipe_in);
        TextView textPertanyaan = (TextView) findViewById(R.id.tvPertanyaan);
        textPertanyaan.setText(getIntent().getStringExtra("soal"));
        btnSubmit = findViewById(R.id.btnSubmit);
        jawaban = findViewById(R.id.inJawaban);

        dbs = new DataHelper(getApplicationContext());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Soal.listJawab.add(jawaban.getText());
                Soal.listCode.add(getIntent().getStringExtra("kode_soal"));

                if ((Soal.listObj.size()) != (Soal.parameter)) {
//                if (Soal.parameter < 3) {
                    Log.d("Tag Kode Soal : ", getIntent().getStringExtra("kode_soal"));
                    Log.d("Tag Jawaban : ", jawaban.getText().toString());
                    narikData();
                } else {
                    open();
                }
            }
        });
    }

    public void narikData() {
        if (jawaban.equals("")) {
            jawaban.setError("Masukkan Jawaban Anda!");
            Toast.makeText(getApplicationContext(), "\t\tKolom Jawaban\n tidak boleh kososng", Toast.LENGTH_SHORT).show();
        } else {
            try {
                JSONObject objData = Soal.listObj.get(Soal.parameter);
                Soal.parameter++;

                String getJenisJawbaan = objData.getString("jenis_pertanyaan");

                if (getJenisJawbaan.equals("isian")) {
                    Intent intent = new Intent(KuesionerTipeInActivity.this, KuesionerTipeInActivity.class);
                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                    intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                    startActivity(intent);
                } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                    Intent intent = new Intent(KuesionerTipeInActivity.this, kuisioner_pg.class);
                    intent.putExtra("jawabA", objData.getString("pilihanA"));
                    intent.putExtra("jawabB", objData.getString("pilihanB"));
                    intent.putExtra("jawabC", objData.getString("pilihanC"));
                    intent.putExtra("jawabD", objData.getString("pilihanD"));
                    intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                    startActivity(intent);
                } else if (getJenisJawbaan.equals("yesno")) {
                    Intent intent = new Intent(KuesionerTipeInActivity.this, kuisioner_yn.class);
                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                    intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                    startActivity(intent);
                } else if (getJenisJawbaan.equals("checkbox")) {
                    Intent intent = new Intent(KuesionerTipeInActivity.this, kuisioner_cb.class);
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

    public void open() {

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(c);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        final String time = ""+mdformat.format(calendar.getTime());

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Upload Hasil Kuisioner?");
        alertDialogBuilder.setMessage("Jika tidak ada koneksi silahkan pilih menu 'Draft'");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        alertDialogBuilder.setPositiveButton("Upload",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
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

                            try {
                                dbs.saveLog(new Logs("Upload "+Soal.kategoriKuis,formattedDate, Soal.jsonIdentitas.getString("nama_desa"),time));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            Intent intent = new Intent(KuesionerTipeInActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(KuesionerTipeInActivity.this, "Berhasil Mengupload Hasil Kuesioner!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(KuesionerTipeInActivity.this, "Tidak Ada Koneksi Intetnet! Silahkan Pilih Menu Draft!", Toast.LENGTH_LONG).show();
                        }


                    }
                });

        alertDialogBuilder.setNegativeButton("Draft", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SendJSON send = new SendJSON(getApplicationContext());
                Log.d("hasil : ", Soal.kategoriKuis);
                DataHelper dbs = new DataHelper(getApplicationContext());
                dbs.saveDraft(new Draft(Soal.jsonIdentitas.toString(), "" + send.fetchJawaban(), Soal.kategoriKuis));

                Log.d("reading", "reading all data");
                List<Draft> listBuku = dbs.findAllDraft();
                for (Draft b : listBuku) {
                    Log.d("data", "ID :" + b.getKategori() + " | JUDUL :" + b.getJsonJawaban() + " | PENULIS:" + b.getJsonResponden());
                }

                try {
                    dbs.saveLog(new Logs("Draft "+Soal.kategoriKuis,formattedDate, Soal.jsonIdentitas.getString("nama_desa"),time));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Intent intent = new Intent(KuesionerTipeInActivity.this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(KuesionerTipeInActivity.this, "Berhasil Mendraft!", Toast.LENGTH_LONG).show();

                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
