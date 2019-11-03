package com.example.e_survey.Model.Cache;

public class Kuesioner {
    private String DataKuesioner;

    public Kuesioner(){

    }

    public Kuesioner(String a){
        this.DataKuesioner = a;
    }

    public String getDataKuesioner() {
        return DataKuesioner;
    }

    public void setDataKuesioner(String dataKuesioner) {
        DataKuesioner = dataKuesioner;
    }
}
