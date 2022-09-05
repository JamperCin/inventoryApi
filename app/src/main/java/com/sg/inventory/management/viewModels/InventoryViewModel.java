package com.sg.inventory.management.viewModels;

import androidx.lifecycle.MutableLiveData;

import com.sg.inventory.inventorymodule.callback.IRequestCallback;
import com.sg.inventory.inventorymodule.model.ResponseModel;
import com.sg.inventory.management.models.RequisitionsModel;
import com.sg.inventory.management.models.ResultModel;

import java.util.List;
import java.util.Map;

public class InventoryViewModel extends BaseViewModel{
    private MutableLiveData<List<RequisitionsModel>> requisitionsLiveData = new MutableLiveData<>();



    public void fetchRequisitions() {
        onProgressLoading.setValue(true);

        //When passing a Result Class to receive
        inventoryApi.fetchRequisitions(null,ResultModel.class, new IRequestCallback<ResponseModel<ResultModel>>() {
            @Override
            public void onFailure(String error) {
                failureLiveData.setValue(error);
                onProgressLoading.setValue(false);
            }

            @Override
            public void onSuccess(ResponseModel<ResponseModel<ResultModel>> response) {
                onProgressLoading.setValue(false);
                ResultModel resultModel = (ResultModel)response.getResults();
                requisitionsLiveData.setValue(resultModel.getRequisitions());
            }
        } );
    }

    public MutableLiveData<List<RequisitionsModel>> getRequisitionsLiveData() {
        return requisitionsLiveData;
    }
}
