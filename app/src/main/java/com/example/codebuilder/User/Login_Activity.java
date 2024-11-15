package com.example.codebuilder.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.codebuilder.MainActivity;
import com.example.codebuilder.R;
import com.example.codebuilder.Utility;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login_Activity extends AppCompatActivity {

    EditText emailedittxt, passedittxt;
    Button login_btn;
    ProgressBar progressBar;
    TextView create_acc_btntxt, textView1, textView2;
    CardView cardView, cardView2;
    LinearLayout linearLayout;
    private FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailedittxt = findViewById(R.id.emailedittxt);
        passedittxt = findViewById(R.id.passedittxt);
        login_btn = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progressbar);
        create_acc_btntxt = findViewById(R.id.create_acc_btntxt);
        textView1 = findViewById(R.id.textview);
        cardView = findViewById(R.id.cardView);
        cardView2 = findViewById(R.id.cardView2);
        linearLayout = findViewById(R.id.toplinearLayout);


// ...
// Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


        login_btn.setOnClickListener(view -> login_user());
        create_acc_btntxt.setOnClickListener(view -> startActivity(new Intent(Login_Activity.this, Create_account_activity.class)));
        Animation drop_down = AnimationUtils.loadAnimation(this, R.anim.bottom_down);
        Animation fade_in = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        cardView.startAnimation(drop_down);
        cardView2.startAnimation(fade_in);
        textView1.startAnimation(drop_down);
        linearLayout.startAnimation(drop_down);
    }
        void login_user () {
            String email = emailedittxt.getText().toString();
            String pass = passedittxt.getText().toString();

            boolean isvalidated = validate_data(email);
            if (!isvalidated) {
                return;
            }
            login_acc_firebase(email, pass);
        }
        void change_in_progress (Boolean inprogress){
            if (inprogress) {
                progressBar.setVisibility(View.VISIBLE);
                login_btn.setVisibility(View.GONE);
            } else {
                progressBar.setVisibility(View.GONE);
                login_btn.setVisibility(View.VISIBLE);
            }
        }
        boolean validate_data (String email){
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailedittxt.setError("Invalid Email");
                return false;
            }
            if (passedittxt.length() < 6) {
                passedittxt.setError("Password lenght is Invalid");
                return false;
            }
            return true;
        }
        void login_acc_firebase (String email, String pass){
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            change_in_progress(true);
            firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    change_in_progress(false);
                    if (task.isSuccessful()) {
                            startActivity(new Intent(Login_Activity.this, MainActivity.class));
                    } else {
                        Utility.showToast(Login_Activity.this, Objects.requireNonNull(task.getException()).getLocalizedMessage());
                    }
                }
            });
        }

}