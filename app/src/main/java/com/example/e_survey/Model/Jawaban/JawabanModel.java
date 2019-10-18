package com.example.e_survey.Model.Jawaban;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;

public class JawabanModel {
    public String code_pertanyaan = "";
    public String answer = "";
    public String json = "";

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getCode_pertanyaan() {
        return code_pertanyaan;
    }

    public void setCode_pertanyaan(String code_pertanyaan) {
        this.code_pertanyaan = code_pertanyaan;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
