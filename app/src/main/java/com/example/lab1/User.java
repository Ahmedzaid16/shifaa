package com.example.lab1;

import android.view.View;

public class User<Button> {

    private String tupeOfDrag;
    private String saleOfDrag;
    private Integer imgDrag;

    public User(String tupeOfDrag, String saleOfDrag, Integer imgDrag) {
        this.tupeOfDrag = tupeOfDrag;
        this.saleOfDrag = saleOfDrag;
        this.imgDrag = imgDrag;
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

    public Integer getImgDrag() {
        return imgDrag;
    }

    public void setImgDrag(Integer imgDrag) {
        this.imgDrag = imgDrag;
    }

}
