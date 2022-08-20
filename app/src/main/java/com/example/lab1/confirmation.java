package com.example.lab1;

import static com.example.lab1.MainActivity.s1_c;
import static com.example.lab1.MainActivity.s2_c;
import static com.example.lab1.MainActivity.s3_c;
import static com.example.lab1.MainActivity.top;
import static com.example.lab1.MainActivity.total;
import static com.example.lab1.MainActivity.no;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.nearby.messages.Message;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;

public class confirmation extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private ArrayList<User> drug = new ArrayList<>();
    EditText name;
    EditText address;
    EditText floorNum;
    EditText apartmentNum;
    EditText phoneNum;
    TextView totalOrder;
    TextView totalprice;
    Button confirm;
    String topic = "buy";
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
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("order");
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<top;i++)
                {

                     drug.add(new User(s1_c[i],s2_c[i],s3_c[i]));
                }
                if (phoneNum.length() == 11) {
                    String key = mDatabaseRef.push().getKey();
                    Order order = new Order(name.getText().toString(), address.getText().toString(), floorNum.getText().toString(), apartmentNum.getText().toString(), phoneNum.getText().toString(), totalOrder.getText().toString(),key);
                    mDatabaseRef.child(key).child("customer info").setValue(order);
                    mDatabaseRef.child(key).child("drug").child("drug1").setValue(drug);
                    name.setText("");
                    address.setText("");
                    floorNum.setText("");
                    apartmentNum.setText("");
                    phoneNum.setText("");
                    totalOrder.setText("");
                    top = 0;
                    no = 0;
                    total = 0.0F;
                    s1_c[0]=null;
                    s2_c[0]=null;
                    s3_c[0]=null;
                    Intent intent = new Intent(confirmation.this, homepage.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Enter a valid Number", Toast.LENGTH_SHORT).show();
            }


        });
    }
}