package com.uboizblog.multitouchtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout = findViewById(R.id.mainlayout);
        CanvasView myView = new CanvasView(this);
        relativeLayout.addView(myView);
    }
}