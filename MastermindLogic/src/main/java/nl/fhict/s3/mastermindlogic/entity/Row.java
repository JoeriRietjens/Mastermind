package nl.fhict.s3.mastermindlogic.entity;

import java.util.ArrayList;

public class Row {

    private final int id;
    public ArrayList<Colour> colours; //Make array of this list Perhaps 4
    public ArrayList<Clue> clues; //Make array of this list Perhaps 4
    public int getId() {
        return id;
    }

    public Row(int id) {
        this.id = id;
    }
}
