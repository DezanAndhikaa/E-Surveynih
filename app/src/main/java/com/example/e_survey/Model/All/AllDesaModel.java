package com.example.e_survey.Model.All;

import com.example.e_survey.Model.Lokasi.Desa;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllDesaModel {
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("total_data")
    @Expose
    private String totalDesa;
    @SerializedName("data")
    @Expose
    private List<Desa> desa = null;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getTotalDesa() {
        return totalDesa;
    }

    public void setTotalDesa(String totalDesa) {
        this.totalDesa = totalDesa;
    }

    public List<Desa> getDesa() {
        return desa;
    }

    public void setDesa(List<Desa> desa) {
        this.desa = desa;
    }
}
