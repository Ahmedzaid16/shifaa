package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeManger extends AppCompatActivity {

    ImageView img_teethM ;
    ImageView img_brainM ;
    ImageView img_heartM ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_manger);
        img_teethM = findViewById(R.id.img_teethM);
        img_brainM = findViewById(R.id.img_brainM);
        img_heartM = findViewById(R.id.img_heartM);

        img_teethM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeManger.this , TeethManger.class);
                startActivity(intent);
            }
        });
        img_brainM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeManger.this , brain_manger.class);
                startActivity(intent);
            }
        });
        img_heartM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeManger.this , heart_manager.class);
                startActivity(intent);
            }
        });
    }
}