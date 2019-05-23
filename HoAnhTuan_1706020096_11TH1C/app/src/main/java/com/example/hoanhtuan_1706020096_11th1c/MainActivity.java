package com.example.hoanhtuan_1706020096_11th1c;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<DsMon> dsMons = new ArrayList<>();
    Adapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Addcontent();
        init();
        adapter = new Adapter(this,R.layout.custom_listview,dsMons);
        listView.setAdapter(adapter);
    }

    private void Addcontent() {
        dsMons.add(new DsMon("Android Cơ Bản","2TH129","3.0"));
        dsMons.add(new DsMon("Anh Văn 2","2DC008","3.0"));
        dsMons.add(new DsMon("Android Cơ Bản","2TH129","3.0"));
    }

    private void init() {
        listView = findViewById(R.id.lv_main);
    }

}
