package com.example.e_survey.Model;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.Activity.Soal;

import java.util.HashMap;
import java.util.Map;

public class SendJSON {

    private Context context;

    public SendJSON(Context con){
        context = con;
    }

    public String fetchJawaban(){
        String quizCode ="[";

        for (int x = 0; x < Soal.listCode.size(); x++) {
            if (x != (Soal.listCode.size() - 1)) {
                quizCode += "{\"code_pertanyaan\":" + "\"" + Soal.listCode.get(x) + "\",\"answer\":\"" + Soal.listJawab.get(x) + "\"},";
            } else {
                quizCode += "{\"code_pertanyaan\":" + "\"" + Soal.listCode.get(x) + "\",\"answer\":\"" + Soal.listJawab.get(x) + "\"}]";
            }
        }

        return quizCode;
    }

    public void PostJSONPetani(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://survey-kartutani.com/api/tambah_hasilpetani",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("TAG E: ", response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG E: ",error.getMessage());
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_fascam", Soal.idManagement);
                params.put("responden_petani", Soal.jsonIdentitas.toString());
                params.put("jawaban_petani", fetchJawaban());
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}

