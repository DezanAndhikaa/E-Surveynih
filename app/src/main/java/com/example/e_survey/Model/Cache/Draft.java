package com.example.e_survey.Model.Cache;

public class Draft {
    private String jsonResponden;
    private String jsonJawaban;
    private String kategori;

    public Draft(){

    }

    public Draft(String a, String b, String c){
        this.jsonResponden =a;
        this.jsonJawaban = b;
        this.kategori = c;
    }

    public String getJsonResponden() {
        return jsonResponden;
    }

    public void setJsonResponden(String jsonResponden) {
        this.jsonResponden = jsonResponden;
    }

    public String getJsonJawaban() {
        return jsonJawaban;
    }

    public void setJsonJawaban(String jsonJawaban) {
        this.jsonJawaban = jsonJawaban;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
