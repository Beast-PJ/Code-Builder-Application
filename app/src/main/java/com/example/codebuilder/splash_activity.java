package com.example.codebuilder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.codebuilder.User.Login_Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class splash_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
            if(currentUser==null){
                startActivity(new Intent(splash_activity.this, Login_Activity.class));
            }
            else{
                startActivity(new Intent(splash_activity.this,MainActivity.class));
            }
            finish();
        }, 1000);
    }
}