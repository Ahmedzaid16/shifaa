package com.example.lab1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signUp extends AppCompatActivity {

    Button btn;
    EditText userName;
    EditText password;
    EditText repassword;
    EditText email;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn = findViewById(R.id.btnsignup);
        userName = findViewById(R.id.userName_SignUp);
        password = findViewById(R.id.password_SignUp);
        repassword = findViewById(R.id.repassword_SignUp);
        email = findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar = new ProgressDialog(com.example.lab1.signUp.this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkForm();
            }
        });
    }

    private void checkForm() {
        String username = userName.getText().toString();
        String mail = email.getText().toString();
        String pass = password.getText().toString();
        String rpass = repassword.getText().toString();

        if (username.isEmpty() || username.length() < 7) {
            Toast.makeText(com.example.lab1.signUp.this, "Your username is not valid!", Toast.LENGTH_SHORT).show();
        } else if (mail.isEmpty() || !mail.contains("@")) {
            Toast.makeText(com.example.lab1.signUp.this, "Email is not valid!", Toast.LENGTH_SHORT).show();
        } else if (pass.isEmpty() || pass.length() < 7) {
            Toast.makeText(com.example.lab1.signUp.this, "Password must be 7 charcter", Toast.LENGTH_SHORT).show();
        } else if (rpass.isEmpty() || !rpass.equals(pass)) {
            Toast.makeText(com.example.lab1.signUp.this, "Password is not matched", Toast.LENGTH_SHORT).show();
        } else {
            mLoadingBar.setTitle("Regiseration");
            mLoadingBar.setMessage("Please wait while chec your Form");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(mail, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(com.example.lab1.signUp.this, "Registeration Successfully", Toast.LENGTH_SHORT).show();

                        mLoadingBar.dismiss();
                        Intent intent = new Intent(com.example.lab1.signUp.this, homepage.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        Toast.makeText(com.example.lab1.signUp.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }
}