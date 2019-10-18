package com.example.e_survey.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.Model.Jawaban.JawabanModel;
import com.example.e_survey.R;
import com.example.e_survey.Util.Constant;
import com.example.e_survey.Util.SharedPreferenceCustom;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.e_survey.Util.Constant.KUESRB_URL;

public class kuisioner_yn extends AppCompatActivity {
    ArrayList<String> arrayListCodePilihan = new ArrayList<String>();
    ArrayList<String> arrayListJawabanPilihan = new ArrayList<String>();
    JawabanModel jawabanModel;
    String json;
    SharedPreferenceCustom sharedPreferenceCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuisioner_yn);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);

        TextView textPertanyaan = (TextView) findViewById(R.id.tvPertanyaan);
        textPertanyaan.setText(getIntent().getStringExtra("soal"));


    }

    public void narikData(View view) {
        if ((Soal.listObj.size()) != (Soal.parameter)) {
            Log.d("Hasil OBJ : ", ""+Soal.listObj.size());
            Log.d("Hasil Parameter : ", ""+Soal.parameter);
            try {
                JSONArray jsonArray = new JSONArray();
                JSONObject objData = Soal.listObj.get(Soal.parameter);
                Soal.parameter++;

                String getJenisJawbaan = objData.getString("jenis_pertanyaan");

                if (getJenisJawbaan.equals("isian")) {
                    Intent intent = new Intent(kuisioner_yn.this, KuesionerTipeInActivity.class);
                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                    for (int i = 0; i < arrayListJawabanPilihan.size(); i++) {
                        JSONObject jawaban = new JSONObject();
                        try {
                            jawaban.put("code_pertanyaan", arrayListCodePilihan.get(i));
                            Log.d("abc", arrayListCodePilihan.get(i));

                            jawaban.put("answer", arrayListJawabanPilihan.get(i));
                            Log.d("abc", arrayListJawabanPilihan.get(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    JSONObject obj = new JSONObject();

                    try {
                        obj.put("answer", jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.d("abc", jsonArray.toString());
                    jawabanModel = new JawabanModel();
                    jawabanModel.setCode_pertanyaan(jsonArray.toString());
                    jawabanModel.setAnswer(jsonArray.toString());
                    json = jsonArray.toString();

                    try {
                        SharedPreferences sharedPreferences = PreferenceManager
                                .getDefaultSharedPreferences(this.getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(jawabanModel);
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList = sharedPreferenceCustom.getSharedPrefStringArray(Constant.JAWABAN);
                        arrayList.add(json);
                        sharedPreferenceCustom.putSharedPrefStringArray(Constant.JAWABAN, arrayList);
                    } catch (Exception e) {

                    }

                    startActivity(intent);
                } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                    Intent intent = new Intent(kuisioner_yn.this, kuisioner_pg.class);
                    intent.putExtra("jawabA", objData.getString("pilihanA"));
                    intent.putExtra("jawabB", objData.getString("pilihanB"));
                    intent.putExtra("jawabC", objData.getString("pilihanC"));
                    intent.putExtra("jawabD", objData.getString("pilihanD"));

                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                    for (int i = 0; i < arrayListJawabanPilihan.size(); i++) {
                        JSONObject jawaban = new JSONObject();
                        try {
                            jawaban.put("code_pertanyaan", arrayListCodePilihan.get(i));
                            Log.d("abc", arrayListCodePilihan.get(i));

                            jawaban.put("answer", arrayListJawabanPilihan.get(i));
                            Log.d("abc", arrayListJawabanPilihan.get(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    JSONObject obj = new JSONObject();

                    try {
                        obj.put("answer", jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.d("abc", jsonArray.toString());
                    jawabanModel = new JawabanModel();
                    jawabanModel.setCode_pertanyaan(jsonArray.toString());
                    jawabanModel.setAnswer(jsonArray.toString());
                    json = jsonArray.toString();

                    try {
                        SharedPreferences sharedPreferences = PreferenceManager
                                .getDefaultSharedPreferences(this.getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(jawabanModel);
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList = sharedPreferenceCustom.getSharedPrefStringArray(Constant.JAWABAN);
                        arrayList.add(json);
                        sharedPreferenceCustom.putSharedPrefStringArray(Constant.JAWABAN, arrayList);
                    } catch (Exception e) {

                    }

                    startActivity(intent);
                } else if (getJenisJawbaan.equals("yesno")) {
                    Intent intent = new Intent(kuisioner_yn.this, kuisioner_yn.class);
                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));

                    for (int i = 0; i < arrayListJawabanPilihan.size(); i++) {
                        JSONObject jawaban = new JSONObject();
                        try {
                            jawaban.put("code_pertanyaan", arrayListCodePilihan.get(i));
                            Log.d("abc", arrayListCodePilihan.get(i));

                            jawaban.put("answer", arrayListJawabanPilihan.get(i));
                            Log.d("abc", arrayListJawabanPilihan.get(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    JSONObject obj = new JSONObject();

                    try {
                        obj.put("answer", jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.d("abc", jsonArray.toString());
                    jawabanModel = new JawabanModel();
                    jawabanModel.setCode_pertanyaan(jsonArray.toString());
                    jawabanModel.setAnswer(jsonArray.toString());
                    json = jsonArray.toString();

                    try {
                        SharedPreferences sharedPreferences = PreferenceManager
                                .getDefaultSharedPreferences(this.getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(jawabanModel);
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList = sharedPreferenceCustom.getSharedPrefStringArray(Constant.JAWABAN);
                        arrayList.add(json);
                        sharedPreferenceCustom.putSharedPrefStringArray(Constant.JAWABAN, arrayList);
                    } catch (Exception e) {

                    }

                    startActivity(intent);
                } else if (getJenisJawbaan.equals("checkbox")) {
                    Intent intent = new Intent(kuisioner_yn.this, kuisioner_cb.class);
                    intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                    intent.putExtra("jawabA", objData.getString("pilihanCB1"));
                    intent.putExtra("jawabB", objData.getString("pilihanCB2"));
                    intent.putExtra("jawabC", objData.getString("pilihanCB3"));
                    intent.putExtra("jawabD", objData.getString("pilihanCB4"));
                    intent.putExtra("jawabE", objData.getString("pilihanCB5"));

                    for (int i = 0; i < arrayListJawabanPilihan.size(); i++) {
                        JSONObject jawaban = new JSONObject();
                        try {
                            jawaban.put("code_pertanyaan", arrayListCodePilihan.get(i));
                            Log.d("abc", arrayListCodePilihan.get(i));

                            jawaban.put("answer", arrayListJawabanPilihan.get(i));
                            Log.d("abc", arrayListJawabanPilihan.get(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    JSONObject obj = new JSONObject();

                    try {
                        obj.put("answer", jsonArray);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.d("abc", jsonArray.toString());
                    jawabanModel = new JawabanModel();
                    jawabanModel.setCode_pertanyaan(jsonArray.toString());
                    jawabanModel.setAnswer(jsonArray.toString());
                    json = jsonArray.toString();

                    try {
                        SharedPreferences sharedPreferences = PreferenceManager
                                .getDefaultSharedPreferences(this.getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(jawabanModel);
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList = sharedPreferenceCustom.getSharedPrefStringArray(Constant.JAWABAN);
                        arrayList.add(json);
                        sharedPreferenceCustom.putSharedPrefStringArray(Constant.JAWABAN, arrayList);
                    } catch (Exception e) {

                    }

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
                            Intent intent = new Intent(kuisioner_yn.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Draft!",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(kuisioner_yn.this, DraftActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

    }

}
