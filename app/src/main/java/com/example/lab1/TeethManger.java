package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TeethManger extends AppCompatActivity {

    ImageView imgcart;
    ListView ls;
    EditText User;
    EditText pass;
    Button add;
    ArrayList<User> m = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teeth_manger);
        User = findViewById(R.id.newUserName);
        pass = findViewById(R.id.newPassword);
        add = findViewById(R.id.add_m);
        customListView myAdapter = new customListView(m);
        ls =  findViewById(R.id.list_item_m);
        ls.setAdapter(myAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.add(new User(User.getText().toString(), pass.getText().toString(), R.drawable.logo));
                myAdapter.notifyDataSetChanged();
                User.setText("");
                pass.setText("");
            }
        });



    }

    class customListView extends BaseAdapter {

        ArrayList<User> Items = new ArrayList<User>();

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
            View view1 = layoutInflater.inflate(R.layout.list_item_m, null);
            EditText txtName = view1.findViewById(R.id.nameDrag_m);
            EditText txtdesc =  view1.findViewById(R.id.salary_m);
            ImageView imag = (ImageView) view1.findViewById(R.id.imagpha_m);

            txtName.setText(Items.get(i).getTupeOfDrag());
            txtdesc.setText(Items.get(i).getSaleOfDrag());
            imag.setImageResource(Items.get(i).getImgDrag());
            return view1;
        }
    }
}