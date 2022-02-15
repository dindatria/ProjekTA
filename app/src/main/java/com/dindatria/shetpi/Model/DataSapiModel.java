package com.dindatria.shetpi.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import okhttp3.MultipartBody;

//mengimpor model data ke android studio
public class DataSapiModel implements Parcelable {
    @SerializedName("foto_sapi")
    private String foto_sapi;

    @SerializedName("id_sapi2")
    private  String id_sapi2;

    @SerializedName("nama_sapi")
    private String nama_sapi;

    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;

    @SerializedName("tanggal_lahir")
    private  String tanggal_lahir;

    @SerializedName("umur")
    private String umur;

    @SerializedName("keterangan")

    private String keterangan;


    public static Creator<DataSapiModel> getCREATOR() {
        return CREATOR;
    }

    protected DataSapiModel(Parcel in) {
        foto_sapi = in.readString();
        id_sapi2 = in.readString();
        nama_sapi = in.readString();
        jenis_kelamin = in.readString();
        umur = in.readString();
        keterangan = in.readString();
        tanggal_lahir = in.readString();
    }

    public static final Creator<DataSapiModel> CREATOR = new Creator<DataSapiModel>() {
        @Override
        public DataSapiModel createFromParcel(Parcel in) {
            return new DataSapiModel(in);
        }

        @Override
        public DataSapiModel[] newArray(int size) {
            return new DataSapiModel[size];
        }
    };

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public String getFoto_sapi() {
        return foto_sapi;
    }

    public String getId_sapi2() {
        return id_sapi2;
    }

    public String getNama_sapi() {
        return nama_sapi;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getUmur() {
        return umur;
    }

    public String getKeterangan() {
        return keterangan;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(foto_sapi);
        dest.writeString(id_sapi2);
        dest.writeString(nama_sapi);
        dest.writeString(jenis_kelamin);
        dest.writeString(tanggal_lahir);
        dest.writeString(umur);
        dest.writeString(keterangan);

    }
}
