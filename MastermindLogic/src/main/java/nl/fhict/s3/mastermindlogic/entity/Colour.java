package nl.fhict.s3.mastermindlogic.entity;

public class Colour {

    private final int id;
    public final EPinColour colour;

    public int getId() {
        return id;
    }

    public Colour(int id, EPinColour colour) {
        this.id = id;
        this.colour = colour;
    }
}
