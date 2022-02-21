package com.dindatria.shetpi.API;


import com.dindatria.shetpi.Model.GetDataSapi;
import com.dindatria.shetpi.Model.GetDatamasuk;
import com.dindatria.shetpi.Model.GetHasilPengukuran;
import com.dindatria.shetpi.Model.PengukuranResponses;
import com.dindatria.shetpi.Model.PostPutDelSapiResponses2;
import com.dindatria.shetpi.Model.PostPutDelDataResponses;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

// untuk membuat antarmuka/interface API, digunakan untuk menjalankan permintaan HTTP seperti POST, PUT, dan DELETE

public interface APIService {
    @GET("API/ambildata")
    Call<GetDatamasuk> getDatamasuk();

    @Multipart
    @POST("API/addSapi")
    Call<PostPutDelSapiResponses2> postDataSapi(@Part MultipartBody.Part foto_sapi,
                                                @Part("id_sapi2") RequestBody   id_sapi2,
                                                @Part("nama_sapi") RequestBody   nama_sapi,
                                                @Part("jenis_kelamin") RequestBody   jenis_kelamin,
                                                @Part("tanggal_lahir") RequestBody   tanggal_lahir,
                                                @Part("keterangan") RequestBody   keterangan);

    @GET("API/getSapi")
    Call<GetDataSapi> getDataSapi();

//    @FormUrlEncoded
//    @POST("API/updatedatamasuk")
//    Call<PostPutDelDataResponses> updateDataMasuk(@Field("id_sapi") String id_sapi,
//                                                  @Field("id_data_masuk") int id_data_masuk);

    @FormUrlEncoded
    @POST("API/deleteData")
    Call<PostPutDelDataResponses> deleteData(@Field("id") int id);

    @FormUrlEncoded
    @POST("API/deleteData2")
    Call<PostPutDelSapiResponses2> deleteData2(@Field("id_sapi2") String id_sapi2);

    @GET("API/DataTerakhir")
    Call<GetDatamasuk> getDataterahir();

    @FormUrlEncoded
    @POST("API/tambahDataMasuk")
    Call<PengukuranResponses> tambahpengukuran(@Field("id_sapi") String id_sapi,
                                               @Field("suhu") String suhu,
                                               @Field("detak_jantung") String detak_jantung,
                                               @Field("berat_sapii") String berat_sapii);


    @GET("API/getHasilDataMasuk")
    Call<GetHasilPengukuran> gethasilpengukuran();

    @GET("API/getHasilDataMasukByID/{id_sapi}")
    Call<GetHasilPengukuran> getHasilPengukuranByIDSapi(@Path("id_sapi") String id_sapi);
}
