package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class view_orders extends AppCompatActivity {
    private DatabaseReference mDatabaseRef;
    private ListView list_Order;
    private ArrayList<User> list = new ArrayList<>();
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            key =(String) b.get("key");
        }
        list_Order = findViewById(R.id.list_orderf);
        customListView custom = new customListView(list);
        list_Order.setAdapter(custom);
        custom.notifyDataSetChanged();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("order");
        mDatabaseRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    User order = snapshot.child(key).child("drug").getValue(User.class);
                    list.add(new User(order.getTupeOfDrag(), order.getSaleOfDrag(), order.getQuantity()));
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

        ArrayList<User> Items = new ArrayList<>();

        customListView(ArrayList<User> Items) {
            this.Items = Items;
        }

        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public Object getItem(int i) {
            return Items.get(i).getTupeOfDrag();
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view1 = layoutInflater.inflate(R.layout.list_order_drug, null);
            EditText drugname = view1.findViewById(R.id.etxt_drugname);
            EditText drugprice = view1.findViewById(R.id.etxt_drugprice);
            EditText quantity= view1.findViewById(R.id.etxt_quantity);
            drugname.setText(Items.get(i).getTupeOfDrag());
            drugprice.setText(Items.get(i).getSaleOfDrag());
            quantity.setText(Items.get(i).getQuantity());
            return view1;
        }
    }
}