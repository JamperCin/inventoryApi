package com.sg.inventory.inventorymodule.repository;

import com.google.gson.JsonElement;
import com.sg.inventory.inventorymodule.config.ServiceConfig;
import com.sg.inventory.inventorymodule.model.RequestModel;
import com.sg.inventory.inventorymodule.model.ResponseModel;
import com.sg.inventory.inventorymodule.net.ApiService;

import retrofit2.Call;

public class InventoryRepository extends BaseRepository {
    private static volatile InventoryRepository instance;

    InventoryRepository(ServiceConfig config) {
        super(config);
    }


    public static InventoryRepository getInstance(ServiceConfig config) {
        if (instance == null) {
            synchronized (InventoryRepository.class) {
                instance = new InventoryRepository(config);
            }
        }
        return instance;
    }


    /**
     * Get the list of orders made or Inventories created.
     * This service is used to fetch the requisitions made
     *
     * @param params The parameters to be passed to the api by the calling Fragment or Activity
     *
     * @return*/
    public Call<JsonElement> getListOfInventory(Object params) {
        RequestModel request = buildRequest("inventory.requisition.fetch", params);
        return ApiService.getApiClient(config.getEnvironment()).getInventories(config.getToken(), request);
    }

    /**
     * Get the list of orders made or Inventories created
     *
     * @param params The parameters to be passed to the api by the calling Fragment or Activity
     **/
    public Call<JsonElement> createInventory(Object params) {
        RequestModel request = buildRequest("inventory.requisition.add", params);
        return ApiService.getApiClient(config.getEnvironment()).createOrder(config.getToken(), request);
    }

    /**
     * This service is used for requesting the various inventory categories and
     * subcategories already configured in the system
     **/
    public Call<JsonElement> getInventoryCategories() {
        RequestModel request = buildRequest("inventory.category.categories", null);
        return ApiService.getApiClient(config.getEnvironment()).getInventoryCategories(config.getToken(), request);
    }

    /**
     * This service is used to fetch an array of allocated requisitions
     *
     * @param params The parameters to be passed to the api by the calling Fragment or Activity
     **/
    public Call<JsonElement> getAllocatedItems(Object params) {
        RequestModel request = buildRequest("inventory.receive.fetch", params);
        return ApiService.getApiClient(config.getEnvironment()).getAllocatedItems(config.getToken(), request);
    }

    /**
     * This service is used to get an allocated requisition by specifying the allocationId in the params
     *
     * @param params The parameters to be passed to the api by the calling Fragment or Activity
     **/
    public Call<JsonElement> getAllocatedItem(Object params) {
        RequestModel request = buildRequest("inventory.receive.get", params);
        return ApiService.getApiClient(config.getEnvironment()).getAllocatedItem(config.getToken(), request);
    }

    /**
     * This service is used to accept allocated requisition
     *
     * @param params The parameters to be passed to the api by the calling Fragment or Activity
     **/
    public Call<JsonElement> acceptAllocatedItem(Object params) {
        RequestModel request = buildRequest("inventory.receive.accept", params);
        return ApiService.getApiClient(config.getEnvironment()).acceptAllocatedItem(config.getToken(), request);
    }

    /**
     * This service is used to reject allocated requisition
     *
     * @param params The parameters to be passed to the api by the calling Fragment or Activity
     **/
    public Call<JsonElement> rejectAllocatedItem(Object params) {
        RequestModel request = buildRequest("inventory.receive.reject", params);
        return ApiService.getApiClient(config.getEnvironment()).rejectAllocatedItem(config.getToken(), request);
    }


}
