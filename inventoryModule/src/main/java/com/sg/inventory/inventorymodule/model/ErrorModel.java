package com.sg.inventory.inventorymodule.model;

public class ErrorModel {
    private int code;
    private String message;
    private String errorPrint;
    private String type;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorPrint() {
        return errorPrint;
    }

    public void setErrorPrint(String errorPrint) {
        this.errorPrint = errorPrint;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
