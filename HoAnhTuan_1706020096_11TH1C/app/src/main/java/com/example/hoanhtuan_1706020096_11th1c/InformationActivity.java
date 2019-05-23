package com.example.hoanhtuan_1706020096_11th1c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InformationActivity extends AppCompatActivity {
    TextView txt_mon;
    TextView txt_ma;
    Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        onIt();
        Intent intent = getIntent();
        txt_mon.setText(intent.getStringExtra("ten"));
        txt_ma.setText(intent.getStringExtra("ma"));
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onIt() {
        txt_mon = findViewById(R.id.txt_mon);
        txt_ma = findViewById(R.id.txt_ma);
        btnback = findViewById(R.id.btn_back);
    }
}
