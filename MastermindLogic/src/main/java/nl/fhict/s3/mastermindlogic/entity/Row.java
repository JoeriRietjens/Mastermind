package nl.fhict.s3.mastermindlogic.entity;

import java.util.ArrayList;

public class Row {

    private final int id;
    public ArrayList<Colour> colours;
    public ArrayList<Clue> clues;
    public int getId() {
        return id;
    }

    public Row(int id) {
        this.id = id;
    }
}
