package com.example.e_survey.Model.All;

import com.example.e_survey.Model.Pertanyaan.KategoriPertanyaanModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllDataModel {
    @SerializedName("Desa")
    @Expose
    private AllDesaModel desa;
    @SerializedName("kios")
    @Expose
    private AllKiosModel kios;
    @SerializedName("kategori")
    @Expose
    private KategoriPertanyaanModel kategori;

    public AllDesaModel getDesa() {
        return desa;
    }

    public void setDesa(AllDesaModel desa) {
        this.desa = desa;
    }

    public AllKiosModel getKios() {
        return kios;
    }

    public void setKios(AllKiosModel kios) {
        this.kios = kios;
    }

    public KategoriPertanyaanModel getKategori() {
        return kategori;
    }

    public void setKategori(KategoriPertanyaanModel kategori) {
        this.kategori = kategori;
    }
}
