package com.example.androidnangcaocuoiki;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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

public class LoginActivity extends AppCompatActivity {
    int RC_SIGN_IN;
    GoogleSignInClient mGoogleSignInClient;
    public  static  String userID;
    static  String userEmail;
    Map<String, String> mMap = new HashMap();
    static  String userName;
    List<Hanghoa> model = new ArrayList<>();
    Button btnLogout;
   static DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myref = FirebaseDatabase.getInstance().getReference("AdvancedAndroidFinalTest");
        initGoogle();
        btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sigout();
            }
        });
    }

    private void Sigout() {
        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    private void initGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.btn_sigin);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
    }
}

    private void handleSignInResult(Task<GoogleSignInAccount> task) {
        try {
            final GoogleSignInAccount account = task.getResult(ApiException.class);
            userID = account.getId();
             userEmail = account.getEmail();
             userName = account.getDisplayName();
            myref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Object data = dataSnapshot.getValue();
                    if (data == null ) {
                        mMap.put("google_id", userEmail);
                        mMap.put("firebase_url", "https://androidnangcaocuoiki.firebaseio.com/");
                        new Asynctask(LoginActivity.this, new iData() {
                            @Override
                            public void onGetDataSuccess(String s, JSONArray jsonArray) {

                            }
                        }, mMap).execute("http://vidoandroid.vidophp.tk/api/FireBase/PushData");
                    }
                    else {
                        for (DataSnapshot ds:dataSnapshot.getChildren()) {
                            Hanghoa hanghoa = ds.getValue(Hanghoa.class);
                            ds.getRef().child(userID).setValue(hanghoa);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            e.printStackTrace();
            Log.w("lỗi sign in", "signInResult:failed code=" + e.getStatusCode());
        }
    }
}
