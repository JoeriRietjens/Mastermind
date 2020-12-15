package nl.fhict.s3.mastermindlogic.entity;

import java.util.*;
import java.util.stream.Collectors;

import javax.websocket.Session;

public class Game {
    private final UUID id;
    private Player player1;
    private Player player2;

    public UUID getId() {
        return id;
    }

    public Game() {
        this.id = UUID.randomUUID();
        player1 = new Player(0);
        player2 = new Player(1);
    }

    public Player getPlayer(int playerId) {
        if(playerId == player1.getId()){
            return player1;
        } else if (playerId == player2.getId()) {
            return player2;
        }
        return null;
    }

    public Player getOponnent(int playerId) {
        if(playerId == player1.getId()){
            return player2;
        } else if (playerId == player2.getId()) {
            return player1;
        }
        return null;
    }
}
