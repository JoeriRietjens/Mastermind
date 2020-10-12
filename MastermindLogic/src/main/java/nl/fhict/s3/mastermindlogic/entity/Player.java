package nl.fhict.s3.mastermindlogic.entity;


public class Player {

    public final int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public Board board;

    private int id;
    private String name;
    private String password;

    public Player(int id, String name, String password, Board board) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.board = board;
    }


}
