package com.dindatria.shetpi.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dindatria.shetpi.API.ApiClient;
import com.dindatria.shetpi.Model.DatamasukModel;
import com.dindatria.shetpi.Model.GetDatamasuk;
import com.dindatria.shetpi.Model.PengukuranResponses;
import com.dindatria.shetpi.R;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengukuranActivitity extends AppCompatActivity {

    private TextView suhu,detakjantung;
    private Timer timer = new Timer();
    private Button btn_submit;
    private EditText berat_badan,id_sapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengukuran);

        getWindow().setFlags(WindowManager.LayoutParams.
                FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        suhu = findViewById(R.id.view_suhu1);
        detakjantung = findViewById(R.id.view_detakjantung1);
        btn_submit = findViewById(R.id.btn_SimpanSapi);
        berat_badan =findViewById(R.id.editBeratSapi);
        id_sapi =findViewById(R.id.editIdSapi);
        getLastdatamasuk();

        btn_submit.setOnClickListener(v -> {
            if (berat_badan.getText().toString().isEmpty()){
                berat_badan.setError("Wajib disii");
                berat_badan.requestFocus();
                return;
            }
            if (id_sapi.getText().toString().isEmpty()){
                id_sapi.setError("Wajib diisi");
                id_sapi.requestFocus();
            }
        Call<PengukuranResponses> pengukuranResponsesCall = ApiClient.getApiInterface().tambahpengukuran
                (id_sapi.getText().toString(),suhu.getText().toString(),detakjantung.getText().toString(),berat_badan.getText().toString());

        pengukuranResponsesCall.enqueue(new Callback<PengukuranResponses>() {
            @Override
            public void onResponse(Call<PengukuranResponses> call, Response<PengukuranResponses> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() == 200) {

                        Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Tambah Data GAGAL", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Tidak Ada Response", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PengukuranResponses> call, Throwable t) {

            }
        });
            Intent intent = new Intent(PengukuranActivitity.this, RecyclerviewDataActivity.class);
            startActivity(intent);
        });

    }

    private void getLastdatamasuk(){
        Call<GetDatamasuk> getDatamasukCall = ApiClient.getApiInterface().getDataterahir();
        getDatamasukCall.enqueue(new Callback<GetDatamasuk>() {
            @Override
            public void onResponse(Call<GetDatamasuk> call, Response<GetDatamasuk> response) {
                if (response.isSuccessful()){

                    for (DatamasukModel datamasukModel : response.body().getDatamasukModel()){
                        detakjantung.setText(String.valueOf(datamasukModel.getDetak_jantung()));
                        suhu.setText(String.valueOf(datamasukModel.getSuhu()));

                        setTimer();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetDatamasuk> call, Throwable t) {

            }
        });
    }

    private void setTimer() {
        final Handler handler = new Handler();
        timer = new Timer();
        TimerTask doTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(() -> {
                    try {
                        //kondisi
                        getLastdatamasuk();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        };
        timer.schedule(doTask, 1000);
    }
}