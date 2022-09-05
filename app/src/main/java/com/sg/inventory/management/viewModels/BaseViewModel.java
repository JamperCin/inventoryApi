package com.sg.inventory.management.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sg.inventory.inventorymodule.engine.InventoryApi;

public class BaseViewModel extends ViewModel {
    protected InventoryApi inventoryApi;
    protected MutableLiveData<String> failureLiveData = new MutableLiveData<>();
    protected MutableLiveData<Boolean> onProgressLoading = new MutableLiveData<>();

    BaseViewModel(){
        initApi();
    }

    private void initApi(){
        inventoryApi = new InventoryApi()
                .setHost("10.16.8.1")
                .setPort("8080")
                .setIsHttps(false)
                .setToken("wesdfg_gvjkk63535353gjk6gfg86hkk")
                .build();

    }


    public MutableLiveData<String> onFailure(){
        return  failureLiveData;
    }

    public MutableLiveData<Boolean> getOnProgressLoading() {
        return onProgressLoading;
    }
}
