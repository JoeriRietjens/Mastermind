package nl.fhict.s3.mastermindlogic.entity;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class Row  {
    @Expose private final int id;
    @Expose private EPinColour[] guess = new EPinColour[4];
    @Expose private EClueColour[] clues = new EClueColour[4];

    public Row(int id) {
        this.id = id;
    }

    public Row() {
        this.id = 0;
    }

    public boolean isNotCompletelyFilled() {
        // See if the code is the correct length, otherwise a length of 3 will still be a 'full' code
        if (guess.length != 4) {
            return true;
        }

        // check if there are no nulls
        for(EPinColour pin : guess) {
            if(pin == null) {
                return true;
            }
        }
        return false;
    }
}
