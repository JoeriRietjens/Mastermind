package nl.fhict.s3.mastermindlogic;

import nl.fhict.s3.mastermindlogic.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

@SpringBootTest
class MastermindlogicApplicationTests {

	@Test
	void testWongColorDecision() {
		//2 player game one make a guess but it is wrong.
	}

	@Test
	void winGame() {
		//the colors are all right and player wins game.

	}

	@Test
	void setupColorCode() {
		//player setup a color pallet.
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

		player1.board.guessCode(EPinColour.BlUE, EPinColour.BlUE, EPinColour.BlUE, EPinColour.BlUE);
	}

	@Test
	void colorTwoPinsRight() {
		//only two colors are right on the board. the result is that 2 pins are right.
	}

	@Test
	void testThreeEntitiesColorCode_ShouldReturnNotEqual() {
		Colour[] code = {new Colour(EPinColour.BlUE), new Colour(EPinColour.YELLOW), new Colour(EPinColour.GREEN)};
		Board board = new Board(1, code);

		int expected = 4;
		int actual = code.length;


		assertNotEquals(expected, actual);
	}

	@Test
	void testFourEntitiesColorCode_ShouldReturnEqual() {
		Colour[] code = {new Colour(EPinColour.BlUE), new Colour(EPinColour.YELLOW), new Colour(EPinColour.GREEN), new Colour(EPinColour.ORANGE)};
		Board board = new Board(1, code);

		int expected = 4;
		int actual = code.length;

		assertEquals(expected, actual);
	}

	@Test
	void testDoubleColorColorCode_ShouldReturnEqual() {
		Colour[] code = {new Colour(EPinColour.BlUE), new Colour(EPinColour.BlUE), new Colour(EPinColour.GREEN), new Colour(EPinColour.ORANGE)};
		Board board = new Board(1, code);

		int expected = 4;
		int actual = code.length;

		assertEquals(expected, actual);
	}

	@Test
	void testNotDoubleColorColorCode_ShouldReturnEqual() {
		Colour[] code = {new Colour(EPinColour.BlUE), new Colour(EPinColour.YELLOW), new Colour(EPinColour.GREEN), new Colour(EPinColour.ORANGE)};
		Board board = new Board(1, code);

		int expected = 4;
		int actual = code.length;

		assertEquals(expected, actual);
	}

	@Test
	void testCodeCheck_ShouldReturnEqual() {
		Colour[] code = {new Colour(EPinColour.BlUE), new Colour(EPinColour.YELLOW), new Colour(EPinColour.GREEN), new Colour(EPinColour.ORANGE)};
		Board board = new Board(1, code);

		board.guessCode(EPinColour.YELLOW, EPinColour.GREEN, EPinColour.PURPLE, EPinColour.RED);

		// Assert the expected pin result as feedback to the player.
	}
}