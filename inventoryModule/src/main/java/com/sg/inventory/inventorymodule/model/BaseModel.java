package com.sg.inventory.inventorymodule.model;

import com.google.gson.annotations.SerializedName;

public class BaseModel {
    @SerializedName("jsonrpc")
    protected String jsonRpc;
    protected String id;

    public String getJsonRpc() {
        return jsonRpc;
    }

    public void setJsonRpc(String jsonRpc) {
        this.jsonRpc = jsonRpc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
