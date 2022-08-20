package com.example.lab1;

public class User<Button> {

    private String tupeOfDrag;
    private String saleOfDrag;
    private String quantity;

    public User(String tupeOfDrag, String saleOfDrag,String quantity) {
        this.tupeOfDrag = tupeOfDrag;
        this.saleOfDrag = saleOfDrag;
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTupeOfDrag() {
        return tupeOfDrag;
    }

    public void setTupeOfDrag(String tupeOfDrag) {
        this.tupeOfDrag = tupeOfDrag;
    }

    public String getSaleOfDrag() {
        return saleOfDrag;
    }

    public void setSaleOfDrag(String saleOfDrag) {
        this.saleOfDrag = saleOfDrag;
    }

}
