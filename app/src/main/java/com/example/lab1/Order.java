package com.example.lab1;

public class Order {

    private String nameCustomer;
    private String addressCustomer;
    private String floorNumber;
    private String apartmentNum;
    private String phoneNumber;
    private String totalOrder;
    String key;
    String drugsquantity;
    public Order() {

    }

    public Order(String nameCustomer, String addressCustomer, String floorNumber, String apartmentNum, String phoneNumber, String totalOrder,String key,String drugsquantity) {
        this.nameCustomer = nameCustomer;
        this.addressCustomer = addressCustomer;
        this.floorNumber = floorNumber;
        this.apartmentNum = apartmentNum;
        this.phoneNumber = phoneNumber;
        this.totalOrder = totalOrder;
        this.key = key;
        this.drugsquantity = drugsquantity;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getApartmentNum() {
        return apartmentNum;
    }

    public void setApartmentNum(String apartmentNum) {
        this.apartmentNum = apartmentNum;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTotalOrder() {
        return totalOrder;
    }

    public String getDrugsquantity() {
        return drugsquantity;
    }

    public void setDrugsquantity(String drugsquantity) {
        this.drugsquantity = drugsquantity;
    }

    public void setTotalOrder(String totalOrder) {
        this.totalOrder = totalOrder;
    }
}

