package com.example.e_survey.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.e_survey.R;
import com.example.e_survey.Util.Constant;
import com.example.e_survey.Util.SharedPreferenceCustom;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.Map;



public class LoginActivity extends AppCompatActivity {

    private EditText username,password;
    private Button btn_login;
    SharedPreferenceCustom sharedPreferenceCustom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
//                            String success = jsonObject.getString("success");
                            boolean error = jsonObject.getBoolean("error");
//                           JSONArray jsonArray = jsonObject.getJSONArray("login");
//                            JSONArray jsonArray = jsonObject.getJSONArray("id_management");
//                            JSONArray jsonArray = jsonObject.getJSONArray("data");

//                            if (success.equals("1")) {
                            if (!error) {

//                                for (int i = 0; i < jsonArray.length(); i++) {

//                                    JSONObject object = jsonArray.getJSONObject(i);

//                                    LoginModel loginModel = new LoginModel();

//                                    int id_fascam = object.getInt("id_fascam");
//                                    String jenis_user = object.getString("jenis_user").trim();
//                                    String username = object.getString("username").trim();
                                    sharedPreferenceCustom.putSharedPref(Constant.USERNAME, username);
//                                    String password = object.getString("password").trim();
//                                    String nama = object.getString("nama").trim();
//                                    int created_at = object.getInt("created_at");
//                                    int updated_at = object.getInt("updated_at");
//                                    int kode_qr = object.getInt("kode_qr");
//                                    String kabupaten = object.getString("kabupaten").trim();
//                                    String kecamatan = object.getString("kecamatan").trim();
//                                    String desa = object.getString("desa").trim();
//                                    String nama_kios = object.getString("nama_kios").trim();

//                                    loginModel.setId_fascam(id_fascam);
//                                    loginModel.setJenis_user(jenis_user);
//                                    loginModel.setUsername(username);
//                                    loginModel.setPassword(password);
//                                    loginModel.setNama(nama);
//                                    loginModel.setCreated_at(created_at);
//                                    loginModel.setUpdated_at(updated_at);
//                                    loginModel.setKode_qr(kode_qr);
//                                    loginModel.setKabupaten(kabupaten);
//                                    loginModel.setKecamatan(kecamatan);
//                                    loginModel.setDesa(desa);
//                                    loginModel.setNama_kios(nama_kios);

                                    Intent login = new Intent(LoginActivity.this, HomeActivity.class );
                                    startActivity(login);



                                }
//                            }else if (success.equals("0")) {
                            else if (!error) {
                                    Toast.makeText(LoginActivity.this, "Username Salah", Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(LoginActivity.this,"\t\t\t\tLogin Failed\nCheck Your Connection", Toast.LENGTH_SHORT).show();
                    }
                })


        {
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}