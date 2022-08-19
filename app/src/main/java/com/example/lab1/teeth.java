package com.example.lab1;

import static com.example.lab1.MainActivity.imageDrag_cart;
import static com.example.lab1.MainActivity.no;
import static com.example.lab1.MainActivity.s1_c;
import static com.example.lab1.MainActivity.s2_c;
import static com.example.lab1.MainActivity.s3_c;
import static com.example.lab1.MainActivity.top;
import static com.example.lab1.MainActivity.total;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class teeth extends AppCompatActivity {
    Button button_cart ;
    ImageView imgcart;
    Bitmap bitmap;
    ArrayList<data> l_item = new ArrayList<>();
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teeth);
        button_cart = findViewById(R.id.btn_addTocart_tee);
        ListView ls = (ListView) findViewById(R.id.list_view);
        customListView myAdapter = new customListView(l_item);
        ls.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        final boolean[] first = new boolean[1];
        if(myAdapter.isEmpty());
           first[0] =true;
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("data1");
        reference.addChildEventListener(new ChildEventListener() {
            ;
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                data d = snapshot.getValue(data.class);
                myAdapter.notifyDataSetChanged();
                if (d.getName() != null) {
                    if (!first[0]) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            first[0] =false;
                            Toast.makeText(getApplicationContext(), "دخل الشرط", Toast.LENGTH_SHORT).show();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    storageReference = FirebaseStorage.getInstance().getReference("images/teeth/" + d.getName());
                    try {
                        File localfile = File.createTempFile("tempfile", ".jpg");
                        storageReference.getFile(localfile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                        l_item.add(new data(d.getName(), d.getPrice(), bitmap));
                                        if (bitmap != null) {
                                            Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                                            myAdapter.notifyDataSetChanged();
                                        } else
                                            Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(), "undone", Toast.LENGTH_SHORT).show();

                                    }
                                });

                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "catch", Toast.LENGTH_SHORT).show();

                        e.printStackTrace();
                    }
                } else
                    Toast.makeText(getApplicationContext(), "fish waqt fish waqt fish waqt", Toast.LENGTH_SHORT).show();
                myAdapter.notifyDataSetChanged();

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
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

            }
        });
        button_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(teeth.this, Cart.class);
                startActivity(intent);

            }
        });




    }

    class customListView extends BaseAdapter {

        ArrayList<data> Items = new ArrayList<data>();

        customListView(ArrayList<data> Items) {
            this.Items = Items;
        }

        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public Object getItem(int i) {
            return Items.get(i).getName();
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
                    String a = String.valueOf(txtdesc.getText().toString());   //Price
                    String b = cha.getText().toString();                       //quantity
                    String r = txtName.getText().toString();                   //name
                    Float c = Float.parseFloat(a);
                    Float d = Float.parseFloat(b);
                    if(d!=0) {
                        Float e = c * d;
                        String f = String.valueOf(e).toString();
                        top++;
                        imageDrag_cart[no] = imag.getDrawable();
                        s1_c[no] = r;
                        s2_c[no] = a;
                        s3_c[no] = b;
                        total += e;
                        no++;
                        Toast.makeText(teeth.this,r+" Added to Cart", Toast.LENGTH_LONG).show();
                        cha.setText("0");
                           }
                    else
                        Toast.makeText(teeth.this,"add element first", Toast.LENGTH_LONG).show();
                }
            });
            txtName.setText(Items.get(i).getName());
            txtdesc.setText(Items.get(i).getPrice());
            imag.setImageBitmap(Items.get(i).getImage());
            return view1;
        }
    }
}