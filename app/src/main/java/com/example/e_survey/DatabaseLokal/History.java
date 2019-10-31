package com.example.e_survey.DatabaseLokal;

public class History {
    String tanggal;
    int jam;
    String kategori;
    String nama;
    String desa;

    public History(){

    }

    public History(String tanggal, int jam, String kategori, String nama, String desa){
        this.tanggal = tanggal;
        this.jam = jam;
        this.kategori = kategori;
        this.nama = nama;
        this.desa = desa;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getJam() {
        return jam;
    }

    public void setJam(int jam) {
        this.jam = jam;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }
}

