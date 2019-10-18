package com.example.e_survey.Model;

import com.example.e_survey.Activity.Soal;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class SendJSON {
    public SendJSON() {
        String append = "{";
        for (int x = 0; x < Soal.listJawab.size(); x++) {
            append += "\"" + Soal.listCode.get(x) + "\":" + "\"" + Soal.listJawab.get(x) + "\",";
            if (x == (Soal.listJawab.size() - 1)) {
                append += "\"" + Soal.listCode.get(x) + "\":" + "\"" + Soal.listJawab.get(x) + "\"}";
            }
        }

        try {
            URL url = new URL("http://survey-kartutani.com/api/tambah_hasilpetani");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

//            String jsonInputString = "{"\"name"+":"+\"Upendra", "job":"Programmer"
        } catch (ProtocolException ex) {
            ex.printStackTrace();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

