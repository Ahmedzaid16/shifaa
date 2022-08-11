package com.example.lab1;

import static com.example.lab1.MainActivity.imageDrag_cart;
import static com.example.lab1.MainActivity.no;
import static com.example.lab1.MainActivity.s1_c;
import static com.example.lab1.MainActivity.s2_c;
import static com.example.lab1.MainActivity.s3_c;
import static com.example.lab1.MainActivity.top;
import static com.example.lab1.MainActivity.total;

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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class Cart extends AppCompatActivity {
    Button buttonview_cart ;
    ImageView imgcart , imgbasket;
    TextView price;

    /*Drawable[] imageDrag = new Drawable[top];
    String[] s1=new String[top];
    String[] s2=new String[top];*/
    ArrayList<User3> l_item = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        price = findViewById(R.id.tv_total_o);
        price.setText(String.valueOf(total));
        buttonview_cart = findViewById(R.id.btn_con_cart);
        buttonview_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Cart.this, confirmation.class);
                startActivity(intent);
            }
        });
        /*for(int i=0 ; i<= imageDrag_cart.length;i++) {
            imageDrag[i]=imageDrag_cart[i];
            s1[i]=s1_c[i];
            s2[i]=s2_c[i];
        }*/
        for ( int i = 0; i < top; i++) {
            l_item.add(new User3(s1_c[i], s2_c[i],imageDrag_cart[i],s3_c[i]));
        }

        customListView myAdapter = new customListView(l_item);
        ListView ls = (ListView) findViewById(R.id.listview_cart);
        ls.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
    }

    class customListView extends BaseAdapter {

        ArrayList<User3> Items = new ArrayList<>();

        customListView(ArrayList<User3> Items) {
            this.Items = Items;
        }

        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public Object getItem(int i) {
            return Items.get(i).getTupeOfDrag2();
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
            imgcart= (ImageView) view1.findViewById(R.id.img_cart);
            imgbasket =(ImageView) view1.findViewById(R.id.img_cart);
            imgbasket.setImageResource(R.drawable.basket);
            btnPlu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String present_value_string = cha.getText().toString();
                    int present_value_int = Integer.parseInt(present_value_string);
                    present_value_int++;
                    cha.setText(String.valueOf(present_value_int));
                    total+=Float.parseFloat(txtdesc.getText().toString());
                    price.setText(String.valueOf(total));
                }
            });

            btnmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String present_value_string = cha.getText().toString();
                    int present_value_int = Integer.parseInt(present_value_string);
                    if (present_value_int > 0) {
                        present_value_int--;
                        total-=Float.parseFloat(txtdesc.getText().toString());
                        price.setText(String.valueOf(total));
                    }
                    cha.setText(String.valueOf(present_value_int));
                }
            });
            imgbasket.setClickable(true);
            imgbasket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    l_item.remove(Items.get(i));
                    top -- ;
                    customListView myAdapter = new customListView(l_item);
                    ListView ls = (ListView) findViewById(R.id.listview_cart);
                    ls.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged() ;

                   // String a = String.valueOf(txtdesc.getText().toString());
                    //String b = cha.getText().toString();
                    //Float c = Float.parseFloat("");
                    //Float d = Float.parseFloat("");
                    //Float e = c * d;
                    //String f = String.valueOf(e).toString();

                }
            });
            txtName.setText(Items.get(i).getTupeOfDrag2());
            txtdesc.setText(Items.get(i).getSaleOfDrag2());
            imag.setImageDrawable(Items.get(i).getImgDrag2());
            cha.setText(Items.get(i).getNumberOfdraugs2());
            return view1;
        }
    }
}