package nl.fhict.s3.mastermindlogic.entity;

public class Colour {

    private final int id;
    public final Colour colour;

    public int getId() {
        return id;
    }

    public Colour(int id, Colour colour) {
        this.id = id;
        this.colour = colour;
    }
}
