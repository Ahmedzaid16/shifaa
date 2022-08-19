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
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class heart_manager extends AppCompatActivity {

    ArrayList<data> temp = new ArrayList<>();
    EditText editTextname;
    EditText editTextprice;
    ImageView imageView;
    ListView lv;
    Button uplode;
    Uri selectedimg;
    StorageReference storageReference;
    ProgressDialog progressDialog;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_manager);
        editTextname = findViewById(R.id.heart_Drugname);
        editTextprice = findViewById(R.id.heart_drugprice);
        imageView = findViewById(R.id.heart_brws_img);
        uplode = findViewById(R.id.heart_add_m);
        lv = findViewById(R.id.heart_list_item_m);
        heart_manager.customListView myAdapter = new heart_manager.customListView(temp);
        lv.setAdapter(myAdapter);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("data3");
        reference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                data d = snapshot.getValue(data.class);
                myAdapter.notifyDataSetChanged();
                if (d.getName() != null) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    storageReference = FirebaseStorage.getInstance().getReference("images/heart/" + d.getName());
                    try {
                        File localfile = File.createTempFile("tempfile", ".jpg");
                        storageReference.getFile(localfile)
                                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                        bitmap = BitmapFactory.decodeFile(localfile.getAbsolutePath());
                                        temp.add(new data(d.getName(), d.getPrice(), bitmap));
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
                    data data3 = new data(editTextname.getText().toString(), editTextprice.getText().toString());
                    reference.push().setValue(data3).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "added sucssesfuly", Toast.LENGTH_SHORT).show();
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
            /// imag.setImageBitmap(Bitmap.createScaledBitmap(Items.get(i).getImage(), 100, 100, false));
            return view1;
        }

    }
    private void uplodeimage() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploding file");
        progressDialog.show();
        String filename = editTextname.getText().toString();//format.format(now);
        storageReference= FirebaseStorage.getInstance().getReference("images/heart/"+filename);
        storageReference.putFile(selectedimg)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getApplicationContext(),"done",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent aa) {
        super.onActivityResult(requestCode, resultCode, aa);
        if (resultCode == RESULT_OK && aa!= null)
        {
            selectedimg = aa.getData();
        }
    }
}