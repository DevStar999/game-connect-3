package com.example.gameconnect3;

import java.util.ArrayList;
import java.util.Arrays;

public class GameDecisionLogic {
    private ArrayList<ArrayList<Integer>> winningCombinations;
    private ArrayList<ArrayList<Integer>> finalWinningPosition;

    public GameDecisionLogic() {
        winningCombinations = new ArrayList<ArrayList<Integer>>(){{
            add(new ArrayList<>(Arrays.asList(0, 1, 2)));
            add(new ArrayList<>(Arrays.asList(3, 4, 5)));
            add(new ArrayList<>(Arrays.asList(6, 7, 8)));
            add(new ArrayList<>(Arrays.asList(0, 4, 8)));
            add(new ArrayList<>(Arrays.asList(2, 4, 6)));
            add(new ArrayList<>(Arrays.asList(0, 3, 6)));
            add(new ArrayList<>(Arrays.asList(1, 4, 7)));
            add(new ArrayList<>(Arrays.asList(2, 5, 8)));
        }};
        finalWinningPosition = new ArrayList<>();
    }

    public Boolean checkWin(ArrayList<CellValues> cellStates) {
        for(Integer cwc=0; cwc<winningCombinations.size(); cwc++) {
            Integer count = 0;
            for(Integer i=0; i<3; i++) {
                if (i==0) { // Check if cell state is not blank
                    if (cellStates.get(winningCombinations.get(cwc).get(i)) == CellValues.red ||
                            cellStates.get(winningCombinations.get(cwc).get(i)) == CellValues.yellow) {
                        count++;
                    }
                }
                else { // Check if all three cells have same game piece
                    if (cellStates.get(winningCombinations.get(cwc).get(i)) ==
                            cellStates.get(winningCombinations.get(cwc).get(0))) {
                        count++;
                    }
                }
            }
            if (count == 3) {
                finalWinningPosition.add(winningCombinations.get(cwc));
            }
        }

        return (finalWinningPosition.size()>0);
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
