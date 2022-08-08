package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class heart extends AppCompatActivity {

    Button button_cart ;
    ImageView imgcart;
    int[] imageDrag = {R.drawable.medicine1,R.drawable.medicine2,R.drawable.medicine3, R.drawable.medicine4,R.drawable.medicine5,R.drawable.medicine6, R.drawable.medicine7, R.drawable.medicine8, R.drawable.medicine9, R.drawable.medicine10};
    String[] s1, s2;
    ArrayList<User> l_item = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_heart);
        button_cart = findViewById(R.id.btn_addTocart_her );
        button_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(heart.this,Cart.class);
                startActivity(intent);
            }
        });
        s1 = getResources().getStringArray(R.array.nameDrag_her);
        s2 = getResources().getStringArray(R.array.typeDrag_her);

        for (int i = 0; i < imageDrag.length; i++) {
            l_item.add(new User(s1[i], s2[i], imageDrag[i]));
        }

        customListView myAdapter = new customListView(l_item);
        ListView ls = (ListView) findViewById(R.id.list_view_her);
        ls.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

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
            View view1 = layoutInflater.inflate(R.layout.list_item, null);
            TextView txtName = view1.findViewById(R.id.nameDrag);
            TextView txtdesc = (TextView) view1.findViewById(R.id.SalryOfDrag);
            TextView cha = (TextView) view1.findViewById(R.id.numOfDrag);
            ImageView imag = (ImageView) view1.findViewById(R.id.imagpha);
            Button btnPlu = (Button) view1.findViewById(R.id.pluseAction);
            Button btnmin = (Button) view1.findViewById(R.id.minesAction);
            imgcart= (ImageView)view1.findViewById(R.id.img_cart);
            btnPlu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String present_value_string = cha.getText().toString();
                    int present_value_int = Integer.parseInt(present_value_string);
                    present_value_int++;
                    cha.setText(String.valueOf(present_value_int));
                }
            });

            btnmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String present_value_string = cha.getText().toString();
                    int present_value_int = Integer.parseInt(present_value_string);
                    if (present_value_int > 0) {
                        present_value_int--;
                    }
                    cha.setText(String.valueOf(present_value_int));
                }
            });
            imgcart.setClickable(true);
            imgcart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String a = String.valueOf(txtdesc.getText().toString());
                    String b = cha.getText().toString();
                    Float c = Float.parseFloat(a);
                    Float d = Float.parseFloat(b);
                    Float e = c * d;
                    String f = String.valueOf(e).toString();
                    Toast.makeText(heart.this,f, Toast.LENGTH_LONG).show();
                }
            });
            txtName.setText(Items.get(i).getTupeOfDrag());
            txtdesc.setText(Items.get(i).getSaleOfDrag());
            imag.setImageResource(Items.get(i).getImgDrag());
            return view1;
        }
    }
}