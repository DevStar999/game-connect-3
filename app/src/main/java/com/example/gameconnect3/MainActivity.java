package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void makeMove(View view) {
        String viewTag = view.getTag().toString();
        Log.i("Slot Pressed :", "Slot: " + viewTag);
        ((ImageView) view).setImageResource(R.drawable.red);
        view.setY(-1*1500);
        view.animate().translationY(0).setDuration(200);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}