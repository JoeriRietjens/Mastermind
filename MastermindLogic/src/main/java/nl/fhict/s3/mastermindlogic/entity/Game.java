package nl.fhict.s3.mastermindlogic.entity;

import java.util.UUID;

public class Game {
    private final UUID id;
    private Player player1 = null;
    private Player player2 = null;

    public UUID getId() {
        return id;
    }

    public Game() {
        this.id = UUID.randomUUID();
    }

    public Player getPlayer(int playerId) {
        if((player1 != null) && (playerId == player1.getId())){
            return player1;
        } else if ((player2 != null) && (playerId == player2.getId())) {
            return player2;
        }

        return null;
    }

    public void restartGame() {
        player1.setBoard(new Board());
        player2.setBoard(new Board());
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
