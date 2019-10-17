package com.example.e_survey.Model.Lokasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kabupaten {
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("kode")
    @Expose
    private String code;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
