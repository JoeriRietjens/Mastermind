package nl.fhict.s3.mastermindlogic.entity;

import java.io.Serializable;

public class Row  {

    private final int id;
    public EPinColour[] code = new EPinColour[4];
    public EClueColour[] clues = new EClueColour[4];

    public int getId() {
        return id;
    }

    public Row(int id) {
        this.id = id;
    }

    public Row() {
        this.id = 0;
    }


    public EPinColour[] getCode() {
        return code;
    }

    public void setCode(EPinColour[] code) {
        this.code = code;
    }

    public EClueColour[] getClues() {
        return clues;
    }

    public void setClues(EClueColour[] clues) {
        this.clues = clues;
    }

    public boolean isNotCompletelyFilled() {
        // See if the code is the correct length, otherwise a length of 3 will still be a 'full' code
        if (code.length != 4) {
            return true;
        }

        // check if there are no nulls
        for(EPinColour pin : code) {
            if(pin == null) {
                return true;
            }
        }
        return false;
    }
}
