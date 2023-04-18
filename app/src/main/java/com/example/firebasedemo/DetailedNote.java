package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailedNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_note);

        Intent intent = getIntent();
        String info = intent.getStringExtra("info");

        TextView textView = findViewById(R.id.textView);
        textView.setText(info);


    }

    public void homePressed(View view){
        finish();
    }
}