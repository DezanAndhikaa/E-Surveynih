package com.example.e_survey.Model;

import com.example.e_survey.Model.Lokasi.Desa;
import com.example.e_survey.Model.Lokasi.Kabupaten;
import com.example.e_survey.Model.Lokasi.Kecamatan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Auth {
    @SerializedName("desa")
    @Expose
    private Desa desa;
    @SerializedName("kabupaten")
    @Expose
    private Kabupaten kabupaten;
    @SerializedName("kecamatan")
    @Expose
    private Kecamatan kecamatan;

    public Desa getDesa() {
        return desa;
    }

    public void setDesa(Desa desa) {
        this.desa = desa;
    }

    public Kabupaten getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(Kabupaten kabupaten) {
        this.kabupaten = kabupaten;
    }

    public Kecamatan getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(Kecamatan kecamatan) {
        this.kecamatan = kecamatan;
    }
}
