package com.sg.inventory.management.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultModel implements Serializable {

    List<RequisitionsModel> receivedItems = new ArrayList<>();

    public List<RequisitionsModel> getRequisitions() {
        return receivedItems;
    }

    public void setReceivedItems(List<RequisitionsModel> receivedItems) {
        this.receivedItems = receivedItems;
    }
}
