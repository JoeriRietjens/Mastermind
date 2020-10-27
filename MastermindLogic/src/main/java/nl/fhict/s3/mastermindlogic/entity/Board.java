package nl.fhict.s3.mastermindlogic.entity;

public class Board implements IBoard{

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

    public Board() {
        id = 0;
    }

    @Override
    public Row checkRow(Row rowToCheck) {
        return null;
    }
    public Row guessCode(EPinColour colour1, EPinColour colour2, EPinColour colour3, EPinColour colour4){
        return new Row(1);
    }
}
