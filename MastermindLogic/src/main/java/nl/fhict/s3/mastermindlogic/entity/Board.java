package nl.fhict.s3.mastermindlogic.entity;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int id;
    public Colour[] code = new Colour[4];
    public Row[] rows = new Row[10];

    public int getId() {
        return id;
    }

    public Board(int id, Colour[] code) {
        this.id = id;
        this.code = code;
    }

     public List<Clue> guessCode(EPinColour ePinColour1, EPinColour ePinColour2, EPinColour ePinColour3, EPinColour ePinColour4){
        List <Clue> clues = new ArrayList<>();
        //Logic that checks if the code is correct.
        return clues;
     }
}
