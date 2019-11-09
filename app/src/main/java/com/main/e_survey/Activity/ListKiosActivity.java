package com.main.e_survey.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.main.e_survey.DatabaseLokal.DataHelper;
import com.main.e_survey.Model.Lokasi.Kios;
import com.main.e_survey.Model.QR;
import com.main.e_survey.R;
import com.main.e_survey.Util.Constant;
import com.main.e_survey.Util.KiosAdapter;
import com.main.e_survey.Util.SharedPreferenceCustom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ListKiosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private KiosAdapter adapter;
    private TextView tv_toolbar;
    private ArrayList<Kios> kiosArrayList;
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

    private void initFIndView() {
        tv_toolbar = findViewById(R.id.tv_toolbar);
        recyclerView = findViewById(R.id.rvProfilKios);
        kiosArrayList = new ArrayList<Kios>();
        adapter = new KiosAdapter(kiosArrayList);

        tv_toolbar.setText("List Kios");
    }

    public void getdata() {
        DataHelper dbs = new DataHelper(this);
        try {
            JSONArray jsonArray = new JSONArray(dbs.cekKios());
            QR.jsonArray = jsonArray;

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);
                Kios kios = new Kios();
                String dataf = object.getString("id_fascam");

                String kode_qr = object.getString("kode_qr").trim();
                String kabupaten = object.getString("kabupaten").trim();
                String kecamatan = object.getString("kecamatan").trim();
                String desa = object.getString("desa").trim();
                String nama_kios = object.getString("nama_kios").trim();
                sharedPreferenceCustom.putSharedPref(Constant.QR, kode_qr);
                QR.hasilQR = kode_qr;
                Soal.idFascam = dataf;
                kios.setKode_qr(kode_qr);
                kios.setKabupaten(kabupaten);
                kios.setDesa(desa);
                kios.setNama_kios(nama_kios);
                sharedPreferenceCustom.putSharedPref(Constant.QR, kode_qr);
                kiosArrayList.add(kios);

            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(ListKiosActivity.this, "Error" + e.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
