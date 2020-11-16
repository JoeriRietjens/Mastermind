package nl.fhict.s3.mastermindlogic.entity;

import java.io.Serializable;

public class Row  {

    private final int id;
    public EPinColour[] code = new EPinColour[4];
    public Clue[] clues = new Clue[4];

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

    public Clue[] getClues() {
        return clues;
    }

    public void setClues(Clue[] clues) {
        this.clues = clues;
    }
}
