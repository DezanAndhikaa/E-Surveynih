package com.example.e_survey.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.Model.Lokasi.Kios;
import com.example.e_survey.Model.QR;
import com.example.e_survey.R;
import com.example.e_survey.Util.Constant;
import com.example.e_survey.Util.KiosAdapter;
import com.example.e_survey.Util.SharedPreferenceCustom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ListKiosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KiosAdapter adapter;
    private TextView tv_toolbar;
    private ArrayList<Kios> kiosArrayList ;
    SharedPreferenceCustom sharedPreferenceCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kios);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);

        //addData();
        initFIndView();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListKiosActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        getdata();
    }

//    public void addData() {
//        kiosArrayList = new ArrayList<>();
//        kiosArrayList.add(new Kios("KIOS A"));
//        kiosArrayList.add(new Kios ("KIOS B"));
//        kiosArrayList.add(new Kios("KIOS C"));
//    }

    private void initFIndView() {
        tv_toolbar = findViewById(R.id.tv_toolbar);
        recyclerView = findViewById(R.id.rvProfilKios);
        kiosArrayList =  new ArrayList<Kios>();
        adapter = new KiosAdapter(kiosArrayList);

        tv_toolbar.setText("List Kios");
    }
    public void getdata () {

        SharedPreferences pref;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.KIOS_URL+sharedPreferenceCustom.getSharedPref(Constant.USERNAME),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean error = jsonObject.getBoolean("error");
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
//                            jsonArray = QR.jsonArray;
                            QR.jsonArray = jsonArray;
                            if (!error) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    Kios kios  = new Kios();
                                    String kode_qr = object.getString("kode_qr").trim();
                                    String kabupaten = object.getString("kabupaten").trim();
                                    String kecamatan = object.getString("kecamatan").trim();
                                    String desa = object.getString("desa").trim();
                                    String nama_kios = object.getString("nama_kios").trim();
//                                    int created_at = object.getInt("created_at");
//                                    int updated_at = object.getInt("updated_at");
                                    sharedPreferenceCustom.putSharedPref(Constant.QR, kode_qr);
                                    QR.hasilQR = kode_qr;
//                                    QR.hasilQR.add(kode_qr);
                                    kios.setKode_qr(kode_qr);
                                    kios.setKabupaten(kabupaten);
                                    kios.setDesa(desa);
                                    kios.setNama_kios(nama_kios);
                                    sharedPreferenceCustom.putSharedPref(Constant.QR, kode_qr);
//                                    kios.setCreated_at(created_at);
//                                    kios.setUpdated_at(updated_at);
                                    kiosArrayList.add(kios);

                                }
                                adapter.notifyDataSetChanged();
                            }else if (error) {
                                Toast.makeText(ListKiosActivity.this, "data Salah", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ListKiosActivity.this, "Error" +e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListKiosActivity.this,"Error" +error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
//                params.put("username", username);
//                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    /*private RecyclerView rvProfilDesa;
    private SharedPreferenceCustom sharedPreferenceCustom;
    private Toolbar toolbar;
    private ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_kios);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);
        initFindView();
        initDataAll();
    }

    private void initDataAll() {
        API service = Adapter.getInstance().getRetrofit(Constant.URL_API.API).create(API.class);

        Call<AllDataModel> call = service.getDataAll(sharedPreferenceCustom.getSharedPref(Constant.USERNAME));
        call.enqueue(new Callback<AllDataModel>() {
            @Override
            public void onResponse(Call<AllDataModel> call, Response<AllDataModel> response) {
                if(response.isSuccessful()) {
                    try {
                        Gson gson = new Gson();
                        String json = gson.toJson(response.body());
                        sharedPreferenceCustom.putSharedPref(Constant.DATA_ALL,json);
                        initData();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    initData();
                }
            }

            @Override
            public void onFailure(Call<AllDataModel> call, Throwable t) {
                initData();
            }
        });
    }

    private void initData() {
        String desa;
        desa = sharedPreferenceCustom.getSharedPref(Constant.DATA_ALL);
        Gson gson = new Gson();
        AllDataModel data = new AllDataModel();
        data = gson.fromJson(desa, AllDataModel.class);

        try {
            List<Desa> resultList = data.getDesa().getDesa();
            resultDesa(resultList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resultDesa(final List<Desa> resultList) {
        CustomRecyclerAdapter<Desa> resultListAdapter = new CustomRecyclerAdapter<>(
                getApplicationContext(), R.layout.item_list_kios,
                resultList,
                new CustomRecyclerAdapter.ViewHolderBinder<Desa>() {
                    @Override
                    public void bind(CustomRecyclerAdapter.CustomViewHolder<Desa> holder, final Desa item) {
                        LinearLayout layoutItem = holder.itemView.findViewById(R.id.layoutItem);
                        TextView tvDesa = holder.itemView.findViewById(R.id.tvProfilDesa);
                        tvDesa.setText(item.getNamaDesa());
                        layoutItem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sharedPreferenceCustom.putSharedPref(Constant.ID_DESA, item.getIdDesa());
                                sharedPreferenceCustom.putSharedPref(Constant.NAMA_DESA, item.getNamaDesa());
                                Intent intent = new Intent(ListKiosActivity.this,HomeActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }
        );
        rvProfilDesa.setAdapter(resultListAdapter);
    }

    private void initFindView() {
        toolbar = findViewById(R.id.toolbar);
        rvProfilDesa = findViewById(R.id.rvProfilDesa);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        rvProfilDesa.setLayoutManager(linearLayoutManager);
        rvProfilDesa.setItemAnimator(new DefaultItemAnimator());

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferenceCustom.clearSharedPrefs();
                Intent intent = new Intent(ListKiosActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }*/
}
