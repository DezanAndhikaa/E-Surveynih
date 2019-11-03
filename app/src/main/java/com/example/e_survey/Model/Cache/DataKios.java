package com.example.e_survey.Model.Cache;

public class DataKios {

    private String ListKiosOff;

    public DataKios(String toString) {
        this.ListKiosOff = toString;
    }

    public DataKios() {

    }

    public String getListKiosOff() {
        return ListKiosOff;
    }

    public void setListKiosOff(String listKiosOff) {
        ListKiosOff = listKiosOff;
    }
}
