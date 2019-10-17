package com.example.e_survey.Model.Lokasi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kios {
    private String kode_qr;
    private String kabupaten;
    private String kecamatan;
    private String desa;
    private String nama_kios;
    private int created_at;
    private int updated_at;

    public String getKode_qr() {
        return kode_qr;
    }

    public void setKode_qr(String kode_qr) {
        this.kode_qr = kode_qr;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getNama_kios() {
        return nama_kios;
    }

    public void setNama_kios(String nama_kios) {
        this.nama_kios = nama_kios;
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
/*@SerializedName("id_kios")
    @Expose
    private String idKios;
    @SerializedName("nama_kios")
    @Expose
    private String namaKios;
    @SerializedName("pemilik_kios")
    @Expose
    private String pemilikKios;
    @SerializedName("alamat_kios")
    @Expose
    private String alamatKios;
    @SerializedName("telp_kios")
    @Expose
    private String telpKios;
    @SerializedName("jumlah_kel_tani")
    @Expose
    private String jumlahKelTani;
    @SerializedName("kios_langlot")
    @Expose
    private String kiosLanglot;
    @SerializedName("kios_longitude")
    @Expose
    private String kiosLongitude;
    @SerializedName("kios_latitude")
    @Expose
    private String kiosLatitude;
    @SerializedName("kios_image")
    @Expose
    private String kiosImage;
    @SerializedName("id_desa")
    @Expose
    private String idDesa;
    @SerializedName("id_kuisioner")
    @Expose
    private String idKuisioner;
    @SerializedName("id_kecamatan")
    @Expose
    private String idKecamatan;

    public String getIdKios() {
        return idKios;
    }

    public void setIdKios(String idKios) {
        this.idKios = idKios;
    }

    public String getNamaKios() {
        return namaKios;
    }

    public void setNamaKios(String namaKios) {
        this.namaKios = namaKios;
    }

    public String getPemilikKios() {
        return pemilikKios;
    }

    public void setPemilikKios(String pemilikKios) {
        this.pemilikKios = pemilikKios;
    }

    public String getTelpKios() {
        return telpKios;
    }

    public void setTelpKios(String telpKios) {
        this.telpKios = telpKios;
    }

    public String getJumlahKelTani() {
        return jumlahKelTani;
    }

    public void setJumlahKelTani(String jumlahKelTani) {
        this.jumlahKelTani = jumlahKelTani;
    }

    public String getKiosLanglot() {
        return kiosLanglot;
    }

    public void setKiosLanglot(String kiosLanglot) {
        this.kiosLanglot = kiosLanglot;
    }

    public String getKiosLongitude() {
        return kiosLongitude;
    }

    public void setKiosLongitude(String kiosLongitude) {
        this.kiosLongitude = kiosLongitude;
    }

    public String getKiosLatitude() {
        return kiosLatitude;
    }

    public void setKiosLatitude(String kiosLatitude) {
        this.kiosLatitude = kiosLatitude;
    }

    public String getKiosImage() {
        return kiosImage;
    }

    public void setKiosImage(String kiosImage) {
        this.kiosImage = kiosImage;
    }

    public String getIdDesa() {
        return idDesa;
    }

    public void setIdDesa(String idDesa) {
        this.idDesa = idDesa;
    }

    public String getIdKuisioner() {
        return idKuisioner;
    }

    public void setIdKuisioner(String idKuisioner) {
        this.idKuisioner= idKuisioner;
    }

    public String getIdKecamatan() {
        return idKecamatan;
    }

    public void setIdKecamatan(String idKecamatan) {
        this.idKecamatan = idKecamatan;
    }*/
}
