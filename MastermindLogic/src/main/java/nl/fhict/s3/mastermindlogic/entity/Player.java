package nl.fhict.s3.mastermindlogic.entity;

public class Player {

    private final int id;
    private String name;
    private String password;
    private Board board;

    public Player(int id) {
        this.id = id;
        board = new Board();
    }

    public final int getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
