package nl.fhict.s3.mastermindlogic.entity;

import java.util.ArrayList;

public class Board {

    private final int id;
    public ArrayList<Colour> code;

    public int getId() {
        return id;
    }

    public Board(int id, ArrayList<Colour> code) {
        this.id = id;
        this.code = code;
    }
}
