package com.example.lab1;

import static com.example.lab1.MainActivity.top;
import static com.example.lab1.MainActivity.total;
import static com.example.lab1.MainActivity.no;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class confirmation extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    EditText name;
    EditText address;
    EditText floorNum;
    EditText apartmentNum;
    EditText phoneNum;
    TextView totalOrder;
    TextView totalprice;
    Button confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        name = (EditText) findViewById(R.id.c_name);
        address = (EditText) findViewById(R.id.c_address);
        floorNum = (EditText) findViewById(R.id.floor);
        apartmentNum = (EditText) findViewById(R.id.apartment);
        phoneNum = (EditText) findViewById(R.id.phone_num);
        totalOrder = (TextView) findViewById(R.id.total_o_p);
        confirm = (Button) findViewById(R.id.con_btn);
        totalOrder.setText(String.valueOf(total));
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Order");
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Order order = new Order(name.getText().toString(), address.getText().toString(), floorNum.getText().toString(), apartmentNum.getText().toString(), phoneNum.getText().toString(), totalOrder.getText().toString());
                        mDatabaseRef.push().setValue(order);
                        name.setText("");
                        address.setText("");
                        floorNum.setText("");
                        apartmentNum.setText("");
                        phoneNum.setText("");
                        totalOrder.setText("");

                top=0;
                no = 0;
                total= 0.0F;
                Intent intent= new Intent(confirmation.this,homepage.class);
                startActivity(intent);
            }
        });
    }
}