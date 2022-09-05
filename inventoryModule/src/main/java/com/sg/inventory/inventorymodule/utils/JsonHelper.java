package com.sg.inventory.inventorymodule.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sg.inventory.inventorymodule.model.ResponseModel;

import java.lang.reflect.Type;

public class JsonHelper {

    public static <T> T fromJson(String obj, Class<T> transaction) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(obj, (Type) transaction);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T fromJsonType(String obj, T type) {
        try {
            Gson gson = new Gson();
            //  .serializeNulls().create();
            return gson.fromJson(obj, (Type) type);
        } catch (Exception e) {
            return null;
        }
    }

    public static  <T> ResponseModel<T> jsonConvert(Class<T> types, String json) {

        GsonBuilder gson = new GsonBuilder();
        Type type = new TypeToken<ResponseModel<T>>(){}.getType();

        ResponseModel<T> myJson = gson.create().fromJson(json, type);
        return myJson;
    }

}
