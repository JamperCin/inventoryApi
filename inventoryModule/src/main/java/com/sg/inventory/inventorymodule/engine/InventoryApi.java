package com.sg.inventory.inventorymodule.engine;

import com.google.gson.JsonElement;
import com.sg.inventory.inventorymodule.callback.IRequestCallback;
import com.sg.inventory.inventorymodule.config.ServiceConfig;
import com.sg.inventory.inventorymodule.model.ResponseModel;
import com.sg.inventory.inventorymodule.repository.InventoryRepository;
import com.sg.inventory.inventorymodule.utils.JsonHelper;

import org.json.JSONObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InventoryApi {
    private String port;
    private String host;
    private boolean isHttps;
    private ServiceConfig config;
    private String token;
    private String rpc = "rpc/";

    /**
     * Set the PORT of the environment
     * eg: 8080
     *
     * @param port The port of the base url
     **/
    public InventoryApi setPort(String port) {
        this.port = port;
        return this;
    }

    /**
     * Set the HOST of the environment
     * eg: api.testserver.com
     *
     * @param host The host of the base url
     **/
    public InventoryApi setHost(String host) {
        this.host = host;
        return this;
    }

    /**
     * A value to determine whether the base url is https or http
     * eg: http:// or https://
     *
     * @param isHttps The bolean value to determine whether the base url is https or http
     **/
    public InventoryApi setIsHttps(boolean isHttps) {
        this.isHttps = isHttps;
        return this;
    }

    /**
     * A value to add to every api call to precede the method call
     * eg: /rpc/
     *
     * @param rpc The prefix label to each api method call
     **/
    public InventoryApi setRpc(String rpc) {
        this.rpc = (rpc == null) ? "" : rpc;
        return this;
    }

    /**
     * A header value to be passed to api for authorisation
     * eg: ddajnlksd75ss656dsdsO86sTsbkssgFShdv_Sdbkbks9565d4Pdgs6d38sOSd
     *
     * @param token This can be optional, but required when api requires token as header
     *              before it can be accessed.
     **/
    public InventoryApi setToken(String token) {
        this.token = token;
        return this;
    }

    public InventoryApi build() {
        String environment = (isHttps ? "https://" : "http://") + host + ":" + port + "/" + rpc;
        environment = !environment.endsWith("/") ? (environment + "/") : environment;
        config = new ServiceConfig(environment, token);

        return this;
    }


    /**
     * =============== P R O C E S S    R E S P O N S E ============================================
     * An Engine to process all responses from the backend. This will check for errors and successful
     * responses and return them accordingly
     *
     * @param call        The request that needs to be enqueued
     * @param resultClass The Generic class to receive response onBehalf of the calling Activity/Fragment
     *                    To be specific, generic class models the response form the Json Key "result" from the
     *                    response. Sample of the main response may look like:
     *                    <p>
     *                    {
     *                    "jsonrpc": "2.0",
     *                    "id": 3,
     *                    "result": {}
     *                    }
     *                    </p>
     *                    <p>
     *                    The "result" json key is picked from the response and converted into the generic class passed.
     *                    This can be optional, when calling Activity/Fragment wants it in the form of a Map.
     *                    An example is :
     *                    <p>
     *                    Map<String, Object> resultModel = (Map<String, Object>) response.getResults();
     *                    </p>
     * @param callback    The callBack to listen on by any calling Activity or Fragment
     *                    =============================================================================
     **/
    private <T> void processResponse(Call<JsonElement> call, final Class<T> resultClass, IRequestCallback<ResponseModel<T>> callback) {
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                if (response.body() == null || response.body().getAsJsonObject() == null) {
                    if (callback != null) callback.onFailure("An unknown error occurred");
                    return;
                }

                try {

                    ResponseModel resp = JsonHelper.fromJson(response.body().getAsJsonObject().toString(), ResponseModel.class);

                    if (resultClass != null && resp.getResults() != null) {
                        Object clazz = JsonHelper.fromJson(new JSONObject((Map<String, Object>) resp.getResults()).toString(), resultClass);
                        resp.setResults(clazz);
                    }


                    if (resp.getError() == null) {
                        if (callback != null) callback.onSuccess(resp);
                    } else {
                        if (callback != null) callback.onFailure(resp.getError().getMessage());
                    }
                } catch (Exception e) {
                    if (callback != null) callback.onFailure(e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                if (callback != null) callback.onFailure(t.getLocalizedMessage());
            }
        });
    }


    /**
     * Get the list of orders made or Inventories created
     *
     * @param params      The parameters to be passed to the api by the calling Fragment or Activity
     * @param resultClass The Generic class to receive response onBehalf of the calling Activity/Fragment
     *                    To be specific, generic class models the response form the Json Key "result" from the
     *                    response. Sample of the main response may look like:
     *                    <p>
     *                    {
     *                    "jsonrpc": "2.0",
     *                    "id": 3,
     *                    "result": {}
     *                    }
     *                    </p>
     *                    <p>
     *                    The "result" json key is picked from the response and converted into the generic class passed.
     *                    This can be optional, when calling Activity/Fragment wants it in the form of a Map.
     *                    An example is :
     *                    <p>
     *                    Map<String, Object> resultModel = (Map<String, Object>) response.getResults();
     *                    </p>
     * @param callback    The callBack to listen to response from API by any calling Activity/Fragment
     **/
    public <T> void fetchRequisitions(Object params, Class<T> resultClass, IRequestCallback<ResponseModel<T>> callback) {
        InventoryRepository mRepo = InventoryRepository.getInstance(config);

        Call<JsonElement> call = mRepo.getListOfInventory(params);
        processResponse(call, resultClass, callback);
    }


    /**
     * Get the list of orders made or Inventories created
     *
     * @param params      The parameters to be passed to the api by the calling Fragment or Activity
     * @param resultClass The Generic class to receive response onBehalf of the calling Activity/Fragment
     *                    To be specific, generic class models the response form the Json Key "result" from the
     *                    response. Sample of the main response may look like:
     *                    <p>
     *                    {
     *                    "jsonrpc": "2.0",
     *                    "id": 3,
     *                    "result": {}
     *                    }
     *                    </p>
     *                    <p>
     *                    The "result" json key is picked from the response and converted into the generic class passed.
     *                    This can be optional, when calling Activity/Fragment wants it in the form of a Map.
     *                    An example is :
     *                    <p>
     *                    Map<String, Object> resultModel = (Map<String, Object>) response.getResults();
     *                    </p>
     * @param callback    The callBack to listen to response from API by any calling Activity/Fragment
     **/
    public <T> void addRequisition(Object params, Class<T> resultClass, IRequestCallback<ResponseModel<T>> callback) {
        InventoryRepository mRepo = InventoryRepository.getInstance(config);

        Call<JsonElement> call = mRepo.createInventory(params);
        processResponse(call, resultClass, callback);
    }

    /**
     * This service is used for requesting the various inventory categories and
     * subcategories already configured in the system
     *
     * @param resultClass The Generic class to receive response onBehalf of the calling Activity/Fragment
     *                    To be specific, generic class models the response form the Json Key "result" from the
     *                    response. Sample of the main response may look like:
     *                    <p>
     *                    {
     *                    "jsonrpc": "2.0",
     *                    "id": 3,
     *                    "result": {}
     *                    }
     *                    </p>
     *                    <p>
     *                    The "result" json key is picked from the response and converted into the generic class passed.
     *                    This can be optional, when calling Activity/Fragment wants it in the form of a Map.
     *                    An example is :
     *                    <p>
     *                    Map<String, Object> resultModel = (Map<String, Object>) response.getResults();
     *                    </p>
     * @param callback    The callBack to listen to response from API by any calling Activity/Fragment
     **/
    public <T> void getCategories(Class<T> resultClass, IRequestCallback<ResponseModel<T>> callback) {
        InventoryRepository mRepo = InventoryRepository.getInstance(config);

        Call<JsonElement> call = mRepo.getInventoryCategories();
        processResponse(call, resultClass, callback);
    }

    /**
     * This service is used to fetch an array of allocated requisitions
     *
     * @param params      The parameters to be passed to the api by the calling Fragment or Activity
     * @param resultClass The Generic class to receive response onBehalf of the calling Activity/Fragment
     *                    To be specific, generic class models the response form the Json Key "result" from the
     *                    response. Sample of the main response may look like:
     *                    <p>
     *                    {
     *                    "jsonrpc": "2.0",
     *                    "id": 3,
     *                    "result": {}
     *                    }
     *                    </p>
     *                    <p>
     *                    The "result" json key is picked from the response and converted into the generic class passed.
     *                    This can be optional, when calling Activity/Fragment wants it in the form of a Map.
     *                    An example is :
     *                    <p>
     *                    Map<String, Object> resultModel = (Map<String, Object>) response.getResults();
     *                    </p>
     * @param callback    The callBack to listen to response from API by any calling Activity/Fragment
     **/
    public <T> void getAllocatedItems(Object params, Class<T> resultClass, IRequestCallback<ResponseModel<T>> callback) {
        InventoryRepository mRepo = InventoryRepository.getInstance(config);

        Call<JsonElement> call = mRepo.getAllocatedItems(params);
        processResponse(call, resultClass, callback);
    }

    /**
     * This service is used to get an allocated requisition by specifying the allocationId in the params
     *
     * @param params      The parameters to be passed to the api by the calling Fragment or Activity
     * @param resultClass The Generic class to receive response onBehalf of the calling Activity/Fragment
     *                    To be specific, generic class models the response form the Json Key "result" from the
     *                    response. Sample of the main response may look like:
     *                    <p>
     *                    {
     *                    "jsonrpc": "2.0",
     *                    "id": 3,
     *                    "result": {}
     *                    }
     *                    </p>
     *                    <p>
     *                    The "result" json key is picked from the response and converted into the generic class passed.
     *                    This can be optional, when calling Activity/Fragment wants it in the form of a Map.
     *                    An example is :
     *                    <p>
     *                    Map<String, Object> resultModel = (Map<String, Object>) response.getResults();
     *                    </p>
     * @param callback    The callBack to listen to response from API by any calling Activity/Fragment
     **/
    public <T> void getAnAllocatedItem(Object params, Class<T> resultClass, IRequestCallback<ResponseModel<T>> callback) {
        InventoryRepository mRepo = InventoryRepository.getInstance(config);

        Call<JsonElement> call = mRepo.getAllocatedItem(params);
        processResponse(call, resultClass, callback);
    }

    /**
     * This service is used to accept allocated requisition
     *
     * @param params      The parameters to be passed to the api by the calling Fragment or Activity
     * @param resultClass The Generic class to receive response onBehalf of the calling Activity/Fragment
     *                    To be specific, generic class models the response form the Json Key "result" from the
     *                    response. Sample of the main response may look like:
     *                    <p>
     *                    {
     *                    "jsonrpc": "2.0",
     *                    "id": 3,
     *                    "result": {}
     *                    }
     *                    </p>
     *                    <p>
     *                    The "result" json key is picked from the response and converted into the generic class passed.
     *                    This can be optional, when calling Activity/Fragment wants it in the form of a Map.
     *                    An example is :
     *                    <p>
     *                    Map<String, Object> resultModel = (Map<String, Object>) response.getResults();
     *                    </p>
     * @param callback    The callBack to listen to response from API by any calling Activity/Fragment
     **/
    public <T> void acceptAllocatedItem(Object params, Class<T> resultClass, IRequestCallback<ResponseModel<T>> callback) {
        InventoryRepository mRepo = InventoryRepository.getInstance(config);

        Call<JsonElement> call = mRepo.acceptAllocatedItem(params);
        processResponse(call, resultClass, callback);
    }

    /**
     * This service is used to reject an allocated requisition by specifying the allocationId
     * and a reason for rejection
     *
     * @param params      The parameters to be passed to the api by the calling Fragment or Activity
     * @param resultClass The Generic class to receive response onBehalf of the calling Activity/Fragment
     *                    To be specific, generic class models the response form the Json Key "result" from the
     *                    response. Sample of the main response may look like:
     *                    <p>
     *                    {
     *                    "jsonrpc": "2.0",
     *                    "id": 3,
     *                    "result": {}
     *                    }
     *                    </p>
     *                    <p>
     *                    The "result" json key is picked from the response and converted into the generic class passed.
     *                    This can be optional, when calling Activity/Fragment wants it in the form of a Map.
     *                    An example is :
     *                    <p>
     *                    Map<String, Object> resultModel = (Map<String, Object>) response.getResults();
     *                    </p>
     * @param callback    The callBack to listen to response from API by any calling Activity/Fragment
     **/
    public <T> void rejectAllocatedItem(Object params, Class<T> resultClass, IRequestCallback<ResponseModel<T>> callback) {
        InventoryRepository mRepo = InventoryRepository.getInstance(config);

        Call<JsonElement> call = mRepo.rejectAllocatedItem(params);
        processResponse(call, resultClass, callback);
    }

}
