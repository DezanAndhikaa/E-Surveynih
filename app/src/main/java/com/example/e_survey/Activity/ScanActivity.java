package com.example.e_survey.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_survey.Model.QR;
import com.example.e_survey.R;
import com.example.e_survey.Util.Constant;
import com.example.e_survey.Util.SharedPreferenceCustom;
import com.google.zxing.Result;
import com.karan.churi.PermissionManager.PermissionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.e_survey.Util.Constant.KUESRB_URL;

public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    PermissionManager permissionManager;
    private ZXingScannerView ScannerView;
    SharedPreferenceCustom sharedPreferenceCustom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        permissionManager = new PermissionManager() {
        };
        permissionManager.checkAndRequestPermissions(this);

        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        permissionManager.checkResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onPause() {
        super.onPause();

        ScannerView.stopCamera();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }


    @Override
    public void handleResult(Result result) {
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);
        String hasil = result.getText();
        String dataOper = getIntent().getExtras().getString("nama");
        String[] splitOper = dataOper.split("\\.");

        int id = Integer.parseInt(splitOper[0]);
        id = id - 1;
        String qrCode = "";
        try {
            JSONObject obj = QR.jsonArray.getJSONObject(id);
            qrCode = obj.getString("kode_qr");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ScannerView.resumeCameraPreview(this);

        if (qrCode.equals(hasil)) {
            Intent login = new Intent(ScanActivity.this, FormKiosActivity.class);
            startActivity(login);
        } else {
            Toast.makeText(getApplicationContext(), "QR Code Tidak Sesuai Dengan Kios yang Dipilih!", Toast.LENGTH_SHORT).show();

        }


    }

    void narikData2() {
        Soal.listObj.clear();
        RequestQueue req = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, KUESRB_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("data");
                    Soal.parameter++;

                    for (int a = 0; a < data.length(); a++) {
                        JSONObject oData = data.getJSONObject(a);
                        String kategori = oData.getString("nama_kategori_kuisioner");
                        Soal.kategoriKuis = "Kios";

                        if (kategori.equals("Kios")) {
                            Soal.listObj.add(oData);
                        }
                    }

                    JSONObject objData = Soal.listObj.get(0);
                    String getJenisJawbaan = objData.getString("jenis_pertanyaan");

                    if (getJenisJawbaan.equals("isian")) {
                        Intent intent = new Intent(ScanActivity.this, KuesionerTipeInActivity.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("pilihan_ganda")) {
                        Intent intent = new Intent(ScanActivity.this, kuisioner_pg.class);
                        intent.putExtra("jawabA", objData.getString("pilihanA"));
                        intent.putExtra("jawabB", objData.getString("pilihanB"));
                        intent.putExtra("jawabC", objData.getString("pilihanC"));
                        intent.putExtra("jawabD", objData.getString("pilihanD"));
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("yesno")) {
                        Intent intent = new Intent(ScanActivity.this, kuisioner_yn.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    } else if (getJenisJawbaan.equals("checkbox")) {
                        Intent intent = new Intent(ScanActivity.this, kuisioner_cb.class);
                        intent.putExtra("soal", objData.getString("pertanyaan_kuisioner"));
                        intent.putExtra("jawabA", objData.getString("pilihanCB1"));
                        intent.putExtra("jawabB", objData.getString("pilihanCB2"));
                        intent.putExtra("jawabC", objData.getString("pilihanCB3"));
                        intent.putExtra("jawabD", objData.getString("pilihanCB4"));
                        intent.putExtra("jawabE", objData.getString("pilihanCB5"));
                        intent.putExtra("kode_soal", objData.getString("code_kuisioner"));

                        startActivity(intent);
                    }

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

