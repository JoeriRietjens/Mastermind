package nl.fhict.s3.mastermindlogic.controller;

import nl.fhict.s3.mastermindlogic.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://localhost:8081", allowedHeaders = "*")
@RestController
public class GameController {

    @Autowired
    Game game;

    public GameController() {
        game = new Game();
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from Joeri & Bram";
    }

    @PostMapping("/guess/submit/{id}")
    public Row submitGuess(@RequestBody Row row, @PathVariable("id") int playerId) {
        return game.getPlayers()[playerId].getBoard().checkRow(row);
    }

    @PostMapping("/code/submit/{id}")
    public EPinColour[] submitCode(@RequestBody EPinColour[] code, @PathVariable("id") int playerId){
        return game.getPlayers()[playerId].getBoard().createCode(code);
    }
    
    @GetMapping("/emptyrow")
    public Row guessEmptyRow() {
        return new Row(0);
    }

    @GetMapping("/board/{id}")
    public Board getBoard(@PathVariable int id) {
        return game.getPlayers()[id].getBoard();
    }


}
