package nl.fhict.s3.mastermindlogic.entity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")

public class Game {

    private final int id;
    private Player[] players = new Player[2];

    public Player[] getPlayers() {
        return players;
    }
    public int getId() {
        return id;
    }

    public Game() {
        this.id = 0;
    }
    public Game(int id) {
        this.id = id;
        Player player1 = new Player(0);
        Player player2 = new Player(1);
        players[0] = player1;
        players[1] = player2;
    }
}
