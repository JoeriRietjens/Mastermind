package nl.fhict.s3.mastermindlogic.controller;

import nl.fhict.s3.mastermindlogic.entity.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class GameController {
    private List<EPinColour> code = new ArrayList<EPinColour>();
    private Board board;
    public GameController(){
        code.add(EPinColour.RED);
        code.add(EPinColour.YELLOW);
        code.add(EPinColour.GREEN);
        code.add(EPinColour.BlUE);
        board = new Board(0, (Colour[]) code.toArray());
    }

    @RequestMapping("/")
    public String index() {
        return "Greetings from Joeri & Bram";
    }

//    @PostMapping("/guess/submit")
//    public List<Clue> submitGuess(@RequestBody List<Colour> colours){
//
//    }
    
    @GetMapping("/guess/emptyrow")
    public Row guessEmptyRow(){
        return new Row(0);
    }

}
