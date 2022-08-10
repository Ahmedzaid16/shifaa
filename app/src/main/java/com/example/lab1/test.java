package com.example.lab1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class test extends AppCompatActivity {
    Button brw_img;
    Uri selectedimg;
    ImageView imgcart;
    ListView ls;
    EditText User;
    EditText pass;
    Button add;
    ArrayList<User2> m = new ArrayList<User2>();
    public test() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        User = findViewById(R.id.newUserNametest);
        pass = findViewById(R.id.newPasswordtest);
        add = findViewById(R.id.add_img_tset);
        customListView myAdapter = new customListView(m);
        ls =  findViewById(R.id.list_test);
        ls.setAdapter(myAdapter);
        brw_img = findViewById(R.id.brws_img_tset);
        brw_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m.add(new User2(User.getText().toString(), pass.getText().toString(),selectedimg));
                myAdapter.notifyDataSetChanged();
                User.setText("");
                pass.setText("");
            }
        });



    }

    class customListView extends BaseAdapter {

        ArrayList<User2> Items = new ArrayList<User2>();

        customListView(ArrayList<User2> Items) {
            this.Items = Items;
        }

        @Override
        public int getCount() {
            return Items.size();
        }

        @Override
        public Object getItem(int i) {
            return Items.get(i).getTupeOfDrag1();
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
            txtName.setText(Items.get(i).getTupeOfDrag1());
            txtdesc.setText(Items.get(i).getSaleOfDrag1());
            imag.setImageURI(Items.get(i).getImgDrag1());
            return view1;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data!= null)
        {
            selectedimg = data.getData();
        }
    }
}