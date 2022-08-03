package com.example.lab1;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Int2;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class homepage extends AppCompatActivity {


    ViewFlipper flipper;
    ImageView imgboone;
    ImageView imghert;
    ImageView imgbrain;
    ImageView imgteeth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        imgboone =(ImageView) findViewById(R.id.img_boone);
        imgbrain =(ImageView) findViewById(R.id.img_brain);
        imgteeth =(ImageView) findViewById(R.id.img_teeth);
        imghert =(ImageView) findViewById(R.id.img_hert);

        imgboone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homepage.this,boone.class);
                startActivity(intent);
            }
        });
        imghert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homepage.this,heart.class);
                startActivity(intent);
            }
        });
        imgbrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homepage.this,brain.class);
                startActivity(intent);
            }
        });
        imgteeth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(homepage.this,teeth.class);
                startActivity(intent);
            }
        });
        int imgarray[] = {R.drawable.one, R.drawable.two, R.drawable.three};
        flipper = (ViewFlipper) findViewById(R.id.flipper);


        for (int i = 0; i < imgarray.length; i++) {
            showimage(imgarray[i]);
        }


    }

    public void showimage(int img) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(img);

        flipper.addView(imageView);
        flipper.setFlipInterval(5000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(this, android.R.anim.slide_in_left);
        flipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }
}