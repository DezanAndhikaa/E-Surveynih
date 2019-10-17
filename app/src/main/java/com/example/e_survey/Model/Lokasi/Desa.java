package com.example.e_survey.Model.Lokasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Desa {
    @SerializedName("id_desa")
    @Expose
    private String idDesa;
    @SerializedName("nama_desa")
    @Expose
    private String namaDesa;
    @SerializedName("code_desa")
    @Expose
    private String kodeDesa;

    public String getIdDesa() {
        return idDesa;
    }

    public void setIdDesa(String idDesa) {
        this.idDesa = idDesa;
    }

    public String getNamaDesa() {
        return namaDesa;
    }

    public void setNamaDesa(String namaDesa) {
        this.namaDesa = namaDesa;
    }

    public String getKodeDesa() {
        return kodeDesa;
    }

    public void setKodeDesa(String kodeDesa) {
        this.kodeDesa = kodeDesa;
    }
}
