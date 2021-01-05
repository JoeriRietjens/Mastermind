package nl.fhict.s3.mastermindlogic.entity;

public class Player {

    private final int id;
    private String name;
    private String password;
    private final Board board;

    public Player(int id) {
        this.id = id;
        board = new Board();
        this.board.setCode(new EPinColour[]{EPinColour.WHITE, EPinColour.BLACK, EPinColour.RED, EPinColour.BLUE});
    }

    public final int getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }
}
