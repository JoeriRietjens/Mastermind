package nl.fhict.s3.mastermindlogic.entity;


public interface IBoard {

    /*
    This functions checks the given colours with the set code and returns the same row with clues
    */
    Row checkRow(Row rowToCheck);
}
