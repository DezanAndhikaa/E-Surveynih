package com.example.e_survey.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FormKiosActivity extends AppCompatActivity {

    private EditText inNamaKios, inPemilik, inAlamat, inHP, inNamaDesa, inStatus, inAverage;
    private Button btnSubmit;
    private TextView tv_toolbar;
    private String URL_KIOS = "";
    String role = "";
    RadioGroup rdGroup;
    RadioButton rdButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kios);
        initFindView();
//        Loader load = new Loader();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Kios();
            }
        });
    }

    private void Kios() {
        final String namaKios = this.inNamaKios.getText().toString().trim();
        final String namaPengelola = this.inPemilik.getText().toString().trim();
        final String alamatKios = this.inAlamat.getText().toString().trim();
        final String noHP = this.inHP.getText().toString().trim();
        final String namaDesa= this.inNamaDesa.getText().toString().trim();
        final String statusBadanHukum = this.inStatus.getText().toString().trim();
        final String average = this.inAverage.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_KIOS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            int selectedID = rdGroup.getCheckedRadioButtonId();
                            rdButton = findViewById(selectedID);

                            if (selectedID == 1) {

                            } else if (selectedID == 2){

                            } else if (selectedID == 3) {

                            }

                            if (success.equals("1")) {
//                                Intent kues_kios = new Intent(FormKiosActivity.this, KuesionerTipeCbActivity.class );
//                                startActivity(kues_kios);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(FormKiosActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FormKiosActivity.this,"Error" +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama_kios", namaKios);
                params.put("pemilik_kios", namaPengelola);
                params.put("alamat_kios", alamatKios);
                params.put("telp_kios", noHP);
                params.put("nama_desa", namaDesa);
                params.put("jumlah_kel_tani", role);
                params.put("status_badan_hukum", statusBadanHukum);
                params.put("rata_rata_penyaluran", average);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void initFindView() {
        inNamaKios = findViewById(R.id.inNamaKios);
        inPemilik = findViewById(R.id.inNamaPengelola);
        inAlamat = findViewById(R.id.inAlamat);
        inHP = findViewById(R.id.inNoHP);
        inNamaDesa = findViewById(R.id.inNamaDesa);
        inStatus = findViewById(R.id.inStatusBadanHukum);
        inAverage = findViewById(R.id.inAvgPenyaluran);
        tv_toolbar = findViewById(R.id.tv_toolbar);
        btnSubmit = findViewById(R.id.btnSubmit);
        rdGroup = findViewById(R.id.rdGroup);
        tv_toolbar.setText("Profil Kios");
    }
}
