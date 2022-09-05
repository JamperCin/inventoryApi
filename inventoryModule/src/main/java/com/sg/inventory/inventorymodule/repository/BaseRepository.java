package com.sg.inventory.inventorymodule.repository;

import androidx.lifecycle.ViewModel;

import com.sg.inventory.inventorymodule.config.ServiceConfig;
import com.sg.inventory.inventorymodule.model.RequestModel;

public class BaseRepository extends ViewModel {
    private int id;
    protected  ServiceConfig config;

    BaseRepository(ServiceConfig config){
        this.config = config;
    }


    protected RequestModel buildRequest(String method, Object params) {
        RequestModel request = new RequestModel();
        request.setJsonRpc("2.0");
        request.setId(getRequestId());
        request.setMethod(method);

        request.setParams(params);

        return request;
    }

    private synchronized String getRequestId() {
        return ++id + "";
    }

}
