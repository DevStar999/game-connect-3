package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Game Logic Related Variables
    private Integer playerTurn;
    private Boolean gameOngoing;
    private Boolean isResetRequired;
    private GameDecisionLogic referee;
    private ArrayList<CellValues> cellStates;

    // Views
    private Button startGameButton;

    private void initialiseVariables() {
        playerTurn = 0;
        gameOngoing = false;
        isResetRequired = false;
        referee = new GameDecisionLogic();

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
        if (playerTurn%2 == CellValues.red.ordinal()) { // Chance for Red to play
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
        playerTurn++;

        // Check game status
        Boolean gameOver = false;
        if (referee.checkWin(cellStates)) { // One of the players won
            Log.i("Info Game WIN", "Player " + currentPlayer.toString() + " has won game");
        }
        else if(referee.checkDraw(playerTurn)) { // Game ended as a draw
            Log.i("Info Game DRAW", "NO WIN, Board full filled");
        }

        if (gameOver) {
            gameOngoing = false; isResetRequired = true;
            startGameButton.setEnabled(true); startGameButton.setAlpha(1f);
        }
    }

    private void resetGame() {
        /*
        // Logic to reset Game
        1. Set isResetRequired to false;
        2. For referee, empty the ArrayList of finalWinningPositions
        3. For all elements of cellStates, change value to blank
        4. ImageView src image to blank.jpeg
        */
        isResetRequired = false;

        referee.clearWinningPositions();

        for (Integer i=0; i<9; i++) {
            cellStates.set(i, CellValues.blank);

            Integer cellResourceId =  getResources().getIdentifier("cellImageView" + i.toString(),
                    "drawable",
                    this.getPackageName());
            ImageView cell = findViewById(cellResourceId);
            Integer blankPieceResourceId = getResources().getIdentifier(CellValues.blank.toString(),
                    "drawable",
                    this.getPackageName());
            cell.setImageResource(blankPieceResourceId);
        }
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