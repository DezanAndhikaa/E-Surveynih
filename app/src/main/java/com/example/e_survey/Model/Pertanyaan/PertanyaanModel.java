package com.example.e_survey.Model.Pertanyaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PertanyaanModel {
    private int id_kuisioner;
    private int code_kuisioner;
    private String pertanyaan_kuisioner;
    private String nama_kategori_kuisioner;
    private String parameter_kuisioner;
    private String jenis_pertanyaan;
    private int created_at;
    private int updated_at;

    public int getId_kuisioner() {
        return id_kuisioner;
    }

    public void setId_kuisioner(int id_kuisioner) {
        this.id_kuisioner = id_kuisioner;
    }

    public int getCode_kuisioner() {
        return code_kuisioner;
    }

    public void setCode_kuisioner(int code_kuisioner) {
        this.code_kuisioner = code_kuisioner;
    }

    public String getPertanyaan_kuisioner() {
        return pertanyaan_kuisioner;
    }

    public void setPertanyaan_kuisioner(String pertanyaan_kuisioner) {
        this.pertanyaan_kuisioner = pertanyaan_kuisioner;
    }

    public String getNama_kategori_kuisioner() {
        return nama_kategori_kuisioner;
    }

    public void setNama_kategori_kuisioner(String nama_kategori_kuisioner) {
        this.nama_kategori_kuisioner = nama_kategori_kuisioner;
    }

    public String getParameter_kuisioner() {
        return parameter_kuisioner;
    }

    public void setParameter_kuisioner(String parameter_kuisioner) {
        this.parameter_kuisioner = parameter_kuisioner;
    }

    public String getJenis_pertanyaan() {
        return jenis_pertanyaan;
    }

    public void setJenis_pertanyaan(String jenis_pertanyaan) {
        this.jenis_pertanyaan = jenis_pertanyaan;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public int getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(int updated_at) {
        this.updated_at = updated_at;
    }
}

//    @SerializedName("kode")
//    @Expose
//    private String kode;
//    @SerializedName("text")
//    @Expose
//    private String text;
//    @SerializedName("kategori")
//    @Expose
//    private String kategori;
//    @SerializedName("Parameter")
//    @Expose
//    private String Parameter;
//    @SerializedName("pilihan")
//    @Expose
//    private String pilihan;
//    @SerializedName("jawaban")
//    @Expose
//    private String jawaban;
//
//    public String getKode() {
//        return kode;
//    }
//
//    public void setKode(String kode) {
//        this.kode = kode;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public String getKategori() {
//        return kategori;
//    }
//
//    public void setKategori(String kategori) {
//        this.kategori = kategori;
//    }
//
//    public String getParameter() {
//        return Parameter;
//    }
//
//    public void setParameter(String Parameter) {
//        this.Parameter = Parameter;
//    }
//
//    public String getPilihan() {
//        return pilihan;
//    }
//
//    public void setPilihan(String pilihan) {
//        this.pilihan = pilihan;
//    }
//
//    public String getJawaban() {
//        return jawaban;
//    }
//
//    public void setJawaban(String jawaban) {
//        this.jawaban = jawaban;
//    }
//}
