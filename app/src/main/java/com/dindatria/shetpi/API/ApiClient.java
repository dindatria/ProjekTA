package com.dindatria.shetpi.API;

import com.dindatria.shetpi.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//membuat instansi retrofit, Untuk mengeluarkan permintaan jaringan ke API dengan Retrofit,
// kita perlu membuat contoh menggunakan kelas ApiClient dan mengkonfigurasinya dengan URL dasar.
public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.SERVER_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static APIService getApiInterface(){
        APIService apiInterface = getRetrofit().create(APIService.class);
        return apiInterface;
    }
}
