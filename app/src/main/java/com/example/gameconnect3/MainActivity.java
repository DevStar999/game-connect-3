package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    // Game Logic Related Variables
    enum CellValues {
        blank, red, yellow;
    }
    private Integer playerTurn;
    private Boolean gameOngoing;
    private Boolean isResetRequired;
    private ArrayList<ArrayList<Integer>> winningCombinations;
    private ArrayList<CellValues> cellStates;

    // Views
    private Button startGameButton;

    private void initialiseVariables() {
        playerTurn = 0;
        gameOngoing = false;
        isResetRequired = false;

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

    private void initialiseView() {
        startGameButton = findViewById(R.id.startGameButton);
    }

    public void makeMove(View view) {
        String viewTag = view.getTag().toString();
        Integer cellPosition = Integer.parseInt(viewTag);
        Log.i("Move Made :", "Cell: " + viewTag);

        // If game is not ongoing OR the current cell is not empty, the don't allow to make a move
        if (!gameOngoing || !cellStates.get(cellPosition).equals(CellValues.blank)) {
            return;
        }

        CellValues currentPlayer;
        if (playerTurn == 1) { // Chance for Red to play
            currentPlayer = CellValues.red;
        }
        else { // Chance for Yellow to play
            currentPlayer = CellValues.yellow;
        }
        Integer gamePieceResourceId = getResources().getIdentifier(currentPlayer.toString(),
                "drawable",
                this.getPackageName());

        // Make the move
        ((ImageView) view).setImageResource(gamePieceResourceId);
        view.setTranslationY(-1000);
        view.setAlpha(1);
        view.animate().translationYBy(1000).setDuration(200);
        cellStates.set(cellPosition, currentPlayer);

        // Change playerTurn
        playerTurn = (playerTurn == 1) ? 2 : 1;

        // Check game status
        if (checkWin()) { // One of the players won
            gameOngoing = false;
        }
        else if(checkDraw()) { // Game ended as a draw
            gameOngoing = false;
        }
    }

    private Boolean checkWin() {
        // Dummy value for now
        return false;
    }
    private Boolean checkDraw() {
        // Dummy value for now
        return false;
    }

    private void resetGame() {
        // Logic to reset Game
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseVariables();
        initialiseView();

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerTurn = 1;
                gameOngoing = true;
                startGameButton.setEnabled(false);
                startGameButton.setAlpha(0f);
                if (isResetRequired) {
                    resetGame();
                }
                Log.i("Start Button Clicked", "playerTurn " + playerTurn
                        + ", gameOngoing = " + gameOngoing);
            }
        });
    }
}