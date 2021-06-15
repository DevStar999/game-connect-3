package com.example.gameconnect3;

import java.util.ArrayList;
import java.util.Arrays;

public class GameDecisionLogic {
    private ArrayList<ArrayList<Integer>> winningCombinations;

    public GameDecisionLogic() {
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
    }

    public Boolean checkWin(ArrayList<CellValues> cellStates) {
        Integer count = 0;
        for(ArrayList<Integer> currentWinningCombination : winningCombinations) {
            for(Integer i=0; i<3; i++) {
                if (i==0) { // Check if cell state is not blank
                    if (cellStates.get(currentWinningCombination.get(i)) == CellValues.red ||
                            cellStates.get(currentWinningCombination.get(i)) == CellValues.yellow) {
                        count++;
                    }
                }
                else { // Check if all three cells have same game piece
                    if (cellStates.get(currentWinningCombination.get(i)) ==
                            cellStates.get(currentWinningCombination.get(0))) {
                        count++;
                    }
                }
            }
        }

        return (count == 3);
    }

    public Boolean checkDraw(ArrayList<CellValues> cellStates) {
        for (CellValues currentCell: cellStates) {
            if (currentCell.equals(CellValues.blank)) {
                return false;
            }
        }
        return true;
    }
}
