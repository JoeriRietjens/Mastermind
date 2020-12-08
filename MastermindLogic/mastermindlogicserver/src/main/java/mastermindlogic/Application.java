package mastermindlogic;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Application {

    private static Application INSTANCE;
    private List<Game> games = new ArrayList<>();

    public static Application getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Application();
        }
        return INSTANCE;
    }

    public Game getOpenGame() {
        for (Game game :
                games) {
            if (game.getPlayers()[1] == null) {
                return game;
            }
        }
        Game game = new Game();
        games.add(game);
        return game;
    }

    public Game getGameById(UUID id) {
        for(Game game: games){
            if(game.getId() == id )
                return game;
        }
        return null;
    }
}
