package com.example.e_survey.Activity;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.Util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.e_survey.Util.Constant.KUESRB_URL;

public class Loader {
    public RequestQueue req;

    public Loader(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, KUESRB_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    JSONObject objData = data.getJSONObject(0);
                    Soal.soal = objData.getString("pertanyaan_kuisioner");
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
