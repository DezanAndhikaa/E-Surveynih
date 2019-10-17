package com.example.e_survey.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfilDesa implements Parcelable {
    private String namaDesa;

    public ProfilDesa(String namaDesa) {
        this.namaDesa = namaDesa;
    }

    public String getNamaDesa() {
        return namaDesa;
    }

    public void setNamaDesa(String namaDesa) {
        this.namaDesa = namaDesa;
    }

    private ProfilDesa(Parcel in) {
        namaDesa = in.readString();
    }

    public static final Creator<ProfilDesa> CREATOR = new Creator<ProfilDesa>() {
        @Override
        public ProfilDesa createFromParcel(Parcel in) {
            return new ProfilDesa(in);
        }

        @Override
        public ProfilDesa[] newArray(int size) {
            return new ProfilDesa[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(namaDesa);
    }
}
