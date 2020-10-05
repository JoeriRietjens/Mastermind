package nl.fhict.s3.mastermindlogic.entity;

import java.util.ArrayList;

public class Board {

    private final int id;
    public ArrayList<Colour> code; //Make array of this list Perhaps 4
    public ArrayList<Row> rows; //Make array of this list Perhaps 10

    public int getId() {
        return id;
    }

    public Board(int id, ArrayList<Colour> code) {
        this.id = id;
        this.code = code;
    }
}
