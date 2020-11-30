package nl.fhict.s3.mastermindlogic.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board implements IBoard {

    private final int id;

    public EPinColour[] code = new EPinColour[4];
    public final Row[] rows = new Row[10];

    public int getId() {
        return id;
    }

    public Board(int id, EPinColour[] code) {
        this.id = id;
        this.code = code;
    }

    public Board() {
        id = 0;
    }

    public Board(int id) {
        this.id = id;
    }

    public EPinColour[] createCode(EPinColour[] codeCreation) {
        code = codeCreation;
        return code;
    }

    @Override
    public Row checkRow(Row rowToCheck) {
        if(rowToCheck.isNotCompletelyFilled()){
            return null;
        }

        rowToCheck.setClues(getClues(rowToCheck.getGuess()));
        List<EClueColour> cluesList = Arrays.asList(rowToCheck.getClues());
        Collections.sort(cluesList);
        rowToCheck.setClues(cluesList.toArray(new EClueColour[0]));
        return rowToCheck;
    }

    public EClueColour[] getClues(EPinColour[] input) {
        EPinColour[] tempCode = code.clone();
        EPinColour [] tempGuess = input.clone();
        EClueColour[] clues = new EClueColour[4];
        getBlackPins(clues, tempCode, tempGuess);
        getWhitePins(clues, tempCode, tempGuess);
        sayClues(clues);
        return clues;

    }

    private void getBlackPins(EClueColour[] clues, EPinColour[] tempCode, EPinColour[] tempGuess) {
        for (int i = 0; i < tempCode.length; i++) {
            if (tempGuess[i] == tempCode[i]) {
                //black pin.
                clues[i] = EClueColour.BLACK;
                tempCode[i] = null;
                tempGuess[i] = null;
            }
            else {
                clues[i] = EClueColour.BLANK;
            }
        }
    }

    private void getWhitePins(EClueColour[] clues, EPinColour[] tempColour, EPinColour[] input) {

        for (int i = 0; i < tempColour.length; i++) {
            if (tempColour[i] == null) {
                continue;
            }
            for (int j = 0; j < input.length; j++) {
                if (input[j] == null) {
                    continue;
                }
                if (tempColour[i] == input[j]) {
                    clues[j] = EClueColour.WHITE;
                    tempColour[i] = null;
                    input[j] = null;
                }
            }
        }
    }

    private void sayClues(EClueColour[] clues) {
        for (int i = 0; i < clues.length; i++) {
            System.out.println("Colour: " + code[i]);
            System.out.println("Index: " + i + " Clue: " + clues[i]);
        }
    }

    public void setCode(EPinColour[] code) {
        this.code = code;
    }
}
