package com.example.androidnangcaocuoiki;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {
    EditText des;
    EditText id;
    EditText pri;
    EditText productor;
    Hanghoa hanghoa;
    EditText produ;
    Button btnedit;
    Button btnsave;
    Button btncancel;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        onIt();
        Disable();
        final Intent intent = getIntent();
        hanghoa = (Hanghoa) intent.getSerializableExtra("data");
        des.setText(hanghoa.getDescription());
        id.setText(String.valueOf(hanghoa.getId()));
        productor.setText(hanghoa.getProducer());
        produ.setText(hanghoa.getProduct_name());
        pri.setText(String.valueOf(hanghoa.getPrice()));
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enable();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disable();
                hanghoa.setDescription(des.getText().toString());
                hanghoa.setId(Integer.parseInt(id.getText().toString()));
                hanghoa.setPrice(Integer.parseInt(pri.getText().toString()));
                hanghoa.setProducer(productor.getText().toString());
                hanghoa.setProduct_name(produ.getText().toString());
                DatabaseReference chil = MainActivity.myRef.child(hanghoa.getKeyParent());
                chil.setValue(hanghoa);
                finish();
            }
        });
        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onIt() {
        des = findViewById(R.id.edt_description2);
        id = findViewById(R.id.edt_id2);
        pri = findViewById(R.id.edt_price2);
        productor = findViewById(R.id.edt_producer2);
        produ = findViewById(R.id.edt_productname2);
        btnedit = findViewById(R.id.btn_edit);
        btnsave = findViewById(R.id.btn_save);
        btncancel = findViewById(R.id.btn_cancel);
    }
    private void Enable(){
        des.setEnabled(true);
        id.setEnabled(true);
        pri.setEnabled(true);
        productor.setEnabled(true);
        produ.setEnabled(true);
    }
    private void Disable(){
        des.setEnabled(false);
        id.setEnabled(false);
        pri.setEnabled(false);
        productor.setEnabled(false);
        produ.setEnabled(false);
    }
}
