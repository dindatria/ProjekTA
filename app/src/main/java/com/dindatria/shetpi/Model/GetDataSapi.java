package com.dindatria.shetpi.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDataSapi {
    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("data_sapi")
    private List<DataSapiModel> dataSapiModels;

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<DataSapiModel> getDataSapiModels() {
        return dataSapiModels;
    }
}
