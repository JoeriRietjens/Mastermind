package nl.fhict.s3.mastermindlogic.entity;

import java.util.ArrayList;

public class Board {

    private final int id;
    public Colour[] code = new Colour[3];
    public Row[] rows = new Row[9];

    public int getId() {
        return id;
    }

    public Board(int id, Colour[] code) {
        this.id = id;
        this.code = code;
    }
     public Board(int id, Colour[] code){
        this.id = id;
        this.code = code;
     }

     public void guessCode(EPinColour ePinColour1,EPinColour ePinColour2, EPinColour ePinColour3, EPinColour ePinColour4){

     }

}
