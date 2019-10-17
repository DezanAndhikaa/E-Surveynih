package com.example.e_survey.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
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

public class kuisioner_pg extends AppCompatActivity {

    private Button btnSubmitPG;
    private RadioButton rb1, rb2, rb3, rb4;

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

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.rb1:
                if (checked)
                    // Pirates are the best
                    break;
            case R.id.rb2:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.rb3:
                if (checked)
                    // Ninjas rule
                    break;
            case R.id.rb4:
                if (checked)
                    // Ninjas rule
                    break;
        }
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
                    Intent intent = new Intent(kuisioner_pg.this, KuesionerTipeInActivity.class);
                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                    startActivity(intent);
                } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                    Intent intent = new Intent(kuisioner_pg.this, kuisioner_pg.class);
                    intent.putExtra("jawabA", objData.getString("pilihanA"));
                    intent.putExtra("jawabB", objData.getString("pilihanB"));
                    intent.putExtra("jawabC", objData.getString("pilihanC"));
                    intent.putExtra("jawabD", objData.getString("pilihanD"));

                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                    startActivity(intent);
                } else if (getJenisJawbaan.equals("yesno")) {
                    Intent intent = new Intent(kuisioner_pg.this, kuisioner_yn.class);
                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                    startActivity(intent);
                } else if (getJenisJawbaan.equals("checkbox")) {
                    Intent intent = new Intent(kuisioner_pg.this, kuisioner_cb.class);
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

