package com.example.e_survey.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_survey.R;

public class KuesionerTipeYnActivity extends AppCompatActivity {

    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kuesioner_tipe_in);

        btnSubmit = findViewById(R.id.btnSubmit);



        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kues_cb = new Intent(KuesionerTipeYnActivity.this, KuesionerTipeYnActivity.class);
                startActivity(kues_cb);
            }
        });
    }
}

//    TextView tvPertanyaan, tvPosisi;
//    RadioGroup radioGroup;
//    ArrayList<String> arrayListPertanyaan = new ArrayList<>();
//    ArrayList<String> arrayListJawaban = new ArrayList<>();
//    ArrayList<String> arrayListKode = new ArrayList<>();
//    ArrayList<String> arrayListPilihanJawaban = new ArrayList<>();
//    ArrayList<String> arrayListPilihanKode = new ArrayList<>();
//    ProgressDialog progressDialog;
//    Button btnSubmit;
//    int position = 0;
//    int radioKlik = -1;
//    int posisiPertanyaan = 0;
//
//    JawabanFormModel jawabanFormModel;
//    PilihanJawabanModel pilihanJawabanModel;
//
//    SharedPreferenceCustom sharedPreferenceCustom;
//    String json;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.kuesioner_tipe_rb);
//        sharedPreferenceCustom = SharedPreferenceCustom.getInstance(this);
//        getPassingBundle(getIntent());
//        initFindView();
//        initProgressDialog();
//        initDataAll();
//    }
//
//    private void initDataAll() {
//        initDataKuesioner();
//        if(arrayListPertanyaan.size() == 0) {
//            initJSONJawaban();
//            //initKirimJawaban();
//        } else {
//            initPertanyaan();
//        }
//    }
//
//    private void getPassingBundle(Intent intent) {
//        posisiPertanyaan = Integer.parseInt(sharedPreferenceCustom.getSharedPref(Constant.POSISI));
//    }
//
//    private void initProgressDialog() {
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage(getResources().getString(R.string.please_wait));
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.setCancelable(false);
//    }
//
//    private void initFindView() {
//        tvPosisi = findViewById(R.id.tvPosisi);
//        tvPertanyaan = findViewById(R.id.tvPertanyaan);
//        radioGroup = findViewById(R.id.radioGroup);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//            }
//        });
//        btnSubmit = findViewById(R.id.btnSubmit);
//    }
//
//    private void initDataKuesioner() {
//        String kios;
//        kios = sharedPreferenceCustom.getSharedPref(Constant.DATA_ALL);
//        Gson gson = new Gson();
//        AllDataModel data = new AllDataModel();
//        data = gson.fromJson(kios, AllDataModel.class);
//
//        List<PertanyaanModel> resultList = data.getKategori().getData().get(posisiPertanyaan).getPertanyaan();
//        for(int i = 0; i < resultList.size(); i++) {
//            arrayListJawaban.add(resultList.get(i).getJawaban());
//            arrayListKode.add(resultList.get(i).getKode());
//            arrayListPertanyaan.add(resultList.get(i).getText());
//        }
//    }
//
//    private void initPertanyaan() {
//        RadioGroup.LayoutParams rprms;
//        int positionX = position + 1;
//        tvPosisi.setText("Pertanyaan " + positionX);
//        tvPertanyaan.setText(arrayListPertanyaan.get(position));
//
//        String json = "{'jawaban: " + arrayListJawaban.get(position) + "}";
//
//        JSONObject jsonResponse;
//        try {
//            jsonResponse = new JSONObject(json);
//            JSONArray jawaban = jsonResponse.getJSONArray("jawaban: ");
//            for(int i = 0; i < jawaban.length(); i++) {
//                JSONObject movie = jawaban.getJSONObject(i);
//                String jsonJawaban = movie.toString();
//                String text = new Gson().fromJson(jsonJawaban, JawabanModel.class).getText();
//                String code = new Gson().fromJson(jsonJawaban, JawabanModel.class).getCode();
//                String jump = new Gson().fromJson(jsonJawaban, JawabanModel.class).getJump();
//                if(!text.equals("")) {
//                    RadioButton radioButton = new RadioButton(getApplicationContext());
//                    radioButton.setText(text);
//                    radioButton.setTag(code + "-" + jump);
//                    rprms = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
//                    radioGroup.addView(radioButton, rprms);
//                }
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                for (int i = 0; i < radioGroup.getChildCount(); i++) {
//                    RadioButton btn = (RadioButton) radioGroup.getChildAt(i);
//                    if (btn.getId() == checkedId) {
//                        radioKlik = i;
//                        btn.getTag();
//                        return;
//                    }
//                }
//            }
//        });
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (radioKlik < 0) {
//                    Toast.makeText(getApplicationContext(), "Please choose your answer", Toast.LENGTH_SHORT).show();
//                } else {
//                    if (position == arrayListJawaban.size() - 1) {
//                        initJSONJawaban();
//                        //openMap();
//                        //initKirimJawaban();
//                    } else {
//                        String selected = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getTag().toString();
//                        Logs.d("cek", selected.substring(selected.indexOf("-") + 1, selected.length()));
//                        arrayListPilihanKode.add(arrayListKode.get(position));
//                        arrayListPilihanJawaban.add(selected.substring(0,selected.indexOf("-")));
//                        radioKlik = -1;
//
//                        String jump = selected.substring(selected.indexOf("-") + 1, selected.length());
//                        if (jump.equals("0")) {
//
//                            position++;
//                        } else if (jump.equals("9999")) {
//                            initJSONJawaban();
//                            //initKirimJawaban();
//                        } else {
//                            for (int i = 0; i < arrayListKode.size(); i++) {
//                                if (arrayListKode.get(i).equals(jump)) {
//                                    position = i;
//                                    break;
//                                }
//                            }
//
//                        }
//                        radioGroup.removeAllViews();
//                        initPertanyaan();
//                    }
//
//                }
//
//            }
//        });
//    }
//
//    private void initJSONJawaban() {
//        JSONArray jsonArray = new JSONArray();
//
//        for (int i = 0; i < arrayListPilihanJawaban.size(); i++) {
//            JSONObject answer = new JSONObject();
//            try {
//                answer.put("code", arrayListPilihanKode.get(i));
//                Logs.d("tes", arrayListPilihanKode.get(i));
//
//                answer.put("choosen", arrayListPilihanJawaban.get(i));
//                Logs.d("tes", arrayListPilihanJawaban.get(i));
//                jsonArray.put(answer);
//            } catch (JSONException e) {
//                //TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//        JSONObject studentsObj = new JSONObject();
//        try {
//            studentsObj.put("answer", jsonArray);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        Logs.d("tes", jsonArray.toString());
//        jawabanFormModel = new JawabanFormModel();
//        jawabanFormModel.setJawaban(jsonArray.toString());
//        json = jsonArray.toString();
//        jawabanFormModel.setUsername(sharedPreferenceCustom.getSharedPref(Constant.USERNAME));
//        jawabanFormModel.setId_desa(sharedPreferenceCustom.getSharedPref(Constant.ID_DESA));
//        jawabanFormModel.setId_kecamatan(sharedPreferenceCustom.getSharedPref(Constant.ID_KECAMATAN));
//        jawabanFormModel.setId_kuisioner(sharedPreferenceCustom.getSharedPref(Constant.ID_KUISIONER));
//        jawabanFormModel.setId_kios(sharedPreferenceCustom.getSharedPref(Constant.ID_KIOS));
//
//        try {
//            SharedPreferences appSharedPrefs = PreferenceManager
//                    .getDefaultSharedPreferences(this.getApplicationContext());
//            SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
//            Gson gson = new Gson();
//            String json = gson.toJson(jawabanFormModel);
//            ArrayList<String> arrayList = new ArrayList<>();
//            arrayList = sharedPreferenceCustom.getSharedPrefStringArray(Constant.FORM_ANSWER_LIST);
//            arrayList.add(json);
//            sharedPreferenceCustom.putSharedPrefStringArray(Constant.FORM_ANSWER_LIST, arrayList);
//        } catch (Exception e) {
//
//        }
//    }
//}

