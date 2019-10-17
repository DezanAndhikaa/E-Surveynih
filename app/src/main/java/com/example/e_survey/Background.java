package com.example.e_survey;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.e_survey.Activity.ListKiosActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Background extends AsyncTask<String, Void, String> {

    AlertDialog alertDialog;
    Context context;
    public Boolean login = false;

    public Background(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.setMessage(s);
        alertDialog.show();
        if(s.contains("login successful")) {
            Intent intent = new Intent();
            intent.setClass(context.getApplicationContext(), ListKiosActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String result = "";
        String username = params[0];
        String password = params[1];

        String loginURL = "http://202.149.70.33/phpmyadmin/index.php";

        try {
            URL url = new URL(loginURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream ops = httpURLConnection.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ops, "UTF-8"));
            String query = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")
                    + "&&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            bw.write(query);
            bw.flush();
            bw.close();
            ops.close();

            InputStream ips = httpURLConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(ips, "ISO-8859-1"));
            String line = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();;
            ips.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
