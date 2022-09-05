package com.sg.inventory.inventorymodule.net;

import com.google.gson.JsonElement;
import com.sg.inventory.inventorymodule.model.RequestModel;
import com.sg.inventory.inventorymodule.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("inventory/requisition/fetch")
    Call<JsonElement> getInventories(@Header("x-xsrf-token") String token, @Body RequestModel request);

    @POST("inventory/requisition/add")
    Call<JsonElement> createOrder(@Header("x-xsrf-token") String token, @Body RequestModel request);

    @POST("inventory/category/categories")
     Call<JsonElement> getInventoryCategories(@Header("x-xsrf-token") String token, @Body RequestModel request);

    @POST("inventory/receive/fetch")
     Call<JsonElement> getAllocatedItems(@Header("x-xsrf-token") String token, @Body RequestModel request);

    @POST("inventory/receive/get")
     Call<JsonElement> getAllocatedItem(@Header("x-xsrf-token") String token, @Body RequestModel request);

    @POST("inventory/receive/accept")
     Call<JsonElement> acceptAllocatedItem(@Header("x-xsrf-token") String token, @Body RequestModel request);

    @POST("inventory/receive/reject")
     Call<JsonElement> rejectAllocatedItem(@Header("x-xsrf-token") String token, @Body RequestModel request);

}
