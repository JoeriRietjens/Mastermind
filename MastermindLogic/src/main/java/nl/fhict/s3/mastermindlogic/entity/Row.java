package nl.fhict.s3.mastermindlogic.entity;

import java.util.ArrayList;

public class Row {

    private final int id;
    public Colour[] code = new Colour[4];
    public Clue[] clues = new Clue[4];

    public int getId() {
        return id;
    }

    public Row(int id) {
        this.id = id;
    }

}
