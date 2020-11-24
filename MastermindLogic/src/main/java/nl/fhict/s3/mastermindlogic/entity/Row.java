package nl.fhict.s3.mastermindlogic.entity;


public class Row  {

    private final int id;
    public static EPinColour[] code = new EPinColour[4];
    public static EClueColour[] clues = new EClueColour[4];

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
        Row.code = code;
    }

    public EClueColour[] getClues() {
        return clues;
    }

    public void setClues(EClueColour[] clues) {
        Row.clues = clues;
    }
}
