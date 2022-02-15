package com.dindatria.shetpi.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DatamasukModel implements Parcelable {
    @SerializedName("id")
    private int id;

    @SerializedName("suhu")
    private float suhu;

    @SerializedName("detak_jantung")
    private int detak_jantung;

    @SerializedName("waktu")
    private String waktu;

    @SerializedName("id_sapi")
    private String id_sapi;

    public DatamasukModel(Parcel in) {
        id = in.readInt();
        suhu = in.readFloat();
        detak_jantung = in.readInt();
        waktu = in.readString();
        id_sapi = in.readString();
    }

    public static final Creator<DatamasukModel> CREATOR = new Creator<DatamasukModel>() {
        @Override
        public DatamasukModel createFromParcel(Parcel in) {
            return new DatamasukModel(in);
        }

        @Override
        public DatamasukModel[] newArray(int size) {
            return new DatamasukModel[size];
        }
    };

    public DatamasukModel(String waktu, float suhu, int detak_jantung) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSuhu() {
        return suhu;
    }

    public void setSuhu(float suhu) {
        this.suhu = suhu;
    }

    public int getDetak_jantung() {
        return detak_jantung;
    }

    public void setDetak_jantung(int detak_jantung) {
        this.detak_jantung = detak_jantung;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getId_sapi() {
        return id_sapi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeFloat(suhu);
        dest.writeInt(detak_jantung);
        dest.writeString(waktu);
        dest.writeString(id_sapi);
    }
}
