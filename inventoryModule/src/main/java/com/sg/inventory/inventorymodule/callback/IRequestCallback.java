package com.sg.inventory.inventorymodule.callback;

import com.sg.inventory.inventorymodule.model.ResponseModel;

public interface IRequestCallback <T>{
    void onSuccess(ResponseModel<T> response);
    void onFailure(String error);
}
