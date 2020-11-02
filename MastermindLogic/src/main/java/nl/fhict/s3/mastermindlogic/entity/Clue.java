package nl.fhict.s3.mastermindlogic.entity;

public class Clue {

    private final int id;

    public nl.fhict.s3.mastermindlogic.entity.EClueColour geteClueColour() {
        return eClueColour;
    }

    protected void seteClueColour(nl.fhict.s3.mastermindlogic.entity.EClueColour eClueColour) {
        this.eClueColour = eClueColour;
    }

    private EClueColour eClueColour;

    public int getId() {
        return id;
    }

    public Clue(int id, EClueColour eClueColour) {
        this.id = id;
        seteClueColour(eClueColour);
    }
}
