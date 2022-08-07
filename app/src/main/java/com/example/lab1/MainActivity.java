package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button login;
    TextView signup;
    TextView username;
    TextView password;
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
                if (username.getText().toString() != name && password.getText().toString() != pas) {
                    Intent intent = new Intent(MainActivity.this, HomeManger.class);
                    startActivity(intent);
                    Toast.makeText(getApplication(),"succed",Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, homepage.class);
                    startActivity(intent);
                    Toast.makeText(getApplication(),"Failed",Toast.LENGTH_LONG).show();
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