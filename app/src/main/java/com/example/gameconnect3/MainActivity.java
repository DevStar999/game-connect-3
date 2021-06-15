package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    enum CellValues {
        blank, red, yellow;
    }
    private Integer playerTurn;
    private Boolean gameOngoing;
    private ArrayList<ArrayList<Integer>> winningCombinations;
    private ArrayList<CellValues> cellStates;

    private void initialise() {
        playerTurn = 0;
        gameOngoing = false;

        winningCombinations = new ArrayList<ArrayList<Integer>>(){{
            new ArrayList<>(Arrays.asList(0, 1, 2));
            new ArrayList<>(Arrays.asList(3, 4, 5));
            new ArrayList<>(Arrays.asList(6, 7, 8));
            new ArrayList<>(Arrays.asList(0, 4, 8));
            new ArrayList<>(Arrays.asList(2, 4, 6));
            new ArrayList<>(Arrays.asList(0, 3, 6));
            new ArrayList<>(Arrays.asList(1, 4, 7));
            new ArrayList<>(Arrays.asList(2, 5, 8));
        }};

        cellStates = new ArrayList<>();
        for(Integer cellPosition=0; cellPosition<9; cellPosition++) {
            cellStates.add(CellValues.blank);
        }
    }

    public void makeMove(View view) {
        if (!gameOngoing) {
            return;
        }

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

        initialise();
    }
}