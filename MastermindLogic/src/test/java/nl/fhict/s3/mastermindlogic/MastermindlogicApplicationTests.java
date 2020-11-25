package nl.fhict.s3.mastermindlogic;

import nl.fhict.s3.mastermindlogic.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MastermindlogicApplicationTests {

	@Test
	void testCodeCreation() {
		//player setup a color pallet.
		Game game = new Game(1);
		Board board1 = new Board(1);
		EPinColour[] codeExpected = {EPinColour.RED,
								 EPinColour.YELLOW,
								 EPinColour.LIME,
								 EPinColour.BLUE};
		board1.createCode(codeExpected);


		EPinColour[] codeActual = board1.code;
		assertArrayEquals(codeExpected, codeActual);
	}

	@Test
	void setupColorCode() {
		//player setup a color pallet.
		Game game = new Game(1);
		Board board1 = new Board(1, new EPinColour[]{EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE});



		EPinColour[] correctColorCode={EPinColour.RED, EPinColour.YELLOW, EPinColour.LIME, EPinColour.BLUE};
		assertArrayEquals(board1.code,correctColorCode);

	}

	@Test
	void testGuessCodeOfOpponent() {
		Game game = new Game(1);
		Board board1 = new Board(1, new EPinColour[]{EPinColour.RED,
				EPinColour.YELLOW,
				EPinColour.LIME,
				EPinColour.BLUE});

		Board board2 = new Board(2, new EPinColour[]{EPinColour.WHITE,
				EPinColour.BLUE,
				EPinColour.YELLOW,
				EPinColour.BLACK});

		Player player1 = new Player(1, "JohnDoe", "secret", board1);
		Player player2 = new Player(2, "KarenMiles", "secret", board2);
		EPinColour[] inPutPlayer2={EPinColour.RED,
				EPinColour.BLUE,
				EPinColour.YELLOW,
				EPinColour.BLACK};

		EClueColour[] clues= player1.getBoard().getClues(inPutPlayer2);
		EClueColour[] cluesExpect=new EClueColour[]{EClueColour.BLACK,EClueColour.WHITE,EClueColour.WHITE,EClueColour.BLANK};

		assertArrayEquals(cluesExpect,clues);

	}

	@Test
	void testRightGuessCodeOfOpponent() {
		Game game = new Game(1);
		EClueColour[] clues=new EClueColour[4];
		Board board1 = new Board(1, new EPinColour[]{EPinColour.RED,
				EPinColour.YELLOW, EPinColour.LIME, EPinColour.BLUE });

		Board board2 = new Board(2, new EPinColour[] { EPinColour.WHITE, EPinColour.BLUE,
				EPinColour.YELLOW, EPinColour.BLACK});

		Player player1 = new Player(1, "JohnDoe", "secret", board1);
		Player player2 = new Player(2, "KarenMiles", "secret", board2);
		EPinColour[] inPutPlayer2 = { EPinColour.RED, EPinColour.YELLOW,
				EPinColour.LIME, EPinColour.BLUE };

		clues = player1.getBoard().getClues(inPutPlayer2);
		EClueColour[] cluesExpect = new EClueColour[] { EClueColour.BLACK, EClueColour.BLACK, EClueColour.BLACK,
				EClueColour.BLACK };
		assertArrayEquals(cluesExpect, clues, "ERROR COLOURS ARE NOT RIGHT");

	}

	@Test
	void colorTwoPinsRight() {
		// only two colors are right on the board. the result is that 2 pins are right.
		Game game = new Game(1);
		Board board1 = new Board(1, new EPinColour[] { EPinColour.BLUE, EPinColour.BLUE,
				EPinColour.LIME, EPinColour.WHITE});
		Player player1 = new Player(1, "JohnDoe", "secret", board1);

		EClueColour[] clues = new EClueColour[4];

		EPinColour[] input = new EPinColour[] { EPinColour.BLUE, EPinColour.BLUE,
				EPinColour.BLUE, EPinColour.BLUE};
		EClueColour[] cluesExpect = new EClueColour[] { EClueColour.BLACK, EClueColour.BLACK, EClueColour.BLANK,
				EClueColour.BLANK};

		clues = player1.getBoard().getClues(input);
		assertArrayEquals(cluesExpect, clues);
	}

	@Test
	void colorOnePinsRight() {
		// only two colors are right on the board. the result is that 2 pins are right.
		Game game = new Game(1);
		Board board1 = new Board(1, new EPinColour[] { EPinColour.BLUE, EPinColour.LIME,
				EPinColour.LIME, EPinColour.WHITE});
		Player player1 = new Player(1, "JohnDoe", "secret", board1);

		EClueColour[] clues = new EClueColour[4];

		EPinColour[] input = new EPinColour[] { EPinColour.BLUE, EPinColour.BLUE,
				EPinColour.BLUE, EPinColour.BLUE};
		EClueColour[] cluesExpect = new EClueColour[] { EClueColour.BLACK, EClueColour.BLANK, EClueColour.BLANK,
				EClueColour.BLANK};

		clues = player1.getBoard().getClues(input);
		assertArrayEquals(cluesExpect, clues);
	}

	@Test
	void colorThreePinsRight() {
		// only two colors are right on the board. the result is that 2 pins are right.
		Game game = new Game(1);
		Board board1 = new Board(1, new EPinColour[] { EPinColour.BLUE, EPinColour.BLUE,
				EPinColour.BLUE, EPinColour.WHITE});
		Player player1 = new Player(1, "JohnDoe", "secret", board1);

		EClueColour[] clues = new EClueColour[4];

		EPinColour[] input = new EPinColour[] { EPinColour.BLUE, EPinColour.BLUE,
				EPinColour.BLUE, EPinColour.BLUE};
		EClueColour[] cluesExpect = new EClueColour[] { EClueColour.BLACK, EClueColour.BLACK, EClueColour.BLACK,
				EClueColour.BLANK};

		clues = player1.getBoard().getClues(input);
		assertArrayEquals(cluesExpect, clues);
	}

	@Test
	void TestColourChecking() {
		Game game = new Game(1);
		Board board1 = new Board(1,
				new EPinColour[] { EPinColour.BLUE, EPinColour.YELLOW, EPinColour.LIME, EPinColour.WHITE});
		Player player1 = new Player(1, "JohnDoe", "secret", board1);

		EClueColour[] clues = new EClueColour[4];
		EPinColour[] input = new EPinColour[] { EPinColour.LIME, EPinColour.BLACK, EPinColour.LIME, EPinColour.BLUE };
		EClueColour[] cluesExpect = new EClueColour[] { EClueColour.BLANK, EClueColour.BLANK, EClueColour.BLACK,
				EClueColour.WHITE};
		clues = player1.getBoard().getClues(input);

		assertArrayEquals(cluesExpect, clues);
	}

	@Test
	void TestInputColour() {
		Game game = new Game(1);
		Board board1 = new Board(1,
				new EPinColour[] { EPinColour.RED, EPinColour.YELLOW, EPinColour.LIME, EPinColour.BLUE });
		Player player1 = new Player(1, "JohnDoe", "secret", board1);

		EPinColour[] ExpectColour = new EPinColour[] { EPinColour.RED, EPinColour.YELLOW,
				EPinColour.LIME, EPinColour.BLUE};

		assertArrayEquals(ExpectColour,player1.getBoard().code);
	}

	@Test
	void testRowIsNotCompletelyFilled() {
		Row row = new Row();
		EPinColour[] clues = new EPinColour[] {
			EPinColour.RED, EPinColour.WHITE, EPinColour.BLUE
		};

		row.setCode(clues);

		assertTrue(row.isNotCompletelyFilled());
	}

	@Test
	void testRowIsNotCompletelyFilledWithNull() {
		Row row = new Row();
		EPinColour[] clues = new EPinColour[] {
			EPinColour.RED, EPinColour.WHITE, EPinColour.BLUE, null
		};

		row.setCode(clues);

		assertTrue(row.isNotCompletelyFilled());
	}

	@Test
	void testRowIsCompletelyFilled() {
		Row row = new Row();
		EPinColour[] clues = new EPinColour[] {
			EPinColour.RED, EPinColour.WHITE, EPinColour.BLUE, EPinColour.BLACK
		};

		row.setCode(clues);

		assertFalse(row.isNotCompletelyFilled());
	}
}