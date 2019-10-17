package com.example.e_survey.Model.Pertanyaan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KategoriPertanyaanModel {
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("total_data")
    @Expose
    private Integer totalData;
    @SerializedName("data")
    @Expose
    private List<KategoriDataPertanyaanModel> data = null;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public Integer getTotalData() {
        return totalData;
    }

    public void setTotalData(Integer totalData) {
        this.totalData = totalData;
    }

    public List<KategoriDataPertanyaanModel> getData() {
        return data;
    }

    public void setData(List<KategoriDataPertanyaanModel> data) {
        this.data = data;
    }
}
