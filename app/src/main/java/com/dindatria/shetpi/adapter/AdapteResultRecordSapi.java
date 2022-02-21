package com.dindatria.shetpi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dindatria.shetpi.Model.PengukuranModel;
import com.dindatria.shetpi.R;

import java.util.List;

public class AdapteResultRecordSapi extends RecyclerView.Adapter<AdapteResultRecordSapi.ViewHolderResultRecord> {

    private List<PengukuranModel> pengukuranModelList;
    private Context context;

    public AdapteResultRecordSapi(List<PengukuranModel> pengukuranModelList, Context context) {
        this.pengukuranModelList = pengukuranModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderResultRecord onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_record, parent, false);
        return new ViewHolderResultRecord(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderResultRecord holder, int position) {
        PengukuranModel pengukuranModel = pengukuranModelList.get(position);

        holder.tanggal.setText(context.getResources().getString(R.string.txt_tanggal) + " "+ pengukuranModel.getWaktu());
        holder.berat.setText(context.getResources().getString(R.string.txt_berat) + " "+pengukuranModel.getBerat_sapii());
        holder.suhu.setText(context.getResources().getString(R.string.txt_suhu) + " "+ pengukuranModel.getSuhu());
        holder.detak_jantung.setText(context.getResources().getString(R.string.txt_detak_jantung) + " "+ pengukuranModel.getDetak_jantung());
    }

    @Override
    public int getItemCount() {
        return pengukuranModelList.size();
    }

    public class ViewHolderResultRecord extends RecyclerView.ViewHolder {

        private TextView tanggal, suhu, berat, detak_jantung;

        public ViewHolderResultRecord(@NonNull View itemView) {
            super(itemView);

            suhu = itemView.findViewById(R.id.suhu);
            tanggal = itemView.findViewById(R.id.record_date);
            berat = itemView.findViewById(R.id.berat);
            detak_jantung = itemView.findViewById(R.id.detak_jantung);
        }
    }
}
