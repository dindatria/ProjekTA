package com.dindatria.shetpi.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;
//mengimpor model data ke android studio
public class GetDatamasuk {
    @SerializedName("id")
    private int id;

    @SerializedName("error")
    private boolean error;

    @SerializedName("message")
    private String message;

    @SerializedName("datamasuk")
    private List<DatamasukModel> datamasukModel;

    public int getId() {
        return id;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public List<DatamasukModel> getDatamasukModel() {
        return datamasukModel;
    }
}
