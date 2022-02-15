package com.dindatria.shetpi.adapter;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.dindatria.shetpi.API.ApiClient;
import com.dindatria.shetpi.Model.DatamasukModel;
import com.dindatria.shetpi.Model.PengukuranModel;
import com.dindatria.shetpi.Model.PengukuranResponses;
import com.dindatria.shetpi.Model.PostPutDelDataResponses;
import com.dindatria.shetpi.R;
import com.dindatria.shetpi.UI.InputDataActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDataShetpi extends RecyclerView.Adapter<AdapterDataShetpi.ViewDataShetpi> {

    private List<PengukuranModel> pengukuranModelList;
    private Context context;
    private OnDelete onDelete;



    public AdapterDataShetpi(List<PengukuranModel> pengukuranModelList, Context context, OnDelete onDelete) {
        this.pengukuranModelList = pengukuranModelList;
        this.context = context;
        this.onDelete = onDelete;
    }

    @NonNull
    @Override
    public ViewDataShetpi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_masuk, parent, false);
        return new ViewDataShetpi(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewDataShetpi holder, int position) {
        PengukuranModel pengukuranModel = pengukuranModelList.get(position);
        holder.id_sapi.setText(pengukuranModel.getId_sapi()==null ? "Identitas Sapi Belum Dimasukkan" : (String.valueOf(pengukuranModel.getId_sapi())));
        holder.pulse.setText(String.valueOf(pengukuranModel.getDetak_jantung()));
        holder.themperature.setText(String.valueOf(pengukuranModel.getSuhu()));
        holder.time.setText(String.valueOf(pengukuranModel.getWaktu()));
        holder.berat_sapi.setText(String.valueOf(pengukuranModel.getBerat_sapii()));


        if (pengukuranModel.getSuhu() >= 36.7 && pengukuranModel.getSuhu() <= 39.1) {
            holder.status1.setText("Suhu Normal");
        } else {
            holder.status1.setText("Suhu Tidak Normal");
        }

        if (pengukuranModel.getDetak_jantung() >= 60 && pengukuranModel.getDetak_jantung() <= 90) {
            holder.status2.setText("Detak Jantung Normal");
        } else {
            holder.status2.setText("Detak jantung Tidak Normal");
        }

        holder.id_sapi.setOnClickListener(v -> {

            CoptText copy = new CoptText();
            Toast.makeText(context, copy.clipboardManager(context, holder.id_sapi.getText().toString()) + " berhasil di copy", Toast.LENGTH_SHORT).show();

        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Call<PostPutDelDataResponses> deleteData = ApiClient.getApiInterface().deleteData(pengukuranModel.getId());
                deleteData.enqueue(new Callback<PostPutDelDataResponses>() {
                    @Override
                    public void onResponse(Call<PostPutDelDataResponses> call, Response<PostPutDelDataResponses> response) {
                        if (response.isSuccessful()){
                            if (response.body().getStatus()==200){
                                onDelete.onLoaddelete(true, response.body().getMessage());
                            }else {
                                onDelete.onLoaddelete(false, response.body().getMessage());
                            }
                        }else {
                            onDelete.onLoaddelete(false, "Not Responses");

                        }
                    }

                    @Override
                    public void onFailure(Call<PostPutDelDataResponses> call, Throwable t) {
                        onDelete.onLoaddelete(false, "Error");
                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return pengukuranModelList.size();
    }

    public class ViewDataShetpi extends RecyclerView.ViewHolder {

        private TextView  pulse, time, themperature, C, status1, status2, id_sapi, berat_sapi, kg;
        private Button btnInput,btnDelete;


        public ViewDataShetpi(@NonNull View itemView) {
            super(itemView);

            pulse = itemView.findViewById(R.id.view_pulse);
            time = itemView.findViewById(R.id.view_time);
            themperature = itemView.findViewById(R.id.view_suhu);
            C = itemView.findViewById(R.id.view_C);
            status1 = itemView.findViewById(R.id.txt_status_suhu);
            status2 = itemView.findViewById(R.id.txt_status_pulse);
            id_sapi = itemView.findViewById(R.id.vw_id_sapi);
            btnDelete=itemView.findViewById(R.id.btn_delete);
            berat_sapi=itemView.findViewById(R.id.view_BeratSapi);
            kg = itemView.findViewById(R.id.view_Kg);
        }

    }

    public interface OnDelete{
        void onLoaddelete(boolean is_delete, String message);
    }



}
