package com.example.lab1;

public class Order {

    private String nameCustomer;
    private String addressCustomer;
    private String floorNumber;
    private String apartmentNum;
    private String phoneNumber;
    private String totalOrder;

    public Order() {

    }

    public Order(String nameCustomer, String addressCustomer, String floorNumber, String apartmentNum, String phoneNumber, String totalOrder) {
        this.nameCustomer = nameCustomer;
        this.addressCustomer = addressCustomer;
        this.floorNumber = floorNumber;
        this.apartmentNum = apartmentNum;
        this.phoneNumber = phoneNumber;
        this.totalOrder = totalOrder;
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

    public void setTotalOrder(String totalOrder) {
        this.totalOrder = totalOrder;
    }
}
