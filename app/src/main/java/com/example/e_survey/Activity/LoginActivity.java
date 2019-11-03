package com.example.e_survey.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.DatabaseLokal.DataHelper;
import com.example.e_survey.Model.Cache.Login;
import com.example.e_survey.R;
import com.example.e_survey.Util.Constant;
import com.example.e_survey.Util.SharedPreferenceCustom;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button btn_login;
    SharedPreferenceCustom sharedPreferenceCustom;
    DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DataHelper(this);

        if(!dbHelper.cekLogin().equals("none")){
            Soal.idManagement = dbHelper.cekLogin();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btn_login = findViewById(R.id.btn_login);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUser = username.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (mUser.equals("") && mPass.equals("")) {
                    username.setError("Please insert username");
                    password.setError("Please insert password");
                } else if (mUser.equals("")) {
                    username.setError("Please insert username");
                } else if (mPass.equals("")) {
                    password.setError("Please insert password");
                } else {
                    Login(mUser, mPass);
                }
            }
        });
    }

    private void Login(final String username, final String password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String error = jsonObject.getString("error");
                            String dataNih = jsonObject.getString("data");
                            JSONObject jData = new JSONObject(dataNih);
                            if (error.equals("false")) {
                                sharedPreferenceCustom.putSharedPref(Constant.USERNAME, username);
                                Soal.idManagement = jData.getString("username");

                                dbHelper.save(new Login(username,password));

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();

                            } else if (error.equals("true")) {
                                Toast.makeText(LoginActivity.this, "Username / Password Salah", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this, "\t\t\t\t\t\t\tLogin Failed\nWrong Username and Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "\t\t\t\tLogin Failed\nCheck Your Connection", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}