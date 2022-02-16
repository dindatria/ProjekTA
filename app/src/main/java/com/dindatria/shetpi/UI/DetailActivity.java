package com.dindatria.shetpi.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.dindatria.shetpi.API.ApiClient;
import com.dindatria.shetpi.Model.DataSapiModel;
import com.dindatria.shetpi.Model.GetDataSapi;
import com.dindatria.shetpi.R;
import com.dindatria.shetpi.adapter.AdapterDataSapi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity implements AdapterDataSapi.OnDelete2{
private RecyclerView rv_list_datasapi;
private Button btnInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        rv_list_datasapi=findViewById(R.id.list_datasapi);
        getListDataSapi();

    }
    private void getListDataSapi() {
        Call<GetDataSapi> getDataSapiCall = ApiClient.getApiInterface().getDataSapi();
        getDataSapiCall.enqueue(new Callback<GetDataSapi>() {
            @Override
            public void onResponse(Call<GetDataSapi> call, Response<GetDataSapi> response) {
                if (response.isSuccessful()){
                    if (!response.body().isError()){
                        AdapterDataSapi adapterDataSapi = new AdapterDataSapi(response.body().getDataSapiModels(),DetailActivity.this, DetailActivity.this);
                        adapterDataSapi.notifyDataSetChanged();
                        rv_list_datasapi.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rv_list_datasapi.setHasFixedSize(true);
                        rv_list_datasapi.setAdapter(adapterDataSapi);

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Tidak Ada Respon Server", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GetDataSapi> call, Throwable t) {
                Log.e("error",t.getMessage());
                Toast.makeText(getApplicationContext(),"Periksa Koneksi Internet Anda", Toast.LENGTH_LONG).show();
            }

        });
    }


    @Override
    public void onLoaddelete2(boolean is_delete, String message) {
        if (is_delete){
            getListDataSapi();
        }
        Toast.makeText(DetailActivity.this, message, Toast.LENGTH_LONG).show();
    }
}