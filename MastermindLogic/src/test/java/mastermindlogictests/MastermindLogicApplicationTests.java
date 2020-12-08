package mastermindlogictests;

import nl.fhict.s3.mastermindlogic.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MastermindLogicApplicationTests {
	private Board board1;
	private Player player1;
	private Player player2;
	private EClueColour[] clues;

	@BeforeEach
	void setup(){
		board1 = new Board(1);
		Board board2 = new Board(2);
		player1 = new Player(1, "JohnDoe","secret",board1);
		player2 = new Player(2,"JaneDoe","secret", board2);
	}

	@Test
	void testCodeCreation() {
		//player setup a color pallet.
		EPinColour[] codeExpected = {EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE};
		board1.setCode(codeExpected);

		EPinColour[] codeActual = board1.getCode();
		assertArrayEquals(codeExpected, codeActual);
	}

	@Test
	void setupColorCode() {
		//player setup a color pallet.
		board1.setCode(new EPinColour[]{
				EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE
		});

		EPinColour[] correctColorCode={EPinColour.RED, EPinColour.YELLOW, EPinColour.LIME, EPinColour.BLUE};
		assertArrayEquals(board1.getCode(),correctColorCode);
	}

	@Test
	void testGuessCodeOfOpponent() {

		player1.getBoard().setCode(new EPinColour[]{
				EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE
		});

		player2.getBoard().setCode(new EPinColour[]{
				EPinColour.WHITE,
				EPinColour.BLUE,
				EPinColour.YELLOW,
				EPinColour.BLACK
		});

		EPinColour[] inPutPlayer2 = {EPinColour.RED,
				EPinColour.BLUE,
				EPinColour.YELLOW,
				EPinColour.BLACK
		};

		clues = player1.getBoard().getClues(inPutPlayer2);
		EClueColour[] cluesExpect = new EClueColour[]{EClueColour.BLACK,EClueColour.WHITE,EClueColour.WHITE,EClueColour.BLANK};

		assertArrayEquals(cluesExpect,clues);
	}

	@Test
	void testRightGuessCodeOfOpponent() {


		player1.getBoard().setCode(new EPinColour[]{
				EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE
		});

		player2.getBoard().setCode(new EPinColour[]{
				EPinColour.WHITE,
				EPinColour.BLUE,
				EPinColour.YELLOW,
				EPinColour.RED
		});

		EPinColour[] inPutPlayer2 = {
				EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE
		};

		clues = player1.getBoard().getClues(inPutPlayer2);
		EClueColour[] cluesExpect = new EClueColour[]{
				EClueColour.BLACK,
				EClueColour.BLACK,
				EClueColour.BLACK,
				EClueColour.BLACK
		};

		assertArrayEquals(cluesExpect, clues, "ERROR COLOURS ARE NOT RIGHT");
	}

	@Test
	void colorTwoPinsRight() {
		// only two colors are right on the board. the result is that 2 pins are right.
		player1.getBoard().setCode(new EPinColour[]{
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.LIME,
				EPinColour.RED
		});

		EPinColour[] input = new EPinColour[] {
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE
		};

		EClueColour[] cluesExpect = new EClueColour[] {
				EClueColour.BLACK,
				EClueColour.BLACK,
				EClueColour.BLANK,
				EClueColour.BLANK
		};

		clues = player1.getBoard().getClues(input);
		assertArrayEquals(cluesExpect, clues);
	}

	@Test
	void colorOnePinsRight() {
		// only two colors are right on the board. the result is that 2 pins are right.
		player1.getBoard().setCode(new EPinColour[]{
				EPinColour.BLUE,
				EPinColour.LIME,
				EPinColour.LIME,
				EPinColour.RED
		});

		EPinColour[] input = new EPinColour[] {
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE
		};

		EClueColour[] cluesExpect = new EClueColour[] {
				EClueColour.BLACK,
				EClueColour.BLANK,
				EClueColour.BLANK,
				EClueColour.BLANK
		};

		clues = player1.getBoard().getClues(input);
		assertArrayEquals(cluesExpect, clues);
	}

	@Test
	void colorThreePinsRight() {
		// only two colors are right on the board. the result is that 2 pins are right.

		player1.getBoard().setCode(new EPinColour[]{
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.WHITE
		});

		EPinColour[] input = new EPinColour[] {
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE,
				EPinColour.BLUE
		};

		EClueColour[] cluesExpect = new EClueColour[] {
				EClueColour.BLACK,
				EClueColour.BLACK,
				EClueColour.BLACK,
				EClueColour.BLANK
		};

		clues = player1.getBoard().getClues(input);

		assertArrayEquals(cluesExpect, clues);
	}

	@Test
	void testColourChecking() {

		player1.getBoard().setCode(new EPinColour[]{
				EPinColour.BLUE,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.WHITE
		});

		EPinColour[] input = new EPinColour[] {
				EPinColour.LIME,
				EPinColour.RED,
				EPinColour.LIME,
				EPinColour.BLUE
		};

		EClueColour[] cluesExpect = new EClueColour[] {
				EClueColour.BLANK,
				EClueColour.BLANK,
				EClueColour.BLACK,
				EClueColour.WHITE
		};

		clues = player1.getBoard().getClues(input);
		assertArrayEquals(cluesExpect, clues);
	}

	@Test
	void testInputColour() {

		player1.getBoard().setCode(new EPinColour[]{
				EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE
		});

		EPinColour[] ExpectColour = new EPinColour[] {
				EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE
		};

		EClueColour[] cluesExpect = new EClueColour[] {
				EClueColour.BLANK,
				EClueColour.BLANK,
				EClueColour.BLACK,
				EClueColour.WHITE
		};

		// TODO: fix this test. Why make cluesExpect and not use it?
		assertArrayEquals(ExpectColour, player1.getBoard().getCode());

		assertArrayEquals(ExpectColour,player1.getBoard().getCode());
	}

	@Test
	void testRowIsNotCompletelyFilled() {
		Row row = new Row();
		EPinColour[] clues = new EPinColour[] {
			EPinColour.RED, EPinColour.WHITE, EPinColour.BLUE
		};

		row.setGuess(clues);

		assertTrue(row.isNotCompletelyFilled());
	}

	@Test
	void testRowIsNotCompletelyFilledWithNull() {
		Row row = new Row();
		EPinColour[] clues = new EPinColour[] {
			EPinColour.RED, EPinColour.WHITE, EPinColour.BLUE, null
		};

		row.setGuess(clues);

		assertTrue(row.isNotCompletelyFilled());
	}

	@Test
	void testRowIsCompletelyFilled() {
		Row row = new Row();
		EPinColour[] clues = new EPinColour[] {
			EPinColour.RED, EPinColour.WHITE, EPinColour.BLUE, EPinColour.BLACK
		};

		row.setGuess(clues);

		assertFalse(row.isNotCompletelyFilled());
	}
}