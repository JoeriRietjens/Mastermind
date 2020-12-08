package nl.fhict.s3.mastermindlogic.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.websocket.Session;

public class Game {
    private final String id;
    private Map<String, Player> players = new HashMap<>();

    public String getId() {
        return id;
    }

    public Game() {
        this.id = "0";
        Player player1 = new Player(0);
        Player player2 = new Player(1);
        players.put("0", player1);
        players.put("1", player2);
    }

    public Game(String id) {
        this.id = id;
        Player player1 = new Player(0);
        Player player2 = new Player(1);
        players.put("0", player1);
        players.put("1", player2);
    }

    public void setPlayerOne(Session session) {
        players.remove("0");
        players.put(session.getId(), new Player(Integer.parseInt(session.getId())));
        players.get(session.getId()).setSession(session);
    }

    public void setPlayerTwo(Session session) {
        players.remove("1");
        players.put(session.getId(), new Player(Integer.parseInt(session.getId())));
        players.get(session.getId()).setSession(session);
    }

    public Player getPlayer(String playerId) {
        return players.get(playerId);
    }

    public List<Session> getSessions() {
        ArrayList<Session> sessions = new ArrayList<>();

        for(Player p : players.values()) {
            sessions.add(p.getSession());
        }

        return sessions;
    }
}
