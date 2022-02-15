package com.dindatria.shetpi.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetHasilPengukuran {
    @SerializedName("id_sapi")
    private String id_sapi;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    public String isId_sapi() {
        return id_sapi;
    }

    @SerializedName("HasilDataMasuk")
    private List<PengukuranModel> pengukuranModels;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<PengukuranModel> getPengukuranModels() {
        return pengukuranModels;
    }
}
