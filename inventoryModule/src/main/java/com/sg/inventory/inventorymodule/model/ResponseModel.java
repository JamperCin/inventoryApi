package com.sg.inventory.inventorymodule.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ResponseModel<T> extends BaseModel implements Serializable {

    private ErrorModel error;
    @SerializedName(value = "result", alternate = "results")
    private T results;

    public ErrorModel getError() {
        return error;
    }

    public void setError(ErrorModel error) {
        this.error = error;
    }

    public Object getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }
}
