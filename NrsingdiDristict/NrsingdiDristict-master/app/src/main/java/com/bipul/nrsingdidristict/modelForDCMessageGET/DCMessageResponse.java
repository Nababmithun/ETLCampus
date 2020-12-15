
package com.bipul.nrsingdidristict.modelForDCMessageGET;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DCMessageResponse {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
