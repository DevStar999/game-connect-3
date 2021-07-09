package com.example.gameconnect3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PostGameDisplays {
    private AlertDialog.Builder gameResultDialogBuilder;
    private AlertDialog gameResultDialog;
    private Context context;

    public PostGameDisplays(Context context) {
        this.context = context;
    }

    public void displayWin(Context context, String winningPlayer, List<List<Integer>> finalWinningPositions) {
        // Creating the Dialog Box
        gameResultDialogBuilder = new AlertDialog.Builder(context);
        String capWinningPlayer = winningPlayer.substring(0,1).toUpperCase() + winningPlayer.substring(1);
        gameResultDialogBuilder.setTitle("Game Winner : " + capWinningPlayer)
                .setMessage("Congratulations to player " + capWinningPlayer + " for winning the game")
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
        gameResultDialog = gameResultDialogBuilder.create();
        Integer winningColorNumber;
        if (winningPlayer.equals(CellValues.red.toString())) { winningColorNumber = Color.RED; }
        else { winningColorNumber = Color.YELLOW; }
        gameResultDialog.getWindow().setBackgroundDrawable(new ColorDrawable(winningColorNumber));

        // Coloring all the winning positions with winning color
        for(List<Integer> currentWinningPosition: finalWinningPositions) {
            for(Integer cell: currentWinningPosition) {
                Integer cellResourceId = context.getResources().getIdentifier("cell_image_view_" + cell.toString(),
                        "id",
                        context.getPackageName());
                ImageView cellImageView = ((Activity) context).findViewById(cellResourceId);
                cellImageView.setBackgroundColor(winningColorNumber);
                cellImageView.getBackground().setAlpha(100);
            }
        }

        // Finally showing the dialog box
        gameResultDialog.show();
    }

    public void displayDraw() {
        // Creating the Dialog Box
        gameResultDialogBuilder = new AlertDialog.Builder(context);
        gameResultDialogBuilder.setTitle("Game Draw")
                .setMessage("The game resulted in a draw")
                .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                });
        gameResultDialog = gameResultDialogBuilder.create();

        // Finally showing the dialog box
        gameResultDialog.show();
    }
}
