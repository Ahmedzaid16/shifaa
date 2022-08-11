package com.example.lab1;

import static com.example.lab1.MainActivity.top;
import static com.example.lab1.MainActivity.total;
import static com.example.lab1.MainActivity.no;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class confirmation extends AppCompatActivity {
TextView totalprice;
Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        totalprice = findViewById(R.id.total_o_p);
        totalprice.setText(String.valueOf(total));
        confirm = findViewById(R.id.con_btn);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                top=0;
                no = 0;
                total= 0.0F;
                Intent intent= new Intent(confirmation.this,homepage.class);
                startActivity(intent);
            }
        });
    }
}