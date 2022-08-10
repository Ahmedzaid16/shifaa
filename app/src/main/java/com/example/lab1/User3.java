package com.example.lab1;

import android.graphics.drawable.Drawable;

public class User3<Button> {

    private String tupeOfDrag;
    private String saleOfDrag;
    private Drawable imgDrag;

    public User3(String tupeOfDrag, String saleOfDrag, Drawable imgDrag) {
        this.tupeOfDrag = tupeOfDrag;
        this.saleOfDrag = saleOfDrag;
        this.imgDrag = imgDrag;
    }


    public String getTupeOfDrag2() {
        return tupeOfDrag;
    }

    public void setTupeOfDrag2(String tupeOfDrag) {
        this.tupeOfDrag = tupeOfDrag;
    }

    public String getSaleOfDrag2() {
        return saleOfDrag;
    }

    public void setSaleOfDrag2(String saleOfDrag) {
        this.saleOfDrag = saleOfDrag;
    }

    public Drawable getImgDrag2() {
        return imgDrag;
    }

    public void setImgDrag2(Drawable imgDrag) {
        this.imgDrag = imgDrag;
    }

}
