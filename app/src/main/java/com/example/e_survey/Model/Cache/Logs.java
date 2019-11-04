package com.example.e_survey.Model.Cache;

public class Logs {
    private String KEY_NamaAksi;
    private String KEY_TanggalAksi;
    private String KEY_NamaDesa;

    public Logs(String KEY_NamaAksi, String KEY_TanggalAksi, String KEY_NamaDesa, String KEY_JamAksi) {
        this.KEY_NamaAksi = KEY_NamaAksi;
        this.KEY_TanggalAksi = KEY_TanggalAksi;
        this.KEY_NamaDesa = KEY_NamaDesa;
        this.KEY_JamAksi = KEY_JamAksi;
    }

    public Logs(){}


    public String getKEY_NamaAksi() {
        return KEY_NamaAksi;
    }

    public void setKEY_NamaAksi(String KEY_NamaAksi) {
        this.KEY_NamaAksi = KEY_NamaAksi;
    }

    public String getKEY_TanggalAksi() {
        return KEY_TanggalAksi;
    }

    public void setKEY_TanggalAksi(String KEY_TanggalAksi) {
        this.KEY_TanggalAksi = KEY_TanggalAksi;
    }

    public String getKEY_NamaDesa() {
        return KEY_NamaDesa;
    }

    public void setKEY_NamaDesa(String KEY_NamaDesa) {
        this.KEY_NamaDesa = KEY_NamaDesa;
    }

    public String getKEY_JamAksi() {
        return KEY_JamAksi;
    }

    public void setKEY_JamAksi(String KEY_JamAksi) {
        this.KEY_JamAksi = KEY_JamAksi;
    }

    private String KEY_JamAksi;
}
