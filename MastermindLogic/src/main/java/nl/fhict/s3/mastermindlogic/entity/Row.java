package nl.fhict.s3.mastermindlogic.entity;


public class Row  {

    private final int id;
    private EPinColour[] guess = new EPinColour[4];
    private EClueColour[] clues = new EClueColour[4];

    public int getId() {
        return id;
    }

    public Row(int id) {
        this.id = id;
    }

    public Row() {
        this.id = 0;
    }


    public EPinColour[] getGuess() {
        return guess;
    }

    public void setGuess(EPinColour[] guess) {
        this.guess = guess;
    }

    public EClueColour[] getClues() {
        return clues;
    }

    public void setClues(EClueColour[] clues) {
        this.clues = clues;
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
