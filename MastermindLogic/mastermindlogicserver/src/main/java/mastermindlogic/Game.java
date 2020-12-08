package mastermindlogic;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.UUID;


public class Game {

    private final UUID id;
    private Player[] players = new Player[2];

    public Player[] getPlayers() {
        return players;
    }
    public UUID getId() {
        return id;
    }

    public Game() {
        this.id = UUID.randomUUID();
        Player player1 = new Player(0);
        Player player2 = new Player(1);
        players[0] = player1;
        players[1] = player2;
    }

    public void setPlayer(Player player) {
        return;
    }
}
