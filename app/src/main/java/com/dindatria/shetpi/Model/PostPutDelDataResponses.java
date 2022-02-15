package com.dindatria.shetpi.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelDataResponses {

    @SerializedName("id_sapi")
    private String id_sapi;

    @SerializedName("id")
    private int id;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private int status;

    public String getId_sapi() {
        return id_sapi;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
