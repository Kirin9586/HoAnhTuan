package com.example.hoanhtuan_1706020096_11th1c;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtuser;
    EditText edtpass;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Input();
        Onvalidate();
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Onvalidate()){
                    if(edtuser.getText().toString().equals("admin") && edtpass.getText().toString().equals("123")){
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this,"connect",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"Connect fail",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private boolean Onvalidate() {
        if(edtuser.getText().toString().length()<1){
            Toast.makeText(this,"User null",Toast.LENGTH_LONG).show();
            return  false;
        }
        if(edtpass.getText().toString().length()<1){
            Toast.makeText(this,"Pass null",Toast.LENGTH_LONG).show();
            return false;
        }
        return  true;
    }

    private void Input() {
        edtuser = findViewById(R.id.user);
        edtpass = findViewById(R.id.pass);
        btnlogin = findViewById(R.id.btn_Login);
    }
}
