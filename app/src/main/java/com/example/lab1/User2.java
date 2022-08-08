package com.example.lab1;

import android.net.Uri;

public class User2<Button> {

    private String tupeOfDrag;
    private String saleOfDrag;
    private Uri imgDrag;

    public User2(String tupeOfDrag, String saleOfDrag, Uri imgDrag) {
        this.tupeOfDrag = tupeOfDrag;
        this.saleOfDrag = saleOfDrag;
        this.imgDrag = imgDrag;
    }



    public String getTupeOfDrag1() {
        return tupeOfDrag;
    }

    public void setTupeOfDrag1(String tupeOfDrag) {
        this.tupeOfDrag = tupeOfDrag;
    }

    public String getSaleOfDrag1() {
        return saleOfDrag;
    }

    public void setSaleOfDrag1(String saleOfDrag) {
        this.saleOfDrag = saleOfDrag;
    }

    public Uri getImgDrag1() {
        return imgDrag;
    }

    public void setImgDrag1(Uri imgDrag) {
        this.imgDrag = imgDrag;
    }

}
