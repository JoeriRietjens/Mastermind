package nl.fhict.s3.mastermindlogic;

import nl.fhict.s3.mastermindlogic.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MastermindlogicApplicationTests {
	
	@Test
	void testWongColorDecision()
	{
		//2 player game one make a guess but it is wrong.
	}

	@Test
	void winGame()
	{
		//the colors are all right and player wins game.

	}

	@Test
	void setupColorCode()
	{
		//player setup a color pallet.
		Game game = new Game(1);
		Board board1 = new Board(1, new Colour[]{new Colour(EPinColour.RED),
				new Colour(EPinColour.YELLOW),
				new Colour(EPinColour.GREEN),
				new Colour(EPinColour.BlUE)});


		Colour[] correctColorCode={new Colour(EPinColour.RED),new Colour( EPinColour.YELLOW),new Colour(EPinColour.GREEN),new Colour( EPinColour.BlUE)};
		assertArrayEquals(board1.code,correctColorCode);

	}
  
	@Test
	void testGuessCodeOfOpponent() {
		Game game = new Game(1);
		Board board1 = new Board(1, new Colour[]{new Colour(EPinColour.RED),
													new Colour(EPinColour.YELLOW),
													new Colour(EPinColour.GREEN),
													new Colour(EPinColour.BlUE)});

		Board board2 = new Board(2, new Colour[]{new Colour(EPinColour.ORANGE),
													new Colour(EPinColour.BlUE),
													new Colour(EPinColour.YELLOW),
													new Colour(EPinColour.PURPLE)});

		Player player1 = new Player(1, "JohnDoe", "secret", board1);
		Player player2 = new Player(2, "KarenMiles", "secret", board2);

		player1.board.guessCode(EPinColour.BlUE, EPinColour.BlUE, EPinColour.BlUE,EPinColour.BlUE);
  }
  
  @Test
	void colorTwoPinsRight()
	{
		//only two colors are right on the board. the result is that 2 pins are right.

	}

}
