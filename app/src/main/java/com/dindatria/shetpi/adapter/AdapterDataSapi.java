package com.dindatria.shetpi.adapter;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dindatria.shetpi.API.ApiClient;
import com.dindatria.shetpi.Model.DataSapiModel;
import com.dindatria.shetpi.Model.PostPutDelSapiResponses2;
import com.dindatria.shetpi.R;
import com.dindatria.shetpi.utils.DateFormatLib;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDataSapi extends RecyclerView.Adapter<AdapterDataSapi.ViewDataSapi> {

    private List<DataSapiModel> dataSapiModelList;
    private Context context;
    private OnDelete2 onDelete2;
    private DateFormatLib dateFormatLib;
    private  int year,month,day;
    private String umur_sapi;
    private Activity activity;
    private LayoutInflater inflater;


    public AdapterDataSapi(List<DataSapiModel> dataSapiModelList, Context context, OnDelete2 onDelete2, Activity activity) {
        this.dataSapiModelList = dataSapiModelList;
        this.context = context;
        this.onDelete2= onDelete2;
        this.activity=activity;
    }

    @NonNull
    @Override
    public AdapterDataSapi.ViewDataSapi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_sapi, parent, false);
        return new ViewDataSapi(view);

    }
    @Override
    public void onBindViewHolder(@NonNull AdapterDataSapi.ViewDataSapi holder, int position) {

            DataSapiModel dataSapiModel=dataSapiModelList.get(position);
            Glide.with(context).load("http://18.214.164.0/Ci_shetpi2/gambar/"+dataSapiModel.getFoto_sapi()).
                    error(R.drawable.error).into(holder.foto_sapi);
            holder.id_sapi2.setText(String.valueOf(dataSapiModel.getId_sapi2()));
            holder.nama_sapi.setText(String.valueOf(dataSapiModel.getNama_sapi()));
            holder.tgl_lahir.setText(String.valueOf(dataSapiModel.getTanggal_lahir()));
            holder.umur.setText(String.valueOf(dataSapiModel.getUmur()));
            holder.jenis_kelamin.setText(String.valueOf(dataSapiModel.getJenis_kelamin()));
            holder.keterangan.setText(String.valueOf(dataSapiModel.getKeterangan()));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date birthSapi = sdf.parse(dataSapiModel.getTanggal_lahir());

            Date now = new Date(System.currentTimeMillis());

            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(now.getTime()-birthSapi.getTime());

            year = c.get(Calendar.YEAR)-1970;
            month = c.get(Calendar.MONTH);
//            day = c.get(Calendar.DAY_OF_MONTH);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (year==0 && month>0){
            umur_sapi = month + " Bulan ";
        }else {
            umur_sapi = year + " Tahun " + month + " Bulan ";
        }
        holder.umur.setText(umur_sapi);
        holder.btn_delete2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Call<PostPutDelSapiResponses2> deleteData2 = ApiClient.getApiInterface().deleteData2(dataSapiModel.getId_sapi2());
                    deleteData2.enqueue(new Callback<PostPutDelSapiResponses2>() {
                        @Override
                        public void onResponse(Call<PostPutDelSapiResponses2> call, Response<PostPutDelSapiResponses2> response) {
                            if (response.isSuccessful()){
                                if (response.body().getStatus()==200){
                                    onDelete2.onLoaddelete2(true, response.body().getMessage());
                                }else {
                                    onDelete2.onLoaddelete2(false, response.body().getMessage());
                                }
                            }else {
                                onDelete2.onLoaddelete2(false, "Not Responses");

                            }
                        }

                        @Override
                        public void onFailure(Call<PostPutDelSapiResponses2> call, Throwable t) {
                            onDelete2.onLoaddelete2(false, "Error");
                        }
                    });
                }
            });


    }

    @Override
    public int getItemCount() {
        return dataSapiModelList.size();

    }

    public class ViewDataSapi extends RecyclerView.ViewHolder {
        private TextView nama_sapi,umur,jenis_kelamin,keterangan,id_sapi2,tgl_lahir;
        private ImageView foto_sapi;
        private Button btn_delete2;
        private SearchView searchView;

        public ViewDataSapi(@NonNull View itemView) {
            super(itemView);

            foto_sapi=itemView.findViewById(R.id.img_viewsapi);
            id_sapi2=itemView.findViewById(R.id.view_IdSapi2);
            nama_sapi=itemView.findViewById(R.id.view_NamaSapi);
            umur=itemView.findViewById(R.id.view_Umur);
            jenis_kelamin=itemView.findViewById(R.id.view_Jekel);
            keterangan=itemView.findViewById(R.id.view_Ket);
            btn_delete2=itemView.findViewById(R.id.btn_delete2);
            tgl_lahir=itemView.findViewById(R.id.view_TglLahir);
            searchView=itemView.findViewById(R.id.actionSearch);
        }
    }

    public interface OnDelete2{
        void onLoaddelete2(boolean is_delete, String message);
    }
}
