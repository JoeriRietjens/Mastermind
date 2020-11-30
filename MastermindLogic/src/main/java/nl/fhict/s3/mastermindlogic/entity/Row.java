package nl.fhict.s3.mastermindlogic.entity;


public class Row  {

    private final int id;
    public EPinColour[] guess = new EPinColour[4];
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
        return guess;
    }

    public void setCode(EPinColour[] guess) {
        this.guess = guess;
    }

    public EClueColour[] getClues() {
        return clues;
    }

    public void setClues(EClueColour[] clues) {
        this.clues = clues;
    }
}
