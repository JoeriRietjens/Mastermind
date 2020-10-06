package nl.fhict.s3.mastermindlogic.entity;

public class Clue {

    private final int id;
    public final EClueColour eClueColour;

    public int getId() {
        return id;
    }

    public Clue(int id, EClueColour eClueColour) {
        this.id = id;
        this.eClueColour = eClueColour;
    }
}
