package com.example.e_survey.Model.All;

import com.example.e_survey.Model.Lokasi.Kios;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllKiosModel {
    @SerializedName("data")
    @Expose
    private List<Kios> kios = null;

    public List<Kios> getKios() {
        return kios;
    }

    public void setKios(List<Kios> kios) {
        this.kios = kios;
    }
}
