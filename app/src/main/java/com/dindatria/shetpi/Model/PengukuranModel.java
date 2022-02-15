package com.dindatria.shetpi.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PengukuranModel implements Parcelable{
    @SerializedName("suhu")
    private float suhu;

    @SerializedName("id")
    private int id;

    @SerializedName("detak_jantung")
    private int detak_jantung;

    @SerializedName("berat_sapii")
    private String berat_sapii;

    @SerializedName("id_sapi")
    private  String id_sapi;

    @SerializedName("waktu")
    private  String waktu;

    public int getId() {
        return id;
    }

    public float getSuhu() {
        return suhu;
    }

    public int getDetak_jantung() {
        return detak_jantung;
    }

    public String getBerat_sapii() {
        return berat_sapii;
    }

    public String getId_sapi() {
        return id_sapi;
    }

    public String getWaktu() {
        return waktu;
    }

    public static Creator<PengukuranModel> getCREATOR() {
        return CREATOR;
    }

    protected PengukuranModel(Parcel in) {
        id = in.readInt();
        suhu = in.readFloat();
        detak_jantung = in.readInt();
        berat_sapii = in.readString();
        waktu = in.readString();
        id_sapi = in.readString();
    }

    public static final Creator<PengukuranModel> CREATOR = new Creator<PengukuranModel>() {
        @Override
        public PengukuranModel createFromParcel(Parcel in) {
            return new PengukuranModel(in);
        }
        @Override
        public PengukuranModel[] newArray(int size) {
                return new PengukuranModel[size];}
    };
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeFloat(suhu);
        dest.writeInt(detak_jantung);
        dest.writeString(berat_sapii);
        dest.writeString(id_sapi);
        dest.writeString(waktu);
    }
}
