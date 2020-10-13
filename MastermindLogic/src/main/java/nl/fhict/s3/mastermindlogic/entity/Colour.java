package nl.fhict.s3.mastermindlogic.entity;

public class Colour {

    //private final int id;
    private final EPinColour colour;


    /*public int getId() {
        return id;
    }*/

    public Colour(EPinColour colour) {
        this.colour = colour;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }

        if(obj.getClass() != this.getClass()) {
            return false;
        }

        Colour toCheck = (Colour) obj;
        return (toCheck.getColour() == this.getColour());
    }

    public EPinColour getColour() {
        return colour;
    }
}
