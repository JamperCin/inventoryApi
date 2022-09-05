package com.sg.inventory.inventorymodule.model;

public class RequestModel extends BaseModel {
    private static final long serialVersionUID = 1L;
    private String method;
    private Object params;

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestModel)) return false;

        RequestModel utRequest = (RequestModel) o;

        if (id != null ? !id.equals(utRequest.id) : utRequest.id != null) return false;
        if (jsonRpc != null ? !jsonRpc.equals(utRequest.jsonRpc) : utRequest.jsonRpc != null)
            return false;
        if (method != null ? !method.equals(utRequest.method) : utRequest.method != null)
            return false;
        if (params != null ? !params.equals(utRequest.params) : utRequest.params != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = jsonRpc != null ? jsonRpc.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("{jsonrpc:'%s', id:'%s', method:'%s', authentication:'%s', params:'%s'}", jsonRpc, id, method , params != null ? params.toString() : "null");
    }
}
