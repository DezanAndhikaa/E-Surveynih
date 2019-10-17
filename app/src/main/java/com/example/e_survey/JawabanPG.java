package com.example.e_survey;

import java.util.ArrayList;

public class JawabanPG {
    public static ArrayList<String> ListPertanyaan;
    public static ArrayList<String> ListJawaban;

    public static ArrayList<String> getListPertanyaan() {
        return ListPertanyaan;
    }

    public static void setListPertanyaan(ArrayList<String> listPertanyaan) {
        ListPertanyaan = listPertanyaan;
    }

    public static ArrayList<String> getListJawaban() {
        return ListJawaban;
    }

    public static void setListJawaban(ArrayList<String> listJawaban) {
        ListJawaban = listJawaban;
    }

}
