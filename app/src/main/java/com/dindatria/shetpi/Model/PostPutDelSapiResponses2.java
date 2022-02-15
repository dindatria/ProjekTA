package com.dindatria.shetpi.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelSapiResponses2 {

    @SerializedName("id_sapi2")
    private String id_sapi2;

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private DataSapiModel dataSapiModel;

    public String getId_sapi2() {
        return id_sapi2;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public DataSapiModel getDataSapiModel() {
        return dataSapiModel;
    }


}
