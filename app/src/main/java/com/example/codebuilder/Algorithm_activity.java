package com.example.codebuilder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Algorithm_activity extends AppCompatActivity{
    public EditText context;
    ImageButton flow_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);
        flow_btn = findViewById(R.id.flow_btn);
        context = findViewById(R.id.content_text);
        context.setText(Program_details.algo_step);
        flow_btn.setOnClickListener(v -> startActivity(new Intent(Algorithm_activity.this, activity_flowchart.class)));
    }
}