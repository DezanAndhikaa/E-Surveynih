package com.example.e_survey.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.e_survey.Util.Constant.KUESRB_URL;

public class kuisioner_yn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuisioner_yn);

        TextView textPertanyaan = (TextView) findViewById(R.id.tvPertanyaan);
        textPertanyaan.setText(getIntent().getStringExtra("soal"));


    }

    public void narikData(View view) {
        if ((Soal.listObj.size()) != (Soal.parameter)) {
            Log.d("Hasil OBJ : ", ""+Soal.listObj.size());
            Log.d("Hasil Parameter : ", ""+Soal.parameter);
            try {
                JSONObject objData = Soal.listObj.get(Soal.parameter);
                Soal.parameter++;

                String getJenisJawbaan = objData.getString("jenis_pertanyaan");

                if (getJenisJawbaan.equals("isian")) {
                    Intent intent = new Intent(kuisioner_yn.this, KuesionerTipeInActivity.class);
                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                    startActivity(intent);
                } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                    Intent intent = new Intent(kuisioner_yn.this, kuisioner_pg.class);
                    intent.putExtra("jawabA", objData.getString("pilihanA"));
                    intent.putExtra("jawabB", objData.getString("pilihanB"));
                    intent.putExtra("jawabC", objData.getString("pilihanC"));
                    intent.putExtra("jawabD", objData.getString("pilihanD"));

                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                    startActivity(intent);
                } else if (getJenisJawbaan.equals("yesno")) {
                    Intent intent = new Intent(kuisioner_yn.this, kuisioner_yn.class);
                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                    startActivity(intent);
                } else if (getJenisJawbaan.equals("checkbox")) {
                    Intent intent = new Intent(kuisioner_yn.this, kuisioner_cb.class);
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
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            alertDialogBuilder.setTitle("Upload Hasil Kuisioner?");

            alertDialogBuilder
                    .setMessage("Jika tidak ada koneksi silahkan pilih menu 'Draft'")
                    .setIcon(R.mipmap.ic_launcher)
                    .setCancelable(false)
                    .setPositiveButton("Upload!",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {

                        }
                    })
                    .setNegativeButton("Draft!",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }

}
