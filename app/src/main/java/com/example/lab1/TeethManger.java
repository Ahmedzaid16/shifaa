package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lab1.data;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class TeethManger extends AppCompatActivity {
    ArrayList<data> temp = new ArrayList<>();
    EditText editTextname;
    EditText editTextprice;
    ImageView imageView;
    ListView lv;
    Button uplode;
    Uri selectedimg;
    DatabaseReference reference;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    Bitmap bitmap;
    customListView myAdapter = new customListView(temp);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teeth_manger);
        editTextname = findViewById(R.id.Drugname);
        editTextprice = findViewById(R.id.drugprice);
        imageView = findViewById(R.id.brws_img);
        uplode = findViewById(R.id.add_m);
        lv = findViewById(R.id.list_item_m);
        lv.setAdapter(myAdapter);
         reference = FirebaseDatabase.getInstance().getReference("teeth");
        reference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                data d = snapshot.getValue(data.class);
                final boolean[] first = new boolean[1];
                if(myAdapter.isEmpty());
                first[0] =true;
                myAdapter.notifyDataSetChanged();
                if (d.getName() != null) {
                    if (!first[0]) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            first[0] =false;
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
                                        temp.add(new data(d.getName(), d.getPrice(), d.getKey(),bitmap));
                                        if (bitmap != null) {
                                           // Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                                            myAdapter.notifyDataSetChanged();
                                        } else
                                           Toast.makeText(getApplicationContext(), "empty", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                       // Toast.makeText(getApplicationContext(), "undone", Toast.LENGTH_SHORT).show();
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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });

        uplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedimg!= null)
                {
                    String key1 = reference.push().getKey();
                    data teeth = new data(editTextname.getText().toString(), editTextprice.getText().toString(),key1);
                    reference.child(key1).setValue(teeth).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "added sucssesfuly", Toast.LENGTH_SHORT).show();
                            editTextname.setText(null);
                            editTextprice.setText(null);
                            imageView.setImageResource(R.drawable.brawse);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "added Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                    myAdapter.notifyDataSetChanged();
                    uplodeimage();
                }
                else
                    Toast.makeText(getApplicationContext(), "pick image first", Toast.LENGTH_SHORT).show();


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
            View view1 = layoutInflater.inflate(R.layout.list_item_m, null);
            ImageView imag = (ImageView) view1.findViewById(R.id.imagpha_m);
            EditText name = view1.findViewById(R.id.nameDrag_m);
            EditText price = view1.findViewById(R.id.salary_m);
            name.setText(Items.get(i).getName());
            price.setText(Items.get(i).getPrice());
            imag.setImageBitmap(Items.get(i).getImage());
            ImageView delete = view1.findViewById(R.id.delete_m);
            delete.setClickable(true);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    reference.child(Items.get(i).getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            storageReference.child(Items.get(i).getName()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(), "deleted", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Items.remove(Items.get(i));
                            myAdapter.notifyDataSetChanged();

                        }
                    });
                }
            });
            return view1;
        }

    }
    private void uplodeimage() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploding file");
        progressDialog.show();
        String filename = editTextname.getText().toString();//format.format(now);
        storageReference= FirebaseStorage.getInstance().getReference("images/teeth/"+filename);
        storageReference.putFile(selectedimg)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                       // Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        //Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent aa) {
        super.onActivityResult(requestCode, resultCode, aa);
        if (resultCode == RESULT_OK && aa!= null)
        {
            selectedimg = aa.getData();
            imageView.setImageURI(selectedimg);
        }
    }

}