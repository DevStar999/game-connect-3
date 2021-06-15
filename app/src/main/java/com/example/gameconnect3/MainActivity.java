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
        //Log.i("Co-ordinates :", "x: " + location[0] + ", y: " + location[1]);
        ((ImageView) view).setImageResource(R.drawable.red);// Testing with red piece
        view.setTranslationY(-1000);
        view.setAlpha(1);
        view.animate().translationYBy(1000).setDuration(200);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}