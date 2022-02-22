package com.dindatria.shetpi.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dindatria.shetpi.API.ApiClient;
import com.dindatria.shetpi.Model.GetHasilPengukuran;
import com.dindatria.shetpi.R;
import com.dindatria.shetpi.adapter.AdapteResultRecordSapi;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultRecordSampleSapiActivity extends AppCompatActivity {

    private RecyclerView rv_result;
    private ProgressBar loading;
    public static final String EXTRA_ID_SAPI = "extra_id_sapi";
    private String id_sapi = null;
    private TextView data_not_available;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_record_sample_sapi);

        getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rv_result = findViewById(R.id.rv_result_record);
        loading = findViewById(R.id.loading);
        data_not_available = findViewById(R.id.data_not_available);

        if (getIntent() != null) {
            id_sapi = getIntent().getStringExtra(EXTRA_ID_SAPI);
        }

        loadData(id_sapi);
    }

    private void loadData(String id_sapi) {
        loading.setVisibility(View.VISIBLE);
        Call<GetHasilPengukuran> getHasilPengukuranCall = ApiClient.getApiInterface().getHasilPengukuranByIDSapi(id_sapi);
        getHasilPengukuranCall.enqueue(new Callback<GetHasilPengukuran>() {
            //  pengambilan data
            @Override
            public void onResponse(Call<GetHasilPengukuran> call, Response<GetHasilPengukuran> response) {
                loading.setVisibility(View.GONE);
                if (response.isSuccessful()) {

                    if (response.body().getPengukuranModels().size() == 0) {
                        data_not_available.setVisibility(View.VISIBLE);
                    } else {
                        data_not_available.setVisibility(View.GONE);
                        AdapteResultRecordSapi adapteResultRecordSapi = new AdapteResultRecordSapi(response.body().getPengukuranModels(), ResultRecordSampleSapiActivity.this);
                        adapteResultRecordSapi.notifyDataSetChanged();

                        rv_result.setLayoutManager(new LinearLayoutManager(ResultRecordSampleSapiActivity.this));
                        rv_result.setHasFixedSize(true);
                        rv_result.setAdapter(adapteResultRecordSapi);
                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Tidak Ada Response Server", Toast.LENGTH_LONG).show();
                }
            }

            public void onFailure(Call<GetHasilPengukuran> call, Throwable t) {
                Toast.makeText(ResultRecordSampleSapiActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}