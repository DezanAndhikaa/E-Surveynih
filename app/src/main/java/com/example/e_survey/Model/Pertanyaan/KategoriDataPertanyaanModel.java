package com.example.e_survey.Model.Pertanyaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KategoriDataPertanyaanModel {
    @SerializedName("id_kategori_kuisioner")
    @Expose
    private String idKategoriKuisioner;
    @SerializedName("nama_kategori_kuisioner")
    @Expose
    private String namaKategoriKuisioner;
    @SerializedName("pertanyaan")
    @Expose
    private List<PertanyaanModel> pertanyaan = null;

    public String getIdKategoriKuisioner() {
        return idKategoriKuisioner;
    }

    public void setIdKategoriKuisioner(String idKategoriKuisioner) {
        this.idKategoriKuisioner = idKategoriKuisioner;
    }

    public String getNamaKategoriKuisioner() {
        return namaKategoriKuisioner;
    }

    public void setNamaKategoriKuisioner(String namaKategoriKuisioner) {
        this.namaKategoriKuisioner = namaKategoriKuisioner;
    }

    public List<PertanyaanModel> getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(List<PertanyaanModel> pertanyaan) {
        this.pertanyaan = pertanyaan;
    }
}
