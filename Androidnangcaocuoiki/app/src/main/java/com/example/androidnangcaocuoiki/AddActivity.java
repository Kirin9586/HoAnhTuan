package com.example.androidnangcaocuoiki;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {
    EditText description;
    EditText price;
    EditText producer;
    EditText id;
    EditText productname;
    Button btnadd;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        database = FirebaseDatabase.getInstance();
        onIt();
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }

    private void Add() {
        DatabaseReference myref = database.getReference("AdvancedAndroidFinalTest");
        Hanghoa model  = new Hanghoa();
        model.setId(Integer.parseInt(id.getText().toString()));
        model.setProducer(producer.getText().toString());
        model.setProduct_name(productname.getText().toString());
        model.setPrice(Integer.parseInt(price.getText().toString()));
        model.setDescription(description.getText().toString());
        DatabaseReference post =myref.push();
        post.setValue(model);
    }

    @SuppressLint("WrongViewCast")
    private void onIt() {
        description = findViewById(R.id.edt_description);
        price = findViewById(R.id.edt_price);
        producer = findViewById(R.id.edt_producer);
        id = findViewById(R.id.edt_id);
        productname = findViewById(R.id.edt_productname);
        btnadd = findViewById(R.id.btnAdd);
    }
}
