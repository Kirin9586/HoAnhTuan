    package com.example.androidnangcaocuoiki;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    static  List<Hanghoa>models = new ArrayList<>();
     static RecyclerViewAdapter adapter;
     static DatabaseReference myRef;
     Button sync;
     static FirebaseDatabase database;
    Button add;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sync = findViewById(R.id.btn_sync);
        recyclerView = findViewById(R.id.recyclerview);
        database = FirebaseDatabase.getInstance();
        add = findViewById(R.id.btnlogin);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
       myRef =database.getReference().child("AdvancedAndroidFinalTest");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                models.clear();
                if(models.isEmpty()){
                    for (DataSnapshot ds:dataSnapshot.getChildren()){
                        Hanghoa hanghoa = ds.getValue(Hanghoa.class);
                        String key = ds.getKey();
                        hanghoa.setKeyParent(key);
                        models.add(hanghoa);
                    }
                    adapter = new RecyclerViewAdapter(models,R.layout.item_layout,MainActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
            sync.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String,String> mMap = new HashMap<>();
                    mMap.put("google_id",LoginActivity.userEmail);
                    mMap.put("firebase_url", "https://androidnangcaocuoiki.firebaseio.com/");
                    new Asynctask(MainActivity.this, new iData() {
                        @Override
                        public void onGetDataSuccess(String s, JSONArray jsonArray) {

                        }
                    }, mMap).execute("http://vidoandroid.vidophp.tk/api/FireBase/PushData");
                }
            });
    }
}
