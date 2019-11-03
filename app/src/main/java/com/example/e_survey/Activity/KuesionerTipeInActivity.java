package com.example.e_survey.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.Model.SendJSON;
import com.example.e_survey.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.e_survey.Util.Constant.KUESRB_URL;

public class KuesionerTipeInActivity extends AppCompatActivity {

    private Button btnSubmit;
    private EditText jawaban;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kuesioner_tipe_in);
        TextView textPertanyaan = (TextView) findViewById(R.id.tvPertanyaan);
        textPertanyaan.setText(getIntent().getStringExtra("soal"));
        btnSubmit = findViewById(R.id.btnSubmit);
        jawaban = findViewById(R.id.inJawaban);


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
        RequestQueue req = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, KUESRB_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        req.add(request);
    }

    public void open() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Upload Hasil Kuisioner?");
        alertDialogBuilder.setMessage("Jika tidak ada koneksi silahkan pilih menu 'Draft'");
        alertDialogBuilder.setIcon(R.mipmap.ic_launcher);
        alertDialogBuilder.setPositiveButton("Upload",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        SendJSON send = new SendJSON(getApplicationContext());
                        Log.d("Identitas : ", Soal.jsonIdentitas.toString());
                        Log.d("Jawaban : ", send.fetchJawaban());
                        if (Soal.kategoriKuis.equals("Petani")) {
                            send.PostJSONPetani();
                        } else if (Soal.kategoriKuis.equals("Kelompok Tani")) {
                            try {
                                send.PostJSONKeltan();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else if (Soal.kategoriKuis.equals("Penyuluh")) {
                            send.PostJSONPenyuluh();
                        } else if (Soal.kategoriKuis.equals("Kios")) {
                            send.PostJSONKios();
                        }

                        Intent intent = new Intent(KuesionerTipeInActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(KuesionerTipeInActivity.this, "Berhasil Mengupload Hasil Kuesioner!", Toast.LENGTH_SHORT).show();
                    }
                });

        alertDialogBuilder.setNegativeButton("Draft", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(KuesionerTipeInActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(KuesionerTipeInActivity.this, "Berhasil Mendraft Hasil Kuesioner!", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
