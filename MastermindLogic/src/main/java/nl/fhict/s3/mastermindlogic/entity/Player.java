package nl.fhict.s3.mastermindlogic.entity;

import javax.websocket.Session;

public class Player {

    private final int id;
    private String name;
    private String password;
    private final Board board;
    private Session session;

    public Player(int id) {
        this.id = id;
        board = new Board();
        this.board.setCode(new EPinColour[]{EPinColour.WHITE, EPinColour.BLACK, EPinColour.RED, EPinColour.BLUE});
    }

    public Player(int id, Session session) {
        this.session = session;
        this.id = id;
        board = new Board();
        this.board.setCode(new EPinColour[]{EPinColour.WHITE, EPinColour.BLACK, EPinColour.RED, EPinColour.BLUE});
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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Board getBoard() {
        return board;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
