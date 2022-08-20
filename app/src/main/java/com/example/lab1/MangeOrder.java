package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;

public class MangeOrder extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private ListView list_Order;
    private ArrayList<Order> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mange_order);
        list_Order = findViewById(R.id.listOrder);
        customListView custom = new customListView(list);
        list_Order.setAdapter(custom);
        custom.notifyDataSetChanged();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("order");
        mDatabaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Order order = snapshot.child("customer info").getValue(Order.class);
                String key = order.getKey();
                list.add(new Order(order.getNameCustomer(),order.getAddressCustomer(),order.getFloorNumber(),order.getApartmentNum(),order.getPhoneNumber(),order.getTotalOrder(),key,order.getDrugsquantity()));
                custom.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    class customListView extends BaseAdapter {

        ArrayList<Order> Items = new ArrayList<>();

        customListView(ArrayList<Order> Items) {
            this.Items = Items;
        }

        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public Object getItem(int i) {
            return Items.get(i).getNameCustomer();
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.list_order, null);
            EditText nameCustomer = view1.findViewById(R.id.nameCustomer);
            EditText addressCustomer = view1.findViewById(R.id.addressCustomer);
            EditText floorCustomer = view1.findViewById(R.id.floorNumber);
            EditText ApartmentNumber = view1.findViewById(R.id.ApartmentNumber);
            EditText phoneNumber = view1.findViewById(R.id.phoneNumber);
            TextView TotalSalry = view1.findViewById(R.id.TotalSalry);
            Button seeOrder = view1.findViewById(R.id.seeOrder);
            nameCustomer.setText(Items.get(i).getNameCustomer());
            addressCustomer.setText(Items.get(i).getAddressCustomer());
            floorCustomer.setText(Items.get(i).getFloorNumber());
            ApartmentNumber.setText(Items.get(i).getApartmentNum());
            phoneNumber.setText(Items.get(i).getPhoneNumber());
            TotalSalry.setText(Items.get(i).getTotalOrder());

            seeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MangeOrder.this, tmpviewcart.class);
                    intent.putExtra("key",Items.get(i).getKey());
                    startActivity(intent);
                }
            });
            return view1;
        }
    }
}