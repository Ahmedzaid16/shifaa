package com.example.lab1;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static Drawable[] imageDrag_cart= new Drawable[6];
    public static int no = 0;
    public static int top =0;
    public static float total =0;
    public static String[] s1_c = new String[50];
    public static String[] s2_c = new String[50];
    public static String[] s3_c = new String[50];
    Button login;
    TextView signup;
    EditText username;
    EditText password;
    String name = "Ahmed";
    String pas = "111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.btnLongin);
        signup = findViewById(R.id.infoTxtCredits);
        username = findViewById(R.id.edtUsername);
        password = findViewById(R.id.edtPassword);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(), "sing in sucssfull", Toast.LENGTH_LONG).show();
                if (username.getText().toString().equals(name)&& password.getText().toString().equals(pas)) {
                    Intent intent = new Intent(MainActivity.this, HomeManger.class);
                    startActivity(intent);
                    Toast.makeText(getApplication(),"done",Toast.LENGTH_LONG).show();
                    username.setText("");
                    password.setText("");
                } else {
                    Intent intent = new Intent(MainActivity.this, homepage.class);
                    startActivity(intent);
                    Toast.makeText(getApplication(),"Faild 😢",Toast.LENGTH_LONG).show();
                    username.setText("");
                    password.setText("");
                }

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signUp.class);
                startActivity(intent);
            }
        });

    }


}