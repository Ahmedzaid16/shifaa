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

public class brain extends AppCompatActivity {
    ImageView imgcart;
    Button button_cart ;
    int[] imageDrag = {R.drawable.buspirone,R.drawable.calmepam,R.drawable.diazepam, R.drawable.milga,R.drawable.motival,R.drawable.neuroton, R.drawable.pregavalex, R.drawable.tegral, R.drawable.thiotacid, R.drawable.uripan};
    String[] s1, s2;
    ArrayList<User> l_item = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_brain);
        button_cart = findViewById(R.id.btn_addTocart_br);
        button_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(brain.this,Cart.class);
                startActivity(intent);
            }
        });
        s1 = getResources().getStringArray(R.array.nameDrag_br);
        s2 = getResources().getStringArray(R.array.typeDrag_br);

        for (int i = 0; i < imageDrag.length; i++) {
            l_item.add(new User(s1[i], s2[i], imageDrag[i]));
        }

        customListView myAdapter = new customListView(l_item);
        ListView ls = (ListView) findViewById(R.id.list_viewbr);
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
                    Toast.makeText(brain.this,f, Toast.LENGTH_LONG).show();
                }
            });
            txtName.setText(Items.get(i).getTupeOfDrag());
            txtdesc.setText(Items.get(i).getSaleOfDrag());
            imag.setImageResource(Items.get(i).getImgDrag());
            return view1;
        }
    }
}