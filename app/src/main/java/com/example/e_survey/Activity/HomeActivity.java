package com.example.e_survey.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.DatabaseLokal.DataHelper;
import com.example.e_survey.Model.Cache.DataKios;
import com.example.e_survey.Model.Cache.Kuesioner;
import com.example.e_survey.Model.Lokasi.Kios;
import com.example.e_survey.R;
import com.example.e_survey.Util.Constant;
import com.example.e_survey.Util.SharedPreferenceCustom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.e_survey.Util.Constant.KUESRB_URL;

public class HomeActivity extends AppCompatActivity {

    private TextView tv_toolbar;
    LinearLayout ll_kios, ll_petani, ll_penyuluh, ll_klmpk_tani;
    ImageView iv_logout;
    SharedPreferenceCustom sharedPreferenceCustom;
    DataHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        dbHelper = new DataHelper(this);
        if (dbHelper.cekKios().equals("none")) {
            setKios();
        } else {
            Log.d("DB Kios: ", dbHelper.cekKios());
        }

        if (dbHelper.cekKuesioner().equals("none")) {
            setKuesioner();
        } else {
            Log.d("DB Kuesioner: ", dbHelper.cekKuesioner());
        }

        Log.d("id ", Soal.idManagement);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);
        tv_toolbar = findViewById(R.id.tv_toolbar);
        iv_logout = findViewById(R.id.iv_logout);
        ll_kios = findViewById(R.id.ll_kios);
        ll_petani = findViewById(R.id.ll_petani);
        ll_penyuluh = findViewById(R.id.ll_penyuluh);
        ll_klmpk_tani = findViewById(R.id.ll_klmpk_tani);

        tv_toolbar.setText("Menu");

        ll_kios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listkios = new Intent(HomeActivity.this, ListKiosActivity.class);
                startActivity(listkios);
            }
        });
        ll_petani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prof_petani = new Intent(HomeActivity.this, FormPetaniActivity.class);
                startActivity(prof_petani);
            }
        });
        ll_penyuluh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prof_penyuluh = new Intent(HomeActivity.this, FormPenyuluhActivity.class);
                startActivity(prof_penyuluh);
            }
        });
        ll_klmpk_tani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent prof_klmpk_tani = new Intent(HomeActivity.this, FormKelompokTaniActivity.class);
                startActivity(prof_klmpk_tani);
            }
        });
        iv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClicker = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case DialogInterface.BUTTON_POSITIVE:
                                DataHelper db = new DataHelper(getApplicationContext());
                                db.clearLogin();
                                finish();
                                Intent inte = new Intent(HomeActivity.this, LoginActivity.class);
                                startActivity(inte);
                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder alert = new AlertDialog.Builder(HomeActivity.this);
                alert.setMessage("Apakah Anda yakin?").setPositiveButton("Iya", dialogClicker).setNegativeButton("Tidak", dialogClicker).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DialogInterface.OnClickListener dialogClicker = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        DataHelper db = new DataHelper(getApplicationContext());
                        db.clearLogin();
                        finish();
                        Intent inte = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(inte);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Apakah Anda yakin?").setPositiveButton("Iya", dialogClicker).setNegativeButton("Tidak", dialogClicker).show();
    }

    public void logout(View v) {
        DataHelper db = new DataHelper(getApplicationContext());
        db.clearLogin();
        finish();
        Intent inte = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(inte);
    }

    public void setKios() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.KIOS_URL + Soal.idManagement,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            DataHelper dbs = new DataHelper(getApplicationContext());
                            dbs.saveKios(new DataKios(jsonArray.toString()));
                            Log.d("From Method : ", dbs.cekKios());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void setKuesioner() {
        RequestQueue req = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, KUESRB_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    DataHelper dbs = new DataHelper(getApplicationContext());
                    dbs.saveKuesioner(new Kuesioner(data.toString()));

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
}