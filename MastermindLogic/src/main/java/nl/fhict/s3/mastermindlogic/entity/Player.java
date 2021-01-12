package nl.fhict.s3.mastermindlogic.entity;

public class Player {

    private final int id;
    private String name;
    private String password;
    private final Board board;

    public Player(int id) {
        this.id = id;
        board = new Board();
    }

    //This constructor is for test purposes.
    public Player(int id, String name, String password, Board board) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.board = board;
    }

    public final int getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }
}
