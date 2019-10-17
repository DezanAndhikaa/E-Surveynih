package com.example.e_survey.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.e_survey.Model.QR;
import com.example.e_survey.R;
import com.example.e_survey.Util.Constant;
import com.example.e_survey.Util.SharedPreferenceCustom;
import com.google.zxing.Result;
import com.karan.churi.PermissionManager.PermissionManager;

import org.json.JSONException;
import org.json.JSONObject;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

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
//        sharedPreferenceCustom.getSharedPref(Constant.QR);
        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);
        String hasil = result.getText();
        String qr = sharedPreferenceCustom.getSharedPref(Constant.QR);
        String dataOper = getIntent().getExtras().getString("nama");
        String[] splitOper = dataOper.split("\\.");

        int id = Integer.parseInt(splitOper[0]);
        id = id -1;
        String qrCode = "";
        try {
            JSONObject obj = QR.jsonArray.getJSONObject(id);
            qrCode = obj.getString("kode_qr");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ScannerView.resumeCameraPreview(this);

        if(qrCode.equals(hasil)){
            Intent intent = new Intent(ScanActivity.this, FormKiosActivity.class);
            startActivity(intent);
        } else {
//            Toast.makeText(getApplicationContext(),"Hasil JSON: " + QR.hasilQR + " || Hasil QR : "+ hasil, Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),qrCode, Toast.LENGTH_SHORT).show();

        }



    }
    }

