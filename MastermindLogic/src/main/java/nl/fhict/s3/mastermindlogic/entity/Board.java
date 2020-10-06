package nl.fhict.s3.mastermindlogic.entity;

import java.util.ArrayList;

public class Board {

    private final int id;
    public Colour[] code = new Colour[4];
    public Row[] rows = new Row[10];

    public int getId() {
        return id;
    }

    public Board(int id) {
        this.id = id;
    }
}
