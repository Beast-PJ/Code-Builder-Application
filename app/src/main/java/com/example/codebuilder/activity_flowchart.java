package com.example.codebuilder;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class activity_flowchart extends Activity {
    LinearLayout flow;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowchart);
        flow = findViewById(R.id.flowchart_canvas);
        for (int i = 0; i < Program_details.flowsteps.size(); i++) {
            drawRect(Program_details.flowsteps.get(i));
            Toast.makeText(this, Program_details.flowsteps.get(i), Toast.LENGTH_SHORT).show();
        }

    }

    public void drawRect(String step){
        TextView textView1 = new TextView(this);
        textView1.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        textView1.setText(step);
        textView1.setBackgroundResource(R.drawable.rectangle);
        textView1.setGravity(Gravity.CENTER_HORIZONTAL);
        textView1.setTextColor(Color.BLACK);
        textView1.setTextSize(15);
        textView1.setPadding(0,35,0,35);
        flow.addView(textView1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}