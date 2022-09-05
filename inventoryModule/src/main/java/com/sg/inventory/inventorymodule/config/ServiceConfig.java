package com.sg.inventory.inventorymodule.config;

public class ServiceConfig {
    private String environment;
    private String token;

    public ServiceConfig(String environment, String token) {
        this.environment = environment;
        this.token = token;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
