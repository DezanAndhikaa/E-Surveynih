package com.example.e_survey;

import com.example.e_survey.Model.All.AllDataModel;
import com.example.e_survey.Model.LoginModel;
import com.example.e_survey.Model.Lokasi.Desa;
import com.example.e_survey.Model.ProfilDesa;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface API {
    @FormUrlEncoded
    @POST("userlogin") //Sesuaikan dengan di PHP
    Call<LoginModel> postLogin(@Field("username") String username,
                               @Field("password") String password);

    @GET("")
    Call<Desa> getDesa(@Field("id_desa") String id_desa,
                             @Field("nama_desa") String nama_desa,
                             @Field("code_desa") String code_desa);

    @GET("getdata/pertanyaan/list/{id}")
    Call<Object> getPertanyaanJSON(@Path("id")String id);

    @FormUrlEncoded
    @POST("submitdata/survey")
    Call<ResponseBody> postSurvey(@Field("surveyor") String surveyor,
                                  @Field("provinsi_code") String provinsi_code,
                                  @Field("kabupaten_code") String kabupaten_code,
                                  @Field("category_lockcode") String category_lockcode,
                                  @Field("lokasi_lockcode") String lokasi_lockcode,
                                  @Field("answer") String answer);

    @Multipart
    @POST("submitdata/survey")
    Call<ResponseBody> postSurveyPhoto(
            @Part("surveyor") RequestBody surveyor,
            @Part("provinsi_code") RequestBody provinsi_code,
            @Part("kabupaten_code") RequestBody kabupaten_code,
            @Part("category_lockcode") RequestBody category_lockcode,
            @Part("lokasi_lockcode") RequestBody lokasi_lockcode,
            @Part("answer") RequestBody answer,
            @Part List<MultipartBody.Part> photo,
            @Part("responden") RequestBody responden);

    @GET("")
    Call<AllDataModel> getDataAll(@Path("id")String id);
}
