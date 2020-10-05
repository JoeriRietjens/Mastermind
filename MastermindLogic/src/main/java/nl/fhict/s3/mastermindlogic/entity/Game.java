package nl.fhict.s3.mastermindlogic.entity;

import java.util.ArrayList;

public class Game {

    private final int id;
    public ArrayList<Player> players;

    public ArrayList<Player> getPlayers() {
        return players;
    }
    public int getId() {
        return id;
    }

    public Game(int id) {
        this.id = id;
    }

}
