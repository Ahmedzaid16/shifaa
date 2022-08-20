package com.example.lab1;

import static com.example.lab1.MainActivity.imageDrag_cart;
import static com.example.lab1.MainActivity.no;
import static com.example.lab1.MainActivity.s1_c;
import static com.example.lab1.MainActivity.s2_c;
import static com.example.lab1.MainActivity.s3_c;
import static com.example.lab1.MainActivity.top;
import static com.example.lab1.MainActivity.total;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
                if (s1_c[0]!=null) {
                    Intent intent = new Intent(Cart.this, confirmation.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"add drug first",Toast.LENGTH_SHORT).show();
            }
        });


        for ( int i = 0; i < top; i++) {

               l_item.add(new User3(s1_c[i], s2_c[i], imageDrag_cart[i], s3_c[i]));
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
                    String[] temp1=new String[50];
                    String[] temp2=new String[50];
                    String[] temp3=new String[50];
                    Drawable[] temp4 = new Drawable[50];
                    for(int j =0;j<top;j++)
                    {
                        boolean ch=false;
                        if(j==i || ch==true) {
                            temp1[j] = s1_c[j+1];
                            temp2[j] = s2_c[j+1];
                            temp3[j] = s3_c[j+1];
                            temp4[j] = imageDrag_cart[j+1];
                            ch=true;
                        }
                        else
                        {
                            temp1[j] = s1_c[j];
                            temp2[j] = s2_c[j];
                            temp3[j] = s3_c[j];
                            temp4[j] = imageDrag_cart[j];
                        }
                    }
                    top -- ;
                    for(int j =0;j<top;j++)
                    {
                            s1_c[j]=temp1[j];
                            s2_c[j]=temp2[j];
                            s3_c[j]=temp3[j];
                            imageDrag_cart[j]=temp4[j];
                    }
                    customListView myAdapter = new customListView(l_item);
                    ListView ls = (ListView) findViewById(R.id.listview_cart);
                    ls.setAdapter(myAdapter);
                    myAdapter.notifyDataSetChanged() ;
                    String a = String.valueOf(txtdesc.getText().toString());
                    String b = cha.getText().toString();
                    Float c = Float.parseFloat(a);
                    Float d = Float.parseFloat(b);
                    Float e = c * d;
                    total-=e;
                    price.setText(String.valueOf(total));
                    no--;
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