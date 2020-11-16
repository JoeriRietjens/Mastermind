package nl.fhict.s3.mastermindlogic.controller;

import nl.fhict.s3.mastermindlogic.entity.*;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "https://localhost:8081", allowedHeaders = "*")
@RestController
public class GameController {
    private Game game;

    public GameController() {
        game = new Game();
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Joeri & Bram";
    }

    @PostMapping("/guess/submit")
    public Row submitGuess(@RequestBody Row row, int playerId){
        return game.players[playerId].board.checkRow(row);
    }

    @PostMapping("/code/submit")
    public Colour[] submitCode(@RequestBody Colour[] code, int playerId){
        return game.players[playerId].board.createCode(code);
    }
    
    @GetMapping("/emptyrow")
    public Row guessEmptyRow(){
        return new Row(0);
    }

    @GetMapping("/board/{id}")
        public Board getBoard(@PathVariable int id){
            return game.players[id].board;
    }

}
