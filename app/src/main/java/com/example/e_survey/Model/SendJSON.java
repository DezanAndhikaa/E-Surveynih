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
import com.example.e_survey.DatabaseLokal.DataHelper;
import com.example.e_survey.DatabaseLokal.HasilJawabanKeltan;
import com.example.e_survey.DatabaseLokal.History;

import org.json.JSONException;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TimeZone;

public class SendJSON {

    Date currentTime = Calendar.getInstance().getTime();

    private DataHelper dataHelper;
    private Context context;

    public SendJSON(Context con) {
        context = con;
    }

    public String fetchJawaban() {

       String quizCode = "";
        for (int x = 0; x < Soal.listCode.size(); x++) {
            if (x != (Soal.listCode.size() - 1)) {
                quizCode += "{\"code_pertanyaan\":" + "\"" + Soal.listCode.get(x) + "\",\"answer\":\"" + Soal.listJawab.get(x) + "\"},";
            } else {
                quizCode += "{\"code_pertanyaan\":" + "\"" + Soal.listCode.get(x) + "\",\"answer\":\"" + Soal.listJawab.get(x) + "\"}]";
            }
        }

        Log.d("tage", quizCode);

        return quizCode;
    }

    public void PostJSONPetani() {

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
                        Log.e("TAG E: ", error.getMessage());
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
        Soal.parameter = 0;
    }

    public void PostJSONKios() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://survey-kartutani.com/api/tambah_hasilkios",
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
                        Log.e("TAG E: ", error.getMessage());
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_fascam", Soal.idFascam);
                params.put("responden_kios", Soal.jsonIdentitas.toString());
                params.put("jawaban_kios", fetchJawaban());
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        Soal.parameter = 0;
    }

    public void PostJSONKeltan(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://survey-kartutani.com/api/tambah_hasilkeltan",
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
                        Log.e("TAG E: ", error.getMessage());
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_fascam", Soal.idManagement);
                params.put("responden_keltan", Soal.jsonIdentitas.toString());
                params.put("jawaban_keltan", fetchJawaban());
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        Soal.parameter = 0;


    }

    public void PostJSONPenyuluh() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://survey-kartutani.com/api/tambah_hasilpenyuluh",
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
                        Log.e("TAG E: ", error.getMessage());
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_fascam", Soal.idManagement);
                params.put("responden_penyuluh", Soal.jsonIdentitas.toString());
                params.put("jawaban_penyuluh", fetchJawaban());
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        Soal.parameter = 0;
    }

    public void PostJSONPetani(String res, String jaw) {
        final String resp = res;
        final String jawb = jaw;
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
                        Log.e("TAG E: ", error.getMessage());
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_fascam", Soal.idManagement);
                params.put("responden_petani", resp);
                params.put("jawaban_petani", jawb);
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        Soal.parameter = 0;
    }

    public void PostJSONKios(String res, String jaw) {
        final String resp = res;
        final String jawb = jaw;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://survey-kartutani.com/api/tambah_hasilkios",
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
                        Log.e("TAG E: ", error.getMessage());
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_fascam", Soal.idFascam);
                params.put("responden_kios", resp);
                params.put("jawaban_kios", jawb);
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        Soal.parameter = 0;
        Soal.listJawab.clear();


    }

    public void PostJSONKeltan(String res, String jaw) {
        final String resp = res;
        final String jawb = jaw;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://survey-kartutani.com/api/tambah_hasilkeltan",
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
                        Log.e("TAG E: ", error.getMessage());
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_fascam", Soal.idManagement);
                params.put("responden_keltan", resp);
                params.put("jawaban_keltan", jawb);
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        Soal.parameter = 0;


    }

    public void PostJSONPenyuluh(String res, String jaw) {
        final String resp = res;
        final String jawb = jaw;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://survey-kartutani.com/api/tambah_hasilpenyuluh",
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
                        Log.e("TAG E: ", error.getMessage());
                    }

                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_fascam", Soal.idManagement);
                params.put("responden_penyuluh", resp);
                params.put("jawaban_penyuluh", jawb);
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
        Soal.parameter = 0;
        Soal.listJawab.clear();

    }

}

