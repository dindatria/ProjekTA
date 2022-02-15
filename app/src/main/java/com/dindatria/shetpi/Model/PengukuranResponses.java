package com.dindatria.shetpi.Model;

import com.google.gson.annotations.SerializedName;

public class PengukuranResponses {
    @SerializedName("id_sapi")
    private String id_sapi;

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private PengukuranModel pengukuranModel;

    public String getId_sapi() {
        return id_sapi;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public PengukuranModel getPengukuranModel() {
        return pengukuranModel;
    }
}
