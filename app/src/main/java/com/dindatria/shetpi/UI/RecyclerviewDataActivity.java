package com.dindatria.shetpi.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.dindatria.shetpi.API.ApiClient;
import com.dindatria.shetpi.Model.GetDatamasuk;
import com.dindatria.shetpi.Model.GetHasilPengukuran;
import com.dindatria.shetpi.R;
import com.dindatria.shetpi.adapter.AdapterDataShetpi;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerviewDataActivity extends AppCompatActivity implements AdapterDataShetpi.OnDelete {

    private RecyclerView rv_list_sepi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_data);

        getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rv_list_sepi = findViewById(R.id.rv_list_sapi);
        getDataListSepi();

    }

    private void getDataListSepi(){
        Call<GetHasilPengukuran> getHasilPengukuranCall = ApiClient.getApiInterface().gethasilpengukuran();
        getHasilPengukuranCall.enqueue(new Callback<GetHasilPengukuran>() {
            @Override
            public void onResponse(Call<GetHasilPengukuran> call, Response<GetHasilPengukuran> response) {
                if (response.isSuccessful()){
                    if (!response.body().isError()){
                        AdapterDataShetpi adapterDataSepi = new AdapterDataShetpi(response.body().getPengukuranModels(), RecyclerviewDataActivity.this, RecyclerviewDataActivity.this);
                        adapterDataSepi.notifyDataSetChanged();
                        rv_list_sepi.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rv_list_sepi.setHasFixedSize(true);
                        rv_list_sepi.setAdapter(adapterDataSepi);

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                    }else{
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Tidak Ada Response Server", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GetHasilPengukuran> call, Throwable t) {
                Log.e("Error", t.getMessage());
                Toast.makeText(getApplicationContext(), "Periksa Koneksi Internet Anda", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        getDataListSepi();
        super.onResume();
    }

    @Override
    public void onLoaddelete(boolean is_delete, String message) {
        if (is_delete){
            getDataListSepi();
        }
        Toast.makeText(RecyclerviewDataActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
