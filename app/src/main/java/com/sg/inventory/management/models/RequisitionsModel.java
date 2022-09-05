package com.sg.inventory.management.models;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;

public class RequisitionsModel implements Serializable {
    private Object allocationReason;

    private String subCategory;

    private String allocatedOn;

    private String allocatedItems;

    private String posMeta;

    private String latitude;

    private String allocationId;

    private String subCategoryId;

    private String requestedItems;

    private String requisitionId;

    private String actorId;

    private Boolean isDeleted;

    private String statusId;

    private Object receivedItems;

    private String allocatedBy;

    private String deliveryDate;

    private String category;

    private String trackingNumber;

    private String categoryId;

    private String longitude;

    private String username;

    public Object getAllocationReason() {
        return this.allocationReason;
    }

    public void setAllocationReason(Object allocationReason) {
        this.allocationReason = allocationReason;
    }

    public String getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getAllocatedOn() {
        return this.allocatedOn;
    }

    public void setAllocatedOn(String allocatedOn) {
        this.allocatedOn = allocatedOn;
    }

    public String getAllocatedItems() {
        return this.allocatedItems;
    }

    public void setAllocatedItems(String allocatedItems) {
        this.allocatedItems = allocatedItems;
    }

    public String getPosMeta() {
        return this.posMeta;
    }

    public void setPosMeta(String posMeta) {
        this.posMeta = posMeta;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getAllocationId() {
        return this.allocationId;
    }

    public void setAllocationId(String allocationId) {
        this.allocationId = allocationId;
    }

    public String getSubCategoryId() {
        return this.subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getRequestedItems() {
        return this.requestedItems;
    }

    public void setRequestedItems(String requestedItems) {
        this.requestedItems = requestedItems;
    }

    public String getRequisitionId() {
        return this.requisitionId;
    }

    public void setRequisitionId(String requisitionId) {
        this.requisitionId = requisitionId;
    }

    public String getActorId() {
        return this.actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getStatusId() {
        return this.statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public Object getReceivedItems() {
        return this.receivedItems;
    }

    public void setReceivedItems(Object receivedItems) {
        this.receivedItems = receivedItems;
    }

    public String getAllocatedBy() {
        return this.allocatedBy;
    }

    public void setAllocatedBy(String allocatedBy) {
        this.allocatedBy = allocatedBy;
    }

    public String getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTrackingNumber() {
        return this.trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}